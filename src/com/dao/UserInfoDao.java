package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.UserInfo;
import com.util.ConnDB;

/**
 * 用户模块的数据库操作类
 * @author Administrator
 *
 */
public class UserInfoDao {

	
	
	
	public List<UserInfo> list(int num){
		List<UserInfo> list = new ArrayList<UserInfo>();
		String sql = "select id,username,password,name,sex,photo,add_date from user_info " +
				"order by add_date desc limit " + num;					
		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给userInfo对象的属性
				UserInfo userInfo = new UserInfo();
				userInfo.setId(rs.getInt("id"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setName(rs.getString("name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setPhoto(rs.getString("photo"));
				userInfo.setAddDate(rs.getString("add_date"));
				
				//把每一个对象放入list
				list.add(userInfo);
				
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
	 * 根据主键取记录
	 * @param id 主键
	 * @return
	 */
	public UserInfo getUserInfoById(Integer id){
		UserInfo userInfo = new UserInfo();
		Connection conn = ConnDB.getConn();
		String sql = "select id,username,password,name,sex,add_date,photo from user_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				userInfo.setId(rs.getInt("id"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setName(rs.getString("name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setAddDate(rs.getString("add_date"));
				userInfo.setPhoto(rs.getString("photo"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}
	/**
	 * 根据主键取密码
	 * @param id 主键
	 * @return
	 */
	public UserInfo getUserInfoByIdtopassword(Integer id){
		UserInfo userInfo = new UserInfo();
		Connection conn = ConnDB.getConn();
		String sql = "select id,password from user_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				userInfo.setId(rs.getInt("id"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setYpassword("");
				userInfo.setXpassword("");
				userInfo.setCfpassword("");
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}
	/**
	 * 根据用户名取记录
	 * @param id 主键
	 * @return
	 */
	public UserInfo getUserInfoByUsername(String username){
		UserInfo userInfo = null;
		Connection conn = ConnDB.getConn();
		String sql = "select id,username,password,name,sex,add_date, photo,relo from user_info where username=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				userInfo = new UserInfo();
				userInfo.setId(rs.getInt("id"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setName(rs.getString("name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setAddDate(rs.getString("add_date"));
				userInfo.setPhoto(rs.getString("photo"));
				userInfo.setRelo(rs.getInt("relo"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}
	
	
	
	/**
	 * 修改
	 * @param userInfo
	 */
	public void update(UserInfo userInfo){
		Connection conn = ConnDB.getConn();
		String sql = "update user_info set name=?,sex=?,photo=? where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userInfo.getName());
			pst.setString(2, userInfo.getSex());
			pst.setString(3,userInfo.getPhoto());
			pst.setInt(4, userInfo.getId());
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改密码查询
	 * @param userInfo
	 */
	public void updatepassword(UserInfo userInfo){
		
		Connection conn = ConnDB.getConn();
		String sql = "update user_info set password=?where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userInfo.getXpassword());
			pst.setInt(2, userInfo.getId());
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args){
		UserInfoDao ud = new UserInfoDao();
		UserInfo userinfo = ud.getUserInfoByUsername("admin1");
		System.out.println("输入userinfo的"+userinfo);
	}
}
