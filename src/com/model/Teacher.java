package com.model;

public class Teacher {
	
	//分页用  每页分的条数
		public static final int PAGE_SIZE = 10;
	private Integer id;
	private String  username;
	private String  password;
	private String  name;
	private String sex;
	private String add_date;
	private String photo;
	private int  relo;
	private String tel;
    private String startDate; //查询时的开始日期
	private String endDate;    //查询时的结束日期
	
	  //修改密码用
		private String ypassword;//原密码
		private String xpassword;//新密码
		private String cfpassword;//重复密码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getRelo() {
		return relo;
	}
	public void setRelo(int relo) {
		this.relo = relo;
	}
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	
	public String getYpassword() {
		return ypassword;
	}
	public void setYpassword(String ypassword) {
		this.ypassword = ypassword;
	}
	public String getXpassword() {
		return xpassword;
	}
	public void setXpassword(String xpassword) {
		this.xpassword = xpassword;
	}
	public String getCfpassword() {
		return cfpassword;
	}
	public void setCfpassword(String cfpassword) {
		this.cfpassword = cfpassword;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", sex=" + sex + ", add_date="
				+ add_date + ", photo=" + photo + ", relo=" + relo + ", tel="
				+ tel + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
	

}
