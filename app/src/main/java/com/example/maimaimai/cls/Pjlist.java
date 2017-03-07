package com.example.maimaimai.cls;

public class Pjlist {

	private int image;
	private String tv01;
	private String tv02;
	private String tv03;
	private String tv04;
	
	public Pjlist(int imageId, String tv01, String tv02, String tv03,
			String tv04) {
		super();
		this.image = imageId;
		this.tv01 = tv01;
		this.tv02 = tv02;
		this.tv03 = tv03;
		this.tv04 = tv04;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getTv01() {
		return tv01;
	}

	public void setTv01(String tv01) {
		this.tv01 = tv01;
	}

	public String getTv02() {
		return tv02;
	}

	public void setTv02(String tv02) {
		this.tv02 = tv02;
	}

	public String getTv03() {
		return tv03;
	}

	public void setTv03(String tv03) {
		this.tv03 = tv03;
	}

	public String getTv04() {
		return tv04;
	}

	public void setTv04(String tv04) {
		this.tv04 = tv04;
	}
	
	
	
}
