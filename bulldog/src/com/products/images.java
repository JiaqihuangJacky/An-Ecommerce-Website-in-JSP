package com.products;

public class images {
	private String picurl;
	private int prod;
	
	public images(String picurl, int prod) {
		super();
		this.picurl = picurl;
		this.prod = prod;
	}
	
	public String getPicurl() {
		return picurl;
	}
	
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	public int getProd() {
		return prod;
	}
	
	public void setProd(int prod) {
		this.prod = prod;
	}
	

}
