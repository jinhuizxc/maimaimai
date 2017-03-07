package com.example.maimaimai.cls;

import com.example.maimaimai.utils.MyImageCache;


public class Username_All {

	private static String name = "null";
	private static boolean stauts = false;
	public static MyImageCache mCache; 
	private static String url_host = "http://192.168.0.12:8080/project_myapp/";
	//http://localhost:8080/project_myapp/product02_01.png
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Username_All.name = name;
	}
	
	public static void setStauts(boolean stauts) {
		Username_All.stauts = stauts;
	}
	public static boolean getStauts() {
		return stauts;
	}
	public static String getUrl_host() {
		return url_host;
	} 
	
}
