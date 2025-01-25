package com.model;

public class News {
	public static final int PAGE_SIZE = 5;//分页用
	
	private int id;
	
	private String title;
	
	private String content;
	
	private String add_date;

    private String startDate; //查询时的开始日期
	
	private String endDate;   //查询时的结束日期
	
	private String photo;
	
	private String classify;//分类
	
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", add_date=" + add_date + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", photo=" + photo + "]";
	}

	
	
}
