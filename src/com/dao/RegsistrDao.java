package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.UserInfo;
import com.util.ConnDB;

public class RegsistrDao {

	/**
	 * 新增
	 * @param userInfo 需要添加的数据
	 */
	public  void add(UserInfo userInfo){
		Connection conn = ConnDB.getConn();
		String sql = "insert into graduate_info(username,password,name,sex,photo,add_date) " +
			     "values(?,?,?,?,?,now())";
	try {
		
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, userInfo.getUsername());
		pst.setString(2, userInfo.getPassword());
		pst.setString(3, userInfo.getName());
		pst.setString(4, userInfo.getSex());
		pst.setString(5, userInfo.getPhoto());
		//增册改都是执行executeUpdate方法
		pst.executeUpdate();
		
		pst.close();
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
