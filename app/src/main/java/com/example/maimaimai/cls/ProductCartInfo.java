package com.example.maimaimai.cls;

public class ProductCartInfo {

	private String url;
	private String name;
	private String price;
	private String type;
	private String color;
	private int num;

	public ProductCartInfo(String url, String name, int num, String color,
			String type, String price) {
		super();
		this.url = url;
		this.name = name;
		this.num = num;
		this.color = color;
		this.type = type;
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getprice() {
		return price;
	}

	public void setprice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return url + name + num + color + price + type;
	}

}
