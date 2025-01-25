package com.model;

/**
 * 
 * 就业实体类
 * 
 */
public class Employment {
	private int id;
	private String name;
	private String photo;
	private double salary;// 薪资
	private String company;// 公司
	private String address;//就业地址
	private String description;//学历
	private String username;//学号
    private Integer count;
    public static final int PAGE_SIZE = 10;
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	} 
	private String addDate;
	
	private String startDate; //查询时的开始日期
	
	private String endDate;   //查询时的结束日期

	public String getAddDate() {
		return addDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Employment [id=" + id + ", name=" + name + ", photo=" + photo
				+ ", salary=" + salary + ", company=" + company + ", address="
				+ address + ", count=" + count + ", addDate=" + addDate
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}


	
}
