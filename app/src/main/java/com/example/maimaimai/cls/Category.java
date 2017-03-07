package com.example.maimaimai.cls;

public class Category {

	private int imageSource;
	private String title;
	private String content;
	
	public Category(int imageSource, String title, String content) {
		super();
		this.imageSource = imageSource;
		this.title = title;
		this.content = content;
	}

	public int getImageSource() {
		return imageSource;
	}

	public void setImageSource(int imageSource) {
		this.imageSource = imageSource;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
