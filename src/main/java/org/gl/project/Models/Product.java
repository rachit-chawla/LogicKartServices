package org.gl.project.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String productName;

	private Integer productPrice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<OrderProduct> productList = new HashSet<OrderProduct>();

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<ProductImages> productImages = new HashSet<ProductImages>();

	private Integer productQuantity;

	private String productType;

	// @ManyToOne
	// @JoinColumn(name="INVENTORY_ID")
	// private Inventory inventory;

	/*
	 * @ManyToMany(mappedBy="productList") private Collection<Orders> orderList
	 * = new ArrayList<Orders>();
	 */

	public String getProductType() {
		return productType;
	}

	public Set<ProductImages> getProductImages() {
		return productImages;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	/*
	 * public Collection<Orders> getOrderList() { return orderList; } public
	 * void setOrderList(Collection<Orders> orderList) { this.orderList =
	 * orderList; }
	 */
	/*
	 * public Collection<OrderProduct> getProductList() { return productList; }
	 * public void setProductList(Collection<OrderProduct> productList) {
	 * this.productList = productList; }
	 */

	// public Inventory getInventory() {
	// return inventory;
	// }
	// public void setInventory(Inventory inventory) {
	// this.inventory = inventory;
	// }

	public Category getCategory() {
		return category;
	}

	public Set<OrderProduct> getProductList() {
		return productList;
	}

	public void setProductList(Set<OrderProduct> productList) {
		this.productList = productList;
	}

	public void setProductImages(Set<ProductImages> productImages) {
		this.productImages = productImages;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Collection<ProductImages> getProductImages() { return
	 * productImages; } public void setProductImages(Collection<ProductImages>
	 * productImages) { this.productImages = productImages; }
	 */
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + "]";
	}
}
