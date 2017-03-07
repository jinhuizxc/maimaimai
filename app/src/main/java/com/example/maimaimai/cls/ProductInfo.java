package com.example.maimaimai.cls;

public class ProductInfo {
    
	private int id;
	private String url;
	private String productname;
	private String color01url;
	private String color01;
	private String color02;
	private String color02url;
	
	private String type01;
	private String type02;
	private String type03;
	private String type04;
	
	private String price;
	private int sendpay;
	private String catergory;

	public ProductInfo(int id, String url, String productname,
			String color01url, String color01, String color02,
			String color02url, String type01, String type02, String type03,
			String type04, String price, int sendpay, String catergory) {
		super();
		this.id = id;
		this.url = url;
		this.productname = productname;
		this.color01url = color01url;
		this.color01 = color01;
		this.color02 = color02;
		this.color02url = color02url;
		this.type01 = type01;
		this.type02 = type02;
		this.type03 = type03;
		this.type04 = type04;
		this.price = price;
		this.sendpay = sendpay;
		this.catergory = catergory;
	}

	public ProductInfo(String url, String productname, String price) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.productname = productname;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getColor01() {
		return color01;
	}

	public void setColor01(String color01) {
		this.color01 = color01;
	}

	public String getColor01url() {
		return color01url;
	}

	public void setColor01url(String color01url) {
		this.color01url = color01url;
	}

	public String getColor02() {
		return color02;
	}

	public void setColor02(String color02) {
		this.color02 = color02;
	}

	public String getColor02url() {
		return color02url;
	}

	public void setColor02url(String color02url) {
		this.color02url = color02url;
	}

	public String gettype01() {
		return type01;
	}

	public void settype01(String type01) {
		this.type01 = type01;
	}

	public String gettype02() {
		return type02;
	}

	public void settype02(String type02) {
		this.type02 = type02;
	}

	public String gettype03() {
		return type03;
	}

	public void settype03(String type03) {
		this.type03 = type03;
	}
	
	public String gettype04() {
		return type04;
	}
	
	public void settype04(String type04) {
		this.type04 = type04;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getSendpay() {
		return sendpay;
	}

	public void setSendpay(int sendpay) {
		this.sendpay = sendpay;
	}

	public String getCatergory() {
		return catergory;
	}

	public void setCatergory(String catergory) {
		this.catergory = catergory;
	}

	

}
