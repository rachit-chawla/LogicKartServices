package org.gl.project.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductImages {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int product_Image_Id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private String product_Image_Path;

	public int getProduct_Image_Id() {
		return product_Image_Id;
	}

	public void setProduct_Image_Id(int product_Image_Id) {
		this.product_Image_Id = product_Image_Id;
	}

	public String getProduct_Image_Path() {
		return product_Image_Path;
	}

	public void setProduct_Image_Path(String product_Image_Path) {
		this.product_Image_Path = product_Image_Path;
	}
}