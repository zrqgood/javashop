package com.enation.javashop.core.service.impl.batchimport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;
import com.enation.framework.util.XMLUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.ImportDataSource;
import com.enation.javashop.core.model.support.GoodsTypeDTO;
import com.enation.javashop.core.service.IBrandManager;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.core.service.batchimport.IGoodsDataBatchManager;
import com.enation.javashop.core.service.batchimport.IGoodsDataImporter;

/**
 * 默认的商品数据批量导入器实现
 * @author kingapex
 *
 */
public class GoodsDataBatchManager implements IGoodsDataBatchManager {
	protected final Logger logger = Logger.getLogger(getClass());
	private IBrandManager brandManager;
	private IGoodsTypeManager goodsTypeManager;
	private IGoodsCatManager goodsCatManager;
	
	
	private IDaoSupport  daoSupport;
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void batchImport(String path) {
		
		
		if(this.logger.isDebugEnabled()){
			logger.debug("开始导入商品数据...");
		}
		
		Document configDoc  = this.load(path);
		
		Element configEl  = XMLUtil.getChildByTagName(configDoc, "config");
		
		//商品批量数据文件夹位置
		String datafolder = configEl.getAttribute("datafolder");
		
		//打开商品数据excel
		String excel = configEl.getAttribute("excel");
		Workbook goodsWb = this.openExcel(excel);
		

		
		//获取所有分类节点列表
		NodeList catNodeList = configEl.getElementsByTagName("cat");
		for(int i=0;i<catNodeList.getLength();i++){
			Node catNode  = catNodeList.item(i);
			this.processSheet(datafolder, goodsWb, catNode);
			
		}
	}
	
	
	private void processSheet(String datafolder,Workbook goodsWb,Node catNode   ){
		
		//找到sheet节点
		Element beforeSheetNode = XMLUtil.getChildByTagName(catNode, "beforesheet");
		
		//此类别数据的sheet位置
		int sheetIndex = Integer.valueOf( XMLUtil.getChildByTagName(catNode, "sheet_index" ).getTextContent() ) ; 
		
		//数据记录开始行位置
		int rowStartNum =  Integer.valueOf( XMLUtil.getChildByTagName(catNode, "start_rouwnum" ).getTextContent() ) ; 
		
		//此类别对应的类别id
		int catid = Integer.valueOf( XMLUtil.getChildByTagName(catNode, "id" ).getTextContent() ) ; 
		
		//商品编号所在列位置
		int  goodsIdCluNum = Integer.valueOf( XMLUtil.getChildByTagName(catNode, "goodsid_column" ).getTextContent() ) ; 

		
		/**
		 * ======================
		 * 生成此类别的导入数据源
		 * =======================
		 */
		ImportDataSource importDs = new ImportDataSource();
		
		Cat cat =this.goodsCatManager.getById(catid);
		
		
		//获取此类别商品的类型
		GoodsTypeDTO typeDTO = this.goodsTypeManager.get(cat.getType_id());
		importDs.setBrandList(brandManager.list());
		importDs.setPropList(typeDTO.getPropList());
		
		//获取此类别的sheet 
		Sheet sheet = goodsWb.getSheetAt(sheetIndex);
		int lastRowNum = sheet.getLastRowNum();
		
		//此类别节点的行配置列表
		NodeList rowList  = beforeSheetNode.getElementsByTagName("column");
		Element processorNode = XMLUtil.getChildByTagName(catNode, "processors"); //处理器
		NodeList importerNodeList =  processorNode.getElementsByTagName("importer");
		
 
		if(this.logger.isDebugEnabled()){
			logger.debug("开始导入类别["+cat.getName()+"]...");
		}
		
		
		//声明要导入的goods数据
		Map goods =null;
		
		for(int i=rowStartNum;i<lastRowNum+1;i++){
			
			Row row  = sheet.getRow(i);
			
			/**
			 * ----------------
			 * 计算商品的编号
			 * ----------------
			 */
			int goodsNum=0;
			if(goodsIdCluNum==-1){
				goodsNum = row.getRowNum()+1;
			}else{
				goodsNum = Double.valueOf(row.getCell(goodsIdCluNum).getNumericCellValue()).intValue();
			}
			
			if(goodsNum!=0){//如果是一个新商品，生成一个新goods
				
				importDs.setDatafolder(  datafolder +"/"+ XMLUtil.getChildByTagName(catNode, "name" ).getTextContent() +"/"+goodsNum);
				importDs.setNewGoods(true);
				importDs.setGoodsNum(goodsNum);
				goods = new HashMap();
				goods.put("market_enable", 1 );
				goods.put("cat_id", catid);
				goods.put("type_id", cat.getType_id());
				goods.put("have_spec", 0);
				goods.put("cost", 0  ); 
				goods.put("store", 1 );
				goods.put("weight",0 );
				goods.put("disabled", 0);
				goods.put("create_time", System.currentTimeMillis());
				goods.put("view_count",0);
				goods.put("buy_count",0);
				goods.put("last_modify",System.currentTimeMillis());
				String sn = "G" + DateUtil.toString( new Date(System.currentTimeMillis()) ,"yyyyMMddhhmmss" )+StringUtil.getRandStr(4);
				goods.put("sn",sn);
			}else{
				importDs.setNewGoods(false);
			}
			
			
			/**
			 * =================
			 * 处理列数据
			 * =================
			 */
			for(int j=0;j<rowList.getLength();j++){
				Element rowNode = (Element)rowList.item(j);
				String index = rowNode.getAttribute("index");
				String importer =  rowNode.getAttribute("importer");
			 
				Object value =  this.getCellValue( row.getCell(Integer.valueOf(index)) );
				IGoodsDataImporter goodsDataImporter = SpringContextHolder.getBean(importer);
				goodsDataImporter.imported(value, rowNode, importDs, goods);
				 
			}
			
			if(goodsNum!=0){//如果是新商品插入数据库
				this.daoSupport.insert("es_goods", goods);
				int goodsid = this.daoSupport.getLastId("es_goods");
				goods.put("goods_id", goodsid);
			}
			
			
			
			/**
			 * 执行后期处理
			 */
			Element afterSheetNode = XMLUtil.getChildByTagName(catNode, "aftersheet");
			
			if(afterSheetNode!=null){
				
				if(goodsNum==4){
					int a=1;
				}
				//此类别节点的行配置列表
				NodeList afterRowList  = afterSheetNode.getElementsByTagName("column");
				/**
				 * =================
				 * 处理后期列数据
				 * =================
				 */
				for(int j=0;j<afterRowList.getLength();j++){
					Element rowNode = (Element)afterRowList.item(j);
					String index = rowNode.getAttribute("index");
					String importer =  rowNode.getAttribute("importer");
				 
					Object value =  this.getCellValue( row.getCell(Integer.valueOf(index)) );
					IGoodsDataImporter goodsDataImporter = SpringContextHolder.getBean(importer);
					goodsDataImporter.imported(value, rowNode, importDs, goods);
					 
				}
			}
			
			/**
			 * =======================
			 * 执行各个导入器
			 * =======================
			 */
			for( int j=0;j<importerNodeList.getLength();j++){
				Element node =(Element)importerNodeList.item(j);
				String importer = node.getTextContent();
				IGoodsDataImporter goodsDataImporter = SpringContextHolder.getBean(importer);
			 
				goodsDataImporter.imported(null, node, importDs, goods);
			}
			
		}
		
		 
		if(this.logger.isDebugEnabled()){
			logger.debug("导入类别["+cat.getName()+"]完成...");
		}
		

	}
	
