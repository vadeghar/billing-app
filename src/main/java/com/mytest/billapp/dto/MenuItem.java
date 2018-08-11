package com.mytest.billapp.dto;

import java.util.List;

public class MenuItem implements Comparable<MenuItem>{
	
	private String name;
	private String link;
	private Integer order;
	private List<MenuItem> subMenuItems;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<MenuItem> getSubMenuItems() {
		return subMenuItems;
	}
	public void setSubMenuItems(List<MenuItem> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public int compareTo(MenuItem menuItem) {
		return this.order.compareTo(menuItem.getOrder());
	}

}
