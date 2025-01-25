package com.model;
/**
 * 公司 
 * @author 小二
 *
 */

public class Company {
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String funds;//注册资金
	private String tel;
	private String address;
	private String scale;//规模
	private String industry;//类型
	private String description;//简介
	private String photo;
	private String website;//网站
	private Integer relo;
	
	

	public Integer getRelo() {
		return relo;
	}

	public void setRelo(Integer relo) {
		this.relo = relo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	private String addDate;
	
	private String startDate; //查询时的开始日期
	
	private String endDate;   //查询时的结束日期

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getFunds() {
		return funds;
	}

	public void setFunds(String funds) {
		this.funds = funds;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", funds=" + funds + ", tel="
				+ tel + ", address=" + address + ", scale=" + scale
				+ ", industry=" + industry + ", description=" + description
				+ ", photo=" + photo + ", website=" + website + ", relo="
				+ relo + ", addDate=" + addDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	



}
