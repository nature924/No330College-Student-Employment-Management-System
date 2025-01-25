package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Employment;
import com.util.ConnDB;

public class EmploymentDao {

	public List<Employment> list(Employment qemployment){
		//查询条件
		String Name = qemployment.getName();  //公司名称
		String startDate = qemployment.getStartDate(); //起始日期
		String endDate = qemployment.getEndDate();     //结束日期
		String address = qemployment.getAddress();//就业地址
		String company = qemployment.getCompany();
		String username = qemployment.getUsername();//学号
		List<Employment> list = new ArrayList<Employment>();
		if((Name != null && !Name.equals(""))||(startDate != null && !startDate.equals(""))
				||(endDate != null && !endDate.equals(""))||((address != null && !address.equals("")))
				||((company != null && !company.equals("")))||((username != null && !username.equals("")))	
				){
		String sql = "select id,name,photo,salary,company,address,username,add_Date from employment_information " +
					 "where 1=1 ";
		//如果name有值ֵ
		if(Name != null && !Name.equals("")){
			sql += "and name like '" + Name + "%' ORDER BY add_Date DESC ";
		}
		if(address != null && !address.equals("")){
			sql += "and address like '" + address + "%'ORDER BY add_Date DESC ";
		}
		if(username != null && !username.equals("")){
			sql += "and username like '" + username + "'ORDER BY add_Date DESC ";
		}
		if(company != null && !company.equals("")){
			sql += "and company like '%" + company + "%'ORDER BY add_Date DESC ";
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
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给employment对象的属性
				Employment employment = new Employment();
				employment.setId(rs.getInt("id"));
				employment.setName(rs.getString("name"));
				employment.setPhoto(rs.getString("photo"));
				employment.setSalary(rs.getDouble("salary"));
				employment.setCompany(rs.getString("company"));
				employment.setAddress(rs.getString("address"));
				employment.setAddDate(rs.getString("add_date"));
				employment.setUsername(rs.getString("username"));
				//把每一个对象放入list
				list.add(employment);
				
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
	 * @param employment 需要添加的数据
	 */
	public void add(Employment employment){
		Connection conn = ConnDB.getConn();
		String sql = "insert into employment_information(name,photo,salary,company,username,address,add_Date ) " +
				     "values(?,?,?,?,?,?,now())";
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, employment.getName());
			pst.setString(2, employment.getPhoto());
			pst.setDouble(3, employment.getSalary());
			pst.setString(4, employment.getCompany());
			pst.setString(5, employment.getUsername());
			pst.setString(6, employment.getAddress());
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
	public Employment getEmploymentById(Integer id){
		Employment employment = new Employment();
		Connection conn = ConnDB.getConn();
		String sql = "select id,name,photo,salary,company,address,username,add_Date from employment_information where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				employment.setId(rs.getInt("id"));
				employment.setName(rs.getString("name"));
				employment.setPhoto(rs.getString("photo"));
				employment.setSalary(rs.getDouble("salary"));
				employment.setCompany(rs.getString("company"));
				employment.setAddress(rs.getString("address"));
				employment.setAddDate(rs.getString("add_date"));
				employment.setUsername(rs.getString("username"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employment;
	}
	
	
	/**
	 * 根据id删除
	 * @param id 主键
	 * @return
	 */
	public void del(Integer id){
		Connection conn = ConnDB.getConn();
		String sql = "delete from employment_information where id=?";
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
	 * 修改  根据主键修改
	 * @param employment
	 */
	public void update(Employment employment){
		Connection conn = ConnDB.getConn();
		String sql = "update employment_information set name=?,photo=?,salary=?,company=?,address=?,username=?,description=?where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, employment.getName());
			pst.setString(2, employment.getPhoto());
			pst.setDouble(3, employment.getSalary());
			pst.setString(4, employment.getCompany());
			pst.setString(5, employment.getAddress());
			pst.setString(6, employment.getUsername());
			pst.setString(7, employment.getDescription());
			pst.setInt(8, employment.getId());
		
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * admin用
	 * @return
	 */
	public  List<Employment> getEmploymentAdmin(){
		List<Employment> list = new ArrayList<Employment>();
		Connection conn = ConnDB.getConn();
		String sql = "SELECT address,COUNT(*) FROM  employment_information GROUP BY address ORDER BY COUNT(*) DESC";
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				Employment employment = new Employment();
				employment.setAddress(rs.getString(1));
				employment.setCount(rs.getInt(2));
				
				list.add(employment);
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
	
 public List<Employment> find(int page){
         
         //创建List
         List<Employment> list = new ArrayList<Employment>();
         //获取数据库连接
         Connection conn = ConnDB.getConn();
         //分页查询的SQL语句
         String sql = "SELECT a.* FROM employment_information a INNER JOIN (SELECT MAX(add_Date) DATE,username "
         		+ "FROM employment_information GROUP BY username) b ON a.add_Date = b.date AND b.username = "
         		+ "a.username ORDER BY salary DESC LIMIT ?,? ";
         try {
             //获取PreparedStatement
             PreparedStatement ps = conn.prepareStatement(sql);
             //SQL语句中的第1个参数赋值
             ps.setInt(1, (page - 1) * Employment.PAGE_SIZE);
             //对SQL语句中的第2个参数赋值
             ps.setInt(2, Employment.PAGE_SIZE);
             //执行查询操作
             ResultSet rs = ps.executeQuery();
             //光标向后移动，并判断是否有效
            while(rs.next()) {
                 //实例化Product
            	Employment employment= new Employment();      
            	employment.setId(rs.getInt("id"));
				employment.setName(rs.getString("name"));
				employment.setPhoto(rs.getString("photo"));
				employment.setSalary(rs.getDouble("salary"));
				employment.setCompany(rs.getString("company"));
				employment.setAddress(rs.getString("address"));
				employment.setAddDate(rs.getString("add_date"));
				employment.setUsername(rs.getString("username"));
                 //将Product添加到List结合中
                 list.add(employment);
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
         String sql = "SELECT COUNT(id) FROM  (SELECT  a.* FROM employment_information a INNER JOIN "
         		+ "(SELECT MAX(add_Date) DATE,username FROM employment_information GROUP BY username ) "
         		+ "b ON a.add_Date = b.date AND b.username = a.username ) c";
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

    
    
	/**
	 * 根据id取记录
	 * @param  name 主键
	 * @return
	 */
	public List<Employment> getEmploymentByName(String username){
		List<Employment> list = new ArrayList<Employment>();
		
		String sql = "SELECT id,NAME,photo,salary,company,address,username,add_Date FROM employment_information"
				+ " WHERE 1=1 AND username LIKE '"+username+"'ORDER BY add_Date DESC ";
		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			 ResultSet rs = pst.executeQuery();
			 while(rs.next()){
				Employment employment = new Employment();
				employment.setId(rs.getInt("id"));
				employment.setName(rs.getString("name"));
				employment.setPhoto(rs.getString("photo"));
				employment.setSalary(rs.getDouble("salary"));
				employment.setCompany(rs.getString("company"));
				employment.setAddress(rs.getString("address"));
				employment.setAddDate(rs.getString("add_date"));
				employment.setUsername(rs.getString("username"));
				//把每一个对象放入list
				list.add(employment);
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
	public Employment getEmploymentselect(String username){
		Employment employment = new Employment();
		Connection conn = ConnDB.getConn();
		String sql = "SELECT id,NAME,photo,salary,company,address,username,add_Date FROM employment_information"
				+ " WHERE 1=1 AND username LIKE '"+username+"'ORDER BY add_Date DESC ";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				employment.setId(rs.getInt("id"));
				employment.setName(rs.getString("name"));
				employment.setPhoto(rs.getString("photo"));
				employment.setUsername(rs.getString("username"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employment;
	}
}
