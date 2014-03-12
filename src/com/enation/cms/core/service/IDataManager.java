package com.enation.cms.core.service;

import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.MultiSite;
import com.enation.framework.database.Page;

/**
 * 数据内容管理
 * @author kingapex
 * 2010-7-5下午01:54:45
 */
public interface IDataManager {
	
	
	/**
	 * 对某模型进行分页搜索
	 * @param pageNo
	 * @param pageSize
	 * @param modelid
	 * @return
	 */
	public Page search(int pageNo ,int pageSize,int modelid);
	
	public Page search(int pageNo ,int pageSize,int modelid, boolean showchild);
	
	
	/**
	 * 对模型进行搜索，不分页
	 * @param modelid
	 * @return
	 */
	public List search(int modelid);
	
	public List search(int modelid, boolean showchild);
	
	/**
	 * 添加数据内容
	 * @param modelid 对应模型id
	 * @param catid   所属数据类别
	 */
	public void add(Integer modelid,Integer catid);
	
	
	/**
	 * 修改数据内容<br/>
	 * 因数据内容的具体字段是动态，<br/>
	 * 所以在参数中并未传递具体的修改数据，<br/>
	 * 需要实现体通过request来获取具体的数据。
	 * @param modelid  对应模型id
	 * @param catid    所属数据类别
	 * @param dataid 要修改的内容id
	 */
	public void edit(Integer modelid,Integer catid,Integer dataid);
	
	
	/**
	 * 删除一个数据内容
	 * @param catid 所属类别，需要根据此类别找到相应模型
	 * @param dataid 要删除的数据id 
	 */
	public void delete(Integer catid,Integer articleid);
	
	
	
	/**
	 * 分页读取某类别下的数据内容列表<br>
	 * <b>只读取当前类别的，并不读取子类别下的内容</b>
	 * @param catid 所属分类id
	 * @param page   页码
	 * @param pageSize 页大小
	 * @return  分页数据集 @see {@link Page}
	 */
	public Page list(Integer catid,int page,int pageSize);
	
	public Page list(Integer catid,int page,int pageSize, Integer site_code);
	
	public void importdata(Integer catid, Integer[] ids);
	
	/**
	 * 读取某类别下的数据内容<br/>
	 * <b>只读取当前类别的，并不读取子类别下的内容</b><br/>
	 * @param catid
	 * @return
	 */
	public List list(Integer catid);
	
	
	/**
	 *  分页读取某类别下的数据内容列表<br>
	 *  <b>读取当前类别和其所有子类别下的内容</b>
	 * @param catid 分类id
	 * @param page 页码
	 * @param pageSize 页大小
	 * @return  分页数据集 @see {@link Page}
	 */
	public Page listAll(Integer catid,String term,int page,int pageSize);
	/**
	 * 读取相关文章
	 * @param catid
	 * @param id
	 * @return
	 */
	
	public Page listAll(Integer catid,String term,String orders, boolean showchild, int page, int pageSize);
	/**
	 * 读取相关文章
	 * @param catid
	 * @param id
	 * @return
	 */
	
	public List listRelated(Integer catid,Integer relcatid,Integer id,String fieldname);
	
	/**
	 * 读取某个内容数据的详细
	 * @param dataid 数据id
	 * @param catid  所属分类id
	 * @param filter 是否过滤值，过滤值：将字段索引值替换为相应字面值<br>
	 * 如 在radio或select的数据:1：男，2女，索引值为 1，如果过滤值的话则会将字段值更新为男。
	 * @return 以字段名为key字段值为value的map
	 */
	public Map get(Integer articleid,Integer catid,boolean filter);
	
	/**
	 * 更新排序
	 * @param ids
	 * @param sorts
	 */
	public void updateSort(Integer[] ids,Integer[] sorts,Integer catid );
	
	/**
	 * 更新文章浏览量
	 * @param id
	 */
	public void updateHit(Integer id,Integer catid);
	
	
	/**
	 * 统计文章信息
	 * @return
	 */
	public Map census();
	
	
	/**
	 * 获取某篇文章的当前类别下下一个文章id，如果是最后一篇则返回0
	 * @param currentId
	 * @return
	 */
	public int getNextId(Integer currentId,Integer catid);
	
	
	
	
	/**
	 * 获取某篇文章的当前类别下的上一篇文章id,如果是第一篇则为0
	 * @param currentId
	 * @param catid
	 * @return
	 */
	public int getPrevId(Integer currentId,Integer catid);
}
