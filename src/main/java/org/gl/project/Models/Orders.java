package org.gl.project.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_ID")
	private User user;
	private Long TotalAmount;
	private String placingDate;
	private String deliveryDate;
	private String status;
	
	@OneToMany(mappedBy="order")
	private Set<OrderProduct> orderList = new HashSet<OrderProduct>();
	
	/*@ManyToMany
	@JoinTable(joinColumns=@JoinColumn(name="ORDER_ID"), inverseJoinColumns=@JoinColumn(name="PRODUCT_ID"))
	private Collection<Product> productList = new ArrayList<Product>();*/
	
	/*public Collection<OrderProduct> getOrderList() {
		return orderList;
	}
	public void setOrderList(Collection<OrderProduct> orderList) {
		this.orderList = orderList;
	}*/
	
	/*public Collection<Product> getProductList() {
		return productList;
	}
	public void setProductList(Collection<Product> productList) {
		this.productList = productList;
	}*/
	public Long getId() {
		return id;
	}
	/*public List<OrderProduct> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderProduct> orderList) {
		this.orderList = orderList;
	}*/
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public Set<OrderProduct> getOrderList() {
		return orderList;
	}


	public void setOrderList(Set<OrderProduct> orderList) {
		this.orderList = orderList;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		TotalAmount = totalAmount;
	}
	public String getPlacingDate() {
		return placingDate;
	}
	public void setPlacingDate(String placingDate) {
		this.placingDate = placingDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id +  ", TotalAmount="
				+ TotalAmount + ", placingDate=" + placingDate
				+ ", deliveryDate=" + deliveryDate + ", status=" + status + "]";
	}
}