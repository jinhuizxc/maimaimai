package com.example.maimaimai.cls;

public class AddressInfo {

	private String name;
	private String phone;
	private String address;
	private boolean checkBox;
	private String text01;
	private String text02;
	
	public AddressInfo(String name, String phone, String address,
			boolean checkBox, String text01, String text02) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.checkBox = checkBox;
		this.text01 = text01;
		this.text02 = text02;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public String getText01() {
		return text01;
	}

	public void setText01(String text01) {
		this.text01 = text01;
	}

	public String getText02() {
		return text02;
	}

	public void setText02(String text02) {
		this.text02 = text02;
	}
	
	
}
