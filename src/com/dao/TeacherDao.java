package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Teacher;
import com.util.ConnDB;

public class TeacherDao {
	
	public List<Teacher> list(Teacher qTeacher){
		//查询条件
				String Name = qTeacher.getName();   //教师名称
				String startDate = qTeacher.getStartDate(); //起始日期
				String endDate = qTeacher.getEndDate();      //结束日期
		List<Teacher> list = new ArrayList<Teacher>();
		
		
		if((Name != null && !Name.equals(""))||(startDate != null && !startDate.equals(""))
				||(endDate != null && !endDate.equals(""))){
			String sql = "select id,username,password,name,tel,sex,add_date,photo from teacher_info " +
					 "where 1=1 ";
		
		
		//如果name有值ֵ
		if(Name != null && !Name.equals("")){
			sql += "and name like '%" + Name + "%' ";
		}
		if(startDate != null && !startDate.equals("")){
			sql += "and add_date>='" + startDate + "' ";
		}
		if(endDate != null && !endDate.equals("")){
			endDate += " 23:59:59";
			sql += "and add_date<='" + endDate + "' ";
		}

		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			//pst.setString(1, qCompany.getName()+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给userInfo对象的属性
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setUsername(rs.getString("username"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				teacher.setSex(rs.getString("sex"));
				teacher.setTel(rs.getString("tel"));
				teacher.setPhoto(rs.getString("photo"));
				teacher.setAdd_date(rs.getString("add_date"));
				//把每一个对象放入list
				list.add(teacher);
				
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
		return null;
	}
	
	/**
	 * 新增
	 * @param company 需要添加的数据
	 */
	public void add(Teacher teacher){
		Connection conn = ConnDB.getConn();
		String sql = "insert into teacher_info(username,password,name,tel,sex,photo,add_date) " +
				     "values(?,?,?,?,?,?,now())";
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, teacher.getUsername());
			pst.setString(2, teacher.getPassword());
			pst.setString(3, teacher.getName());
			pst.setString(4, teacher.getTel());
			pst.setString(5, teacher.getSex());
			pst.setString(6,teacher.getPhoto());
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
	public Teacher getTeacherById(Integer id){
		Teacher teacher = new Teacher();
		Connection conn = ConnDB.getConn();
		String sql = "select id,username,password,name,tel,sex,photo,add_date from teacher_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				teacher.setId(rs.getInt("id"));
				teacher.setUsername(rs.getString("username"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				teacher.setTel(rs.getString("tel"));
				teacher.setSex(rs.getString("sex"));
				teacher.setPhoto(rs.getString("photo"));
				teacher.setAdd_date(rs.getString("add_date"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
	/**
	 * 登录用
	 * 根据用户名取记录
	 * @param id 主键
	 * @return
	 */
	public Teacher getTeacherByUsername(String username){
		Teacher teacher = null;
		Connection conn = ConnDB.getConn();
		String sql = "select id,username,password,name,tel,sex,photo,add_date,relo from teacher_info where username=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setUsername(rs.getString("username"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				teacher.setTel(rs.getString("tel"));
				teacher.setSex(rs.getString("sex"));
				teacher.setPhoto(rs.getString("photo"));
				teacher.setAdd_date(rs.getString("add_date"));
				teacher.setRelo(rs.getInt("relo"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
	
	
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 */
	public void del(Integer id){
		Connection conn = ConnDB.getConn();
		String sql = "delete from teacher_info where id=?";
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
	 * 修改
	 * @param  teacher
	 */
	public void update(Teacher teacher){
		Connection conn = ConnDB.getConn();
		String sql = "update teacher_info set password=?,name=?,tel=?,sex=?,photo=? where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, teacher.getPassword());
			pst.setString(2, teacher.getName());
			pst.setString(3, teacher.getTel());
			pst.setString(4, teacher.getSex());
			pst.setString(5, teacher.getPhoto());
			pst.setInt(6, teacher.getId());
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
	public void updatepassword(Teacher teacher){
		
		Connection conn = ConnDB.getConn();
		String sql = "update teacher_info set password=?where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, teacher.getXpassword());
			pst.setInt(2, teacher.getId());
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据主键取密码
	 * @param id 主键
	 * @return
	 */
	public Teacher getTeacherByIdtopassword(Integer id){
		Teacher teacher = new Teacher();
		Connection conn = ConnDB.getConn();
		String sql = "select id,password from teacher_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				teacher.setId(rs.getInt("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setYpassword("");
				teacher.setXpassword("");
				teacher.setCfpassword("");
				System.out.println(teacher);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
	
	
	public List<Teacher> find(int page){
        
        //创建List
        List<Teacher> list = new ArrayList<Teacher>();
        //获取数据库连接
        Connection conn = ConnDB.getConn();
        //分页查询的SQL语句
        String sql = "select * from teacher_info limit ?,? ";
        try {
            //获取PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            //SQL语句中的第1个参数赋值
            ps.setInt(1, (page - 1) * Teacher.PAGE_SIZE);
            //对SQL语句中的第2个参数赋值
            ps.setInt(2, Teacher.PAGE_SIZE);
            //执行查询操作
            ResultSet rs = ps.executeQuery();
            //光标向后移动，并判断是否有效
           while(rs.next()) {
                //实例化Product
        	   Teacher teacher = new Teacher();
        	   teacher.setId(rs.getInt("id"));
				teacher.setUsername(rs.getString("username"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				teacher.setTel(rs.getString("tel"));
				teacher.setSex(rs.getString("sex"));
				teacher.setPhoto(rs.getString("photo"));
				teacher.setAdd_date(rs.getString("add_date"));
				//把每一个对象放入list
				list.add(teacher);
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
        String sql = "select count(*) from teacher_info";
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