	/**
	 * 打开excel
	 * @param excelPath
	 * @return
	 */
	private Workbook openExcel(String excelPath){
 
        try {
        	POIFSFileSystem  fs = new POIFSFileSystem(new FileInputStream(excelPath)); 
        	Workbook wb = new HSSFWorkbook(fs);
        	return wb;
        } catch (IOException e) {
        	e.printStackTrace();
        	return null;
        }
		 
	}
	
	
	/**
	 * 加载导入配置xml文件
	 * @param path
	 * @return
	 */
	private Document load(String path){
		try {
		    DocumentBuilderFactory factory = 
		    DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(path);
		    return document;
		}
		catch (Exception e) {
		 
			e.printStackTrace();
			throw new RuntimeException("load ["+path+"]    error" );
		} 	 
	}

	
	
	private Object getCellValue(Cell cell){
		 if(cell==null) return null;
		int celltype = cell.getCellType();
		
		if(celltype ==  Cell.CELL_TYPE_NUMERIC)
			return cell.getNumericCellValue();
		
		if(celltype == Cell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
	
		if(celltype == Cell.CELL_TYPE_FORMULA){
			return cell.getStringCellValue();
		}
		
		if(celltype  == cell.CELL_TYPE_BLANK)
			return "";
		
		if(celltype ==  cell.CELL_TYPE_ERROR)
			return "";
		
		
		
		return "";
	}
	

	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}


	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}




	public IGoodsTypeManager getGoodsTypeManager() {
		return goodsTypeManager;
	}


	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}


	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}


	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}


	public IBrandManager getBrandManager() {
		return brandManager;
	}


	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

}
