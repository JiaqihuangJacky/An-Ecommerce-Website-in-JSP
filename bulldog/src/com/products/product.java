package com.products;

public class product {

	//<!-- id,sku,pic,name,pict,price,delieverFee,descript,seller,categ,postDate  -->

	private int id;
	private String sku;	
	private String name;		
	private double price;	
	private double DelieverFee;	
	private String Descript;			
	private int Seller;	
	private String Date;
	private String imageurl;
	


	public product(int id, String sku, String name, double price,
			double delieverFee, String descript, int seller, String date,
			String imageurl) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.price = price;
		DelieverFee = delieverFee;
		Descript = descript;
		Seller = seller;
		Date = date;
		this.imageurl = imageurl;
	}






	public String getImageurl() {
		return imageurl;
	}






	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}






	public String getDate() {
		return Date;
	}



	


	public void setDate(String date) {
		Date = date;
	}






	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public product(int id, String sku, String name, double price,
			double delieverFee, String descript, int seller) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.price = price;
		DelieverFee = delieverFee;
		Descript = descript;
		Seller = seller;
	}






	public product(int id, String sku, String name, double price,
			double delieverFee, String descript, int seller, String date) {
		super();
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.price = price;
		DelieverFee = delieverFee;
		Descript = descript;
		Seller = seller;
		Date = date;
	}






	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getDelieverFee() {
		return DelieverFee;
	}



	public void setDelieverFee(double delieverFee) {
		DelieverFee = delieverFee;
	}



	public String getDescript() {
		return Descript;
	}



	public void setDescript(String descript) {
		Descript = descript;
	}



	public int getSeller() {
		return Seller;
	}



	public void setSeller(int seller) {
		Seller = seller;
	}






	public product(String sku, String name, double price, double delieverFee,
			String descript, int seller) {
		super();
		this.sku = sku;
		this.name = name;
		this.price = price;
		DelieverFee = delieverFee;
		Descript = descript;
		Seller = seller;
	}






}
