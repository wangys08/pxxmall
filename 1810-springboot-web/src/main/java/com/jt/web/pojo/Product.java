package com.jt.web.pojo;
/**
 * 每个当前类的对象
 * 都对应t_product一行数据
 * @author Administrator
 *
 */
public class Product {
	//编写，根据驼峰命名，定义的所有表格字段
	//对应的属性；
	private String productId;
	private String productName;
	private Double productPrice;
	private String productCategory;
	private String productImgurl;
	private Integer productNum;
	private String productDescription;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductImgurl() {
		return productImgurl;
	}
	public void setProductImgurl(String productImgurl) {
		this.productImgurl = productImgurl;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productCategory=" + productCategory + ", productImgurl=" + productImgurl + ", productNum="
				+ productNum + ", productDescription=" + productDescription + "]";
	}
	
	
}
