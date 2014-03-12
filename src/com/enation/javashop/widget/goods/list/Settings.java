package com.enation.javashop.widget.goods.list;

/**
 * 
 * 商品列表挂件设置
 * @author kingapex
 *2010-5-13上午11:24:27
 */
public class Settings {
	
	private static final String OFF = "off";
	private static final String ON ="on";
	
	public Settings(){
		
		this.goodsNum =10;
		this.columnnum = 4;
		this.goodsImgPosition="top";
		this.titleImgPosition= "left";
		
		this.showGoodsImg=ON;
		this.showGoodsName=ON;
		this.showGoodsDesc=ON;
		this.showTitleImg =OFF;
		this.showTitle=OFF;
		
		this.titleImgSrc=""; 
		this.titleImgAlt="";
		this.titleImgHref="";
		titleImgWidth= "100%";
		
		
		
	}
	private String type; //default,morediv
	private String showGoodsDesc; //是否显示商品描述
	private String showGoodsImg; //是否显示商品图片
	private String showGoodsName ;//是否显示商品名称
	private String goodsImgPosition; //商品图片位置
	
	private Integer goodsNum; //显示商品数
	private Integer columnnum; //显示列数
	private Integer columnwidth;  //计算好的列宽 : 百分比值
	private String thumbnail_pic_width; //商品图片缩略图宽
	private String thumbnail_pic_height; //商品图片缩略图高
	
	private String showTitleImg; //是否显示分类图片
	private String showTitle; //是否显示 分类描述
	private String titleImgHref; //分类图片连接
	private String titleImgSrc;  //分类图片地址
	private String titleImgPosition; //分类图片位置
	private String titleImgAlt; //分类图片提示
	private String titleDesc; //分类描述
	private String titleImgWidth; //分类图片宽
	private Integer  changeEffect;
	
	
	public String getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	public String getShowGoodsDesc() {
		return showGoodsDesc;
	}
	public void setShowGoodsDesc(String showGoodsDesc) {
		this.showGoodsDesc = showGoodsDesc;
	}
	public String getShowGoodsImg() {
		return showGoodsImg;
	}
	public void setShowGoodsImg(String showGoodsImg) {
		this.showGoodsImg = showGoodsImg;
	}
	public String getShowGoodsName() {
		return showGoodsName;
	}
	public void setShowGoodsName(String showGoodsName) {
		this.showGoodsName = showGoodsName;
	}
	public String getGoodsImgPosition() {
		return goodsImgPosition;
	}
	public void setGoodsImgPosition(String goodsImgPosition) {
		this.goodsImgPosition = goodsImgPosition;
	}
	public Integer getColumnnum() {
		return columnnum;
	}
	public void setColumnnum(Integer columnnum) {
		this.columnnum = columnnum;
	}
	public Integer getColumnwidth() {
		return columnwidth;
	}
	public void setColumnwidth(Integer columnwidth) {
		this.columnwidth = columnwidth;
	}
 
 
	public String getTitleImgHref() {
		return titleImgHref;
	}
	public void setTitleImgHref(String titleImgHref) {
		this.titleImgHref = titleImgHref;
	}
	public String getTitleImgSrc() {
		return titleImgSrc;
	}
	public void setTitleImgSrc(String titleImgSrc) {
		this.titleImgSrc = titleImgSrc;
	}
	public String getTitleImgPosition() {
		return titleImgPosition;
	}
	public void setTitleImgPosition(String titleImgPosition) {
		this.titleImgPosition = titleImgPosition;
	}
	public String getTitleImgAlt() {
		return titleImgAlt;
	}
	public void setTitleImgAlt(String titleImgAlt) {
		this.titleImgAlt = titleImgAlt;
	}
	public String getShowTitleImg() {
		return showTitleImg;
	}
	public void setShowTitleImg(String showTitleImg) {
		this.showTitleImg = showTitleImg;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getTitleDesc() {
		return titleDesc;
	}
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}
	public String getThumbnail_pic_width() {
		return thumbnail_pic_width;
	}
	public void setThumbnail_pic_width(String thumbnailPicWidth) {
		thumbnail_pic_width = thumbnailPicWidth;
	}
	public String getThumbnail_pic_height() {
		return thumbnail_pic_height;
	}
	public void setThumbnail_pic_height(String thumbnailPicHeight) {
		thumbnail_pic_height = thumbnailPicHeight;
	}
	public String getTitleImgWidth() {
		return titleImgWidth;
	}
	public void setTitleImgWidth(String titleImgWidth) {
		this.titleImgWidth = titleImgWidth;
	}
	public Integer getChangeEffect() {
		return changeEffect;
	}
	public void setChangeEffect(Integer changeEffect) {
		this.changeEffect = changeEffect;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
 
}