package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Post;
import com.util.ConnDB;

public class PostDao {

	public List<Post> list(Post qpost){
		List<Post> list = new ArrayList<Post>();
		String sql = "select id,name,demand_number,sex,education,salary,workplace," +
				"description,add_date from post_info where 1=1 ";
		
		//查询条件
		String name = qpost.getName();   //用户名
		String startDate = qpost.getStartDate();  //起始日期
		String endDate = qpost.getEndDate();     //结束日期
		
		//如果name有值ֵ
		if(name != null && !name.equals("")){
			sql += "and name like '" + name + "%' ";
		}
		if(startDate != null && !startDate.equals("")){
			sql += "and add_date>='" + startDate + "' ";
		}
		if(endDate != null && !endDate.equals("")){
			endDate += " 23:59:59";
			sql += "and add_date<='" + endDate + "' ";
		}
		System.out.println(sql);
		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			//pst.setString(1, qUserInfo.getUsername()+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给post对象的属性
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("name"));
				post.setDemand_number(rs.getInt("demand_number"));
				post.setSex(rs.getString("sex"));
				post.setEducation(rs.getString("education"));
				post.setSalary(rs.getFloat("salary"));
				post.setWorkplace(rs.getString("workplace"));
				post.setDescription(rs.getString("description"));
				post.setAdd_date(rs.getString("add_date"));
				
				//把每一个对象放入list
				list.add(post);
				
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<Post> list(int num){
		List<Post> list = new ArrayList<Post>();
		String sql = "select id,name,demand_number,sex,education,salary,workplace," +
				"description,add_date from post_info " +"order by add_date desc limit " + num;					
		
		
		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			//pst.setString(1, qUserInfo.getUsername()+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){//遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给userInfo对象的属性
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("name"));
				post.setDemand_number(rs.getInt("demand_number"));
				post.setSex(rs.getString("sex"));
				post.setEducation(rs.getString("education"));
				post.setSalary(rs.getFloat("salary"));
				post.setWorkplace(rs.getString("workplace"));
				post.setDescription(rs.getString("description"));
				post.setAdd_date(rs.getString("add_date"));
				//把每一个对象放入list
				list.add(post);
				
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 新增
	 * @param userInfo 需要添加的数据
	 */
	public void add(Post post){
		Connection conn = ConnDB.getConn();
		String sql = "insert into post_info(id,name,demand_number,sex,education,salary,workplace,description,add_date)" +
				" values(?,?,?,?,?,?,?,?,now())";
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, post.getId());
			pst.setString(2, post.getName());
			pst.setInt(3, post.getDemand_number());
			pst.setString(4, post.getSex());
			pst.setString(5, post.getEducation());
			pst.setFloat(6, post.getSalary());
			pst.setString(7, post.getWorkplace());
			pst.setString(8, post.getDescription());
			
			//增册改都是执行executeUpdate方法
			pst.executeUpdate();
			
			pst.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据主键取记录
	 * @param id 主键
	 * @return
	 */
	public Post getPostById(Integer id){
		Post post = new Post();
		Connection conn = ConnDB.getConn();
		String sql = "select id,name,demand_number,sex,education,salary,workplace," +
				"description,add_date from post_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("name"));
				post.setDemand_number(rs.getInt("demand_number"));
				post.setSex(rs.getString("sex"));
				post.setEducation(rs.getString("education"));
				post.setSalary(rs.getFloat("salary"));
				post.setWorkplace(rs.getString("workplace"));
				post.setDescription(rs.getString("description"));
				post.setAdd_date(rs.getString("add_date"));
				
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}
	
	/**
	 * 根据用户名取记录
	 * @param id 主键
	 * @return
	 */
	public Post getPostByName(String name){
		Post post = null;
		Connection conn = ConnDB.getConn();
		String sql = "select id, name,demand_number,sex,education,salary,workplace," +
				"description,add_date from post_info where name=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				post = new Post();
				post.setId(rs.getInt("id"));
				post.setName(rs.getString("name"));
				post.setDemand_number(rs.getInt("demand_number"));
				post.setSex(rs.getString("sex"));
				post.setEducation(rs.getString("education"));
				post.setSalary(rs.getFloat("salary"));
				post.setWorkplace(rs.getString("workplace"));
				post.setDescription(rs.getString("description"));
				post.setAdd_date(rs.getString("add_date"));
				
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 */
	public void del(Integer id){
		Connection conn = ConnDB.getConn();
		String sql = "delete from post_info where id=?";
		PreparedStatement pst;
		System.out.println("删除成功");
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
	 * 修改
	 * @param userInfo
	 */
	public void update(Post post){
		Connection conn = ConnDB.getConn();
		String sql = "update post_info setname=?,demand_number=?,sex=?,education=?,salary=?,workplace=?," +
				"description=?,add_date=? where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, post.getName());
			pst.setInt(2, post.getDemand_number());
			pst.setString(3, post.getSex());
			pst.setString(4, post.getEducation());
			pst.setFloat(5, post.getSalary());
			pst.setString(6, post.getWorkplace());
			pst.setString(7, post.getDescription());
			pst.setString(8,post.getAdd_date());
			pst.setInt(9, post.getId());
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		PostDao ud = new PostDao();
		Post post = ud.getPostByName("admin1");
		System.out.println("post"+post);
	}
}
