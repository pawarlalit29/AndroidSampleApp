package com.lalit.retailstore.Database;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class ProductDetails implements Serializable {

	/**
	 * Model class for product_details database table
	 */
	private static final long serialVersionUID = -222864131214757024L;
	
	public static final String ID_FIELD = "cart_id";
	public static final String PID_FIELD = "product_id";

	// Primary key defined as an auto generated integer 
	// If the database table column name differs than the Model class variable name, the way to map to use columnName
	@DatabaseField(generatedId = true, columnName = ID_FIELD)
	public int cartId;

	// Define a String type field to hold product's id
	@DatabaseField(columnName = PID_FIELD)
	public String productID;

	// Define a String type field to hold product's name
	@DatabaseField(columnName = "product_title")
	public String productTitle;

	// Define a String type field to hold product's disc
	@DatabaseField(columnName = "product_disc")
	public String productDisc;


	// Define a String type field to hold product's cat
	@DatabaseField(columnName = "product_category")
	public String productCat;

	// Define a String type field to hold product's imageurl
	@DatabaseField(columnName = "product_image")
	public String productImageUrl;

	// Define a String type field to hold product's imageurl
	@DatabaseField(columnName = "product_price")
	public String productPrice;

	// Define a String type field to hold product's date of insertion
	@DatabaseField(columnName = "added_date")
	public Date addedDate;
	
	// Default constructor is needed for the SQLite, so make sure you also have it
	public ProductDetails(){
		
	}
	
	//For our own purpose, so it's easier to create a ProductDetails object

	public ProductDetails(String productID, String productTitle, String productDisc, String productCat, String productImageUrl, String productPrice) {
		this.productID = productID;
		this.productTitle = productTitle;
		this.productDisc = productDisc;
		this.productCat = productCat;
		this.productImageUrl = productImageUrl;
		this.productPrice = productPrice;
	}
}
