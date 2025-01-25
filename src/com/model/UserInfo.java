package com.model;

/**
 * �û����Ӧ��ʵ����
 * @author Administrator
 *
 */
public class UserInfo {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String sex;
	
	private String addDate;
	
	private String photo;
	
	private int relo;
	
	private String startDate; //查询时的开始日期
	
	private String endDate;    //查询时的结束日期

    //修改密码用
	private String ypassword;//原密码
	private String xpassword;//新密码
	private String cfpassword;//重复密码


	public int getRelo() {
		return relo;
	}

	public void setRelo(int relo) {
		this.relo = relo;
	}

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

	
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
		return "UserInfo [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", sex=" + sex + ", addDate="
				+ addDate + ", photo=" + photo + ", relo=" + relo
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}