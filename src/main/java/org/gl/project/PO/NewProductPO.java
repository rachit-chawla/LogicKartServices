package org.gl.project.PO;

import java.io.Serializable;

public class NewProductPO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private Integer productPrice;
	private Integer productQuantity;
	private String productType;
	private String book_Author;
	private String book_Description;
	private String book_Publisher;
	private Integer book_Publishing_Year;
	private String opticalMediaType;
	private String opticalMediaCategory;
	private String opticalMediaDescription;
	private String magazine_Author;
	private String magazine_Description;
	private String magazine_Publisher;
	private Integer magazine_Publishing_Year;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBook_Author() {
		return book_Author;
	}
	public void setBook_Author(String book_Author) {
		this.book_Author = book_Author;
	}
	public String getBook_Description() {
		return book_Description;
	}
	public void setBook_Description(String book_Description) {
		this.book_Description = book_Description;
	}
	public String getBook_Publisher() {
		return book_Publisher;
	}
	public void setBook_Publisher(String book_Publisher) {
		this.book_Publisher = book_Publisher;
	}
	public Integer getBook_Publishing_Year() {
		return book_Publishing_Year;
	}
	public void setBook_Publishing_Year(Integer book_Publishing_Year) {
		this.book_Publishing_Year = book_Publishing_Year;
	}
	public String getOpticalMediaType() {
		return opticalMediaType;
	}
	public void setOpticalMediaType(String opticalMediaType) {
		this.opticalMediaType = opticalMediaType;
	}
	public String getOpticalMediaCategory() {
		return opticalMediaCategory;
	}
	public void setOpticalMediaCategory(String opticalMediaCategory) {
		this.opticalMediaCategory = opticalMediaCategory;
	}
	public String getOpticalMediaDescription() {
		return opticalMediaDescription;
	}
	public void setOpticalMediaDescription(String opticalMediaDescription) {
		this.opticalMediaDescription = opticalMediaDescription;
	}
	public String getMagazine_Author() {
		return magazine_Author;
	}
	public void setMagazine_Author(String magazine_Author) {
		this.magazine_Author = magazine_Author;
	}
	public String getMagazine_Description() {
		return magazine_Description;
	}
	public void setMagazine_Description(String magazine_Description) {
		this.magazine_Description = magazine_Description;
	}
	public String getMagazine_Publisher() {
		return magazine_Publisher;
	}
	public void setMagazine_Publisher(String magazine_Publisher) {
		this.magazine_Publisher = magazine_Publisher;
	}
	public Integer getMagazine_Publishing_Year() {
		return magazine_Publishing_Year;
	}
	public void setMagazine_Publishing_Year(Integer magazine_Publishing_Year) {
		this.magazine_Publishing_Year = magazine_Publishing_Year;
	}
}