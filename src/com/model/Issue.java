package com.model;

public class Issue {
	public static final int PAGE_SIZE = 10;
	private int id;
	
	private String username;//反馈者id
	
	private String questions;//反馈者
	
	private String qcontent;//反馈
	
	private String qdate;//反馈时间
	
	
    private String reply;//回复状态
	
	private String replier;//回复时间
	
	private String rcontent;//回复内容
	
	private String rdate;//回复时间
	
	private String startDate; //查询时的开始日期
	
	private String endDate;    //查询时的结束日期
	
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
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplier() {
		return replier;
	}
	public void setReplier(String replier) {
		this.replier = replier;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Issue [id=" + id + ", questions=" + questions + ", qcontent="
				+ qcontent + ", qdate=" + qdate + ", reply=" + reply
				+ ", replier=" + replier + ", rcontent=" + rcontent
				+ ", rdate=" + rdate + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}


	
}
