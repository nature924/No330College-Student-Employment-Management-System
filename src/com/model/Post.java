package com.model;

public class Post {
    
	private int id;
	
	private int company_id;
	
	private String name;
	
	private int demand_number;
	private String sex;
	private String education;
	private float salary;
	private String workplace;
	private String description;
	private String add_date;
	private String startDate;
	private String endDate;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDemand_number() {
		return demand_number;
	}
	public void setDemand_number(int demand_number) {
		this.demand_number = demand_number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", company_id=" + company_id + ", name="
				+ name + ", demand_number=" + demand_number + ", sex=" + sex + ",education=" +education+",salary="
				+ salary +",workplace = "+workplace+",description = "+description+",add_date ="+add_date+"]";
		}
	
}
