package com.model;

/**
 * 用户表对应的实体类
 * @author Administrator
 *
 */
public class Graduate {
	//分页用  每页分的条数
	public static final int PAGE_SIZE = 10;
	
	
    private String username;//学号
    
    private String password;
    
	private String name;
	
	private String sex;
	
	private String university;//院系
	
	private String address;//地址
	
	private String tel;
	
	private String description;//学历
	
	private String photo;
	
	private String addDate;//添加时间
	
	private int relo;
	
	private String startDate; //查询时的开始日期
	
	private String endDate;   //查询时的结束日期
	
	private String  graduateyear;//查询毕业年份

	private String nation;//民族
	
	private String source;// 来源地
	
	private String major;//专业
	
	private String aspec; //政治面貌
	
	private String status;//就业情况
	
	private String salary;// 薪资
	
	private String company;// 公司

	
	//修改密码用
		private String ypassword;//原密码
		private String xpassword;//新密码
		private String cfpassword;//重复密码

	
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAspec() {
		return aspec;
	}

	public void setAspec(String aspec) {
		this.aspec = aspec;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public int getRelo() {
		return relo;
	}

	public void setRelo(int relo) {
		this.relo = relo;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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





	public String getGraduateyear() {
		return graduateyear;
	}

	public void setGraduateyear(String graduateyear) {
		this.graduateyear = graduateyear;
	}

	@Override
	public String toString() {
		return "Graduate [ username=" + username + ", password="
				+ password + ", name=" + name + ", sex=" + sex
				+ ", university=" + university + ", address=" + address
				+ ", tel=" + tel + ", description=" + description + ", photo="
				+ photo + ", addDate=" + addDate + ", relo=" + relo
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", nation=" + nation + ", source=" + source + ", major="
				+ major + ", aspec=" + aspec + ", status=" + status
				+ ", salary=" + salary + ", company=" + company + "]";
	}



}
