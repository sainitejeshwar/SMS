package com.flipkart.bean;


/*
 * CLASS DESCRIPTION
 * 
 * Has
 * 			- Name 		: Name of the catalog
 * 			- CatalogID	: Catalog unique ID
 */
public class Catalog {
	private String Name;
	private int CatalogID;
	
	//GETTERS AND SETTERS
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCatalogID() {
		return CatalogID;
	}
	public void setCatalogID(int catalogID) {
		CatalogID = catalogID;
	}
	
}
