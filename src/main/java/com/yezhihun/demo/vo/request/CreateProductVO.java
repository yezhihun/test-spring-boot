package com.yezhihun.demo.vo.request;

import java.io.Serializable;

public class CreateProductVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品ID
	 */
	private Long productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品描述
	 */
	private String text;
	/**
	 * 商品所在经纬度位置描述
	 */
	private String position;
	/**
	 * 纬度坐标
	 */
	private Double latitude;
	/**
	 * 经度坐标
	 */
	private Double longitude;

	/**
	 * 图片地址
	 */
	private String[] imageList;

	public String[] getImageList() {
		return imageList;
	}
	
	public void setImageList(String[] imageList) {
		this.imageList = imageList;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
