package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Issue;
import com.util.ConnDB;

public class IssueDao {
/**
 * 查询
 * @param qissue
 * @return
 */
	public List<Issue> list(Issue qissue){
		//查询条件
		
				String name = qissue.getQuestions();   //名字
				String qcontent = qissue.getQcontent();//反馈内容
				String username = qissue.getUsername();//反馈学号
				String startDate = qissue.getStartDate(); //起始日期
				String endDate = qissue.getEndDate();     //结束日期
		List<Issue> list = new ArrayList<Issue>();
		
		if((name != null && !name.equals(""))||(startDate != null && !startDate.equals(""))
				||(endDate != null && !endDate.equals(""))||(qcontent != null && !qcontent.equals(""))
				||(username != null && !username.equals(""))){
		
		String sql = "select id,username,questions,qcontent,qdate,reply,replier,rcontent,rdate from issue_info " +
					 "where 1=1 ";
		
		
		
		//如果startDate endDate有值
		if(name != null && !name.equals("")){
			sql += "and questions like '" + name + "%' ";
		}
		if(qcontent != null && !qcontent.equals("")){
			sql += "and qcontent like '%" + qcontent + "%' ";
		}
		if(username != null && !username.equals("")){
			sql += "and username like '" + username + "' ";
		}
		if(startDate != null && !startDate.equals("")){
			sql += "and qdate>='" + startDate + "' ";
		}
		if(endDate != null && !endDate.equals("")){
			endDate += " 23:59:59";
			sql += "and qdate<='" + endDate + "' ";
		}
		System.out.println(sql);
		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给graduate对象的属性
				Issue issue = new Issue();
				issue.setId(rs.getInt("id"));
				issue.setQuestions(rs.getString("questions"));
				issue.setUsername(rs.getString("username"));
				issue.setQcontent(rs.getString("qcontent"));
				issue.setQdate(rs.getString("qdate"));
			    issue.setReply(rs.getString("reply"));//回复状态
				issue.setReplier(rs.getString("replier"));
				issue.setRcontent(rs.getString("rcontent"));
				issue.setRdate(rs.getString("rdate"));
				//把每一个对象放入list
				list.add(issue);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;}
		return null;
	}

	/**
	 * 根据主键取记录
	 * @param id 主键
	 * @return
	 */
	public Issue getIssueById(Integer id){
		Issue issue = new Issue();
		Connection conn = ConnDB.getConn();
		String sql = "select id,questions,qcontent,qdate,reply,replier,rcontent,rdate from issue_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				issue.setId(rs.getInt("id"));
				issue.setQuestions(rs.getString("questions"));
				issue.setQcontent(rs.getString("qcontent"));
				issue.setQdate(rs.getString("qdate"));
			    issue.setReply(rs.getString("reply"));//回复状态
				issue.setReplier(rs.getString("replier"));
				issue.setRcontent(rs.getString("rcontent"));
				issue.setRdate(rs.getString("rdate"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issue;
	}

	/**
	 * 回复
	 * @param issue
	 */
	public void update(Issue issue){
		Connection conn = ConnDB.getConn();
		String sql = "update issue_info set reply=1,replier=?, rcontent=?,rdate=now() where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, issue.getReplier());
			pst.setString(2, issue.getRcontent());
			pst.setInt(3, issue.getId());
			System.out.println(sql);
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 */
	public void del(Integer id){
		Connection conn = ConnDB.getConn();
		String sql = "delete from issue_info where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 新增
	 * @param userInfo 需要添加的数据
	 */
	public void add(Issue issue){
		Connection conn = ConnDB.getConn();
		String sql = "insert into issue_info(username,questions,qcontent,qdate)values(?,?,?,now())";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, issue.getUsername());
			pst.setString(2, issue.getQuestions());
			pst.setString(3, issue.getQcontent());
			//增册改都是执行executeUpdate方法
			pst.executeUpdate();
			
			pst.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Issue> find(int page){
        
        //创建List
        List<Issue> list = new ArrayList<Issue>();
        //获取数据库连接
        Connection conn = ConnDB.getConn();
        //分页查询的SQL语句
        String sql = "select * from issue_info   ORDER BY qdate DESC   limit ?,? ";
        try {
            //获取PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            //SQL语句中的第1个参数赋值
            ps.setInt(1, (page - 1) * Issue.PAGE_SIZE);
            //对SQL语句中的第2个参数赋值
            ps.setInt(2, Issue.PAGE_SIZE);
            //执行查询操作
            ResultSet rs = ps.executeQuery();
            //光标向后移动，并判断是否有效
           while(rs.next()) {
                //实例化Product
        	   Issue issue = new Issue();
        	   issue.setId(rs.getInt("id"));
				issue.setQuestions(rs.getString("questions"));
				issue.setUsername(rs.getString("username"));
				issue.setQcontent(rs.getString("qcontent"));
				issue.setQdate(rs.getString("qdate"));
			    issue.setReply(rs.getString("reply"));//回复状态
				issue.setReplier(rs.getString("replier"));
				issue.setRcontent(rs.getString("rcontent"));
				issue.setRdate(rs.getString("rdate"));
				//把每一个对象放入list
				list.add(issue);
            }
            //关闭ResultSet
            rs.close();
            //关闭PreparedStatement
            ps.close();
            //关闭Connection
            conn.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
        
    }
    /**
     * 查询记录总数
     * @return count记录数
     */
   public int findCount() {
        //总记录数
        int count = 0;
        //获取数据库连接
        Connection conn = ConnDB.getConn();
        //查询总记录数SQL语句
        String sql = "select count(*) from issue_info";
        try {
           //创建Statement
            Statement stmt = conn.createStatement();
            //查询并获取ResultSet
            ResultSet rs = stmt.executeQuery(sql);
            //光标向后移动，并判断是否有效
            if(rs.next()) {
               //取出id字段的值对总记录赋值
                count = rs.getInt(1);
            }
            //关闭ResultSet
            rs.close();
            //关闭Connection
            conn.close();
       }catch(SQLException e) {
            e.printStackTrace();
        }
       //返回总记录
        return count;
    }
	
}
