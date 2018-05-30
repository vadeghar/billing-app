package com.mytest.billapp.view;

import java.util.List;

import com.mytest.billapp.model.Brand;
import com.mytest.billapp.model.Product;
import com.mytest.billapp.model.ProductItems;

public class ProductView extends BaseView {
	
	private Product product;
	private List<Product> productList;
	private ProductItems productItems;
	private List<ProductItems> productItemsList;
	private Long selectedItemId;
	private Brand brand;
	private String newBrnadName;
	private String brandName;
	private List<Brand> brandList;
	
	
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public ProductItems getProductItems() {
		return productItems;
	}
	public void setProductItems(ProductItems productItems) {
		this.productItems = productItems;
	}
	public List<ProductItems> getProductItemsList() {
		return productItemsList;
	}
	public void setProductItemsList(List<ProductItems> productItemsList) {
		this.productItemsList = productItemsList;
	}
	public Long getSelectedItemId() {
		return selectedItemId;
	}
	public void setSelectedItemId(Long selectedItemId) {
		this.selectedItemId = selectedItemId;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getNewBrnadName() {
		return newBrnadName;
	}
	public void setNewBrnadName(String newBrnadName) {
		this.newBrnadName = newBrnadName;
	}
	public List<Brand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
