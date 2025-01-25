package com.model;

public class Regsistr {

	

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String sex;
	
	private String addDate;
	
	private String photo;
	
	private String relo;


	public String getRelo() {
		return relo;
	}

	public void setRelo(String relo) {
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

	
	
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Regsistr [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", sex=" + sex + ", addDate="
				+ addDate + ", photo=" + photo + ", relo=" + relo + "]";
	}

	
}
