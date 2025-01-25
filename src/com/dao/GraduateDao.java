package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Graduate;
import com.util.ConnDB;

/**
 * 用户模块的数据库操作类
 * @author Administrator
 *
 */
public class GraduateDao {

	public List<Graduate> list(Graduate qGraduate){
		//查询条件
				String name = qGraduate.getName();   //姓名
				String startDate = qGraduate.getStartDate(); //起始日期
				String endDate = qGraduate.getEndDate();     //结束日期
				String university = qGraduate.getUniversity();//院系
				String source = qGraduate.getSource();//生源地
				String status = qGraduate.getStatus();//就业情况
				String username = qGraduate.getUsername();//学号
				String graduateyear = qGraduate.getGraduateyear();//毕业年份
				String description = qGraduate.getDescription();
		List<Graduate> list = new ArrayList<Graduate>();
		//如果就业情况为空值
		
		if((name != null && !name.equals(""))||(startDate != null && !startDate.equals(""))
				||(endDate != null && !endDate.equals(""))||(university != null && !university.equals(""))
				||(source != null && !source.equals(""))||(status != null && !status.equals(""))
				||(username != null && !username.equals(""))||(graduateyear != null && !graduateyear.equals(""))
				||(description != null && !description.equals(""))){
		String sql = "select username,name,sex,university,source,tel,description,photo,graduateyear,status,add_date from graduate_info " +
					 "where 1=1 ";
		
		//学历
		if(description != null && !description.equals("")){
			sql += "and description like '" + description + "' ";
		}
		//就业情况
		if(status != null && !status.equals("")){
			sql += "and status like '" + status + "' ";
		}
		//学号
		if(username != null && !username.equals("")){
			sql += "and username like '" + username + "' ";
		}
		//毕业年份
		if(graduateyear != null && !graduateyear.equals("")){
			sql += "and graduateyear like '" + graduateyear + "' ";
		}
		if(name != null && !name.equals("")){
			sql += "and name like '%" + name + "%' ";
		}
		//院系
		if(university != null && !university.equals("")){
			sql += "and university like '" + university + "%' ";
		}
		//来源地
		if(source != null && !source.equals("")){
			sql += "and source like '" + source + "%' ";
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
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给graduate对象的属性
				Graduate graduate = new Graduate();
				graduate.setUsername(rs.getString("username"));
				graduate.setName(rs.getString("name"));
				graduate.setUniversity(rs.getString("university"));
				graduate.setSource(rs.getString("source"));
				graduate.setTel(rs.getString("tel"));
				graduate.setDescription(rs.getString("description"));
				graduate.setPhoto(rs.getString("photo"));
				graduate.setAddDate(rs.getString("add_date"));
				graduate.setStatus(rs.getString("status"));//就业情况
				graduate.setGraduateyear(rs.getString("graduateyear"));//毕业年份
				//把每一个对象放入list
				list.add(graduate);
				
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
	 * @param graduate 需要添加的数据
	 */
	public void add(Graduate graduate){
		Connection conn = ConnDB.getConn();
		String sql = "insert into graduate_info(username,password,name,graduateyear,sex,university,tel,description,photo,"
				+ "nation,source,major,aspect,status,company,salary,address,add_date) " +
				     "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, graduate.getUsername());
			pst.setString(2, graduate.getPassword());
			pst.setString(3, graduate.getName());
			if((graduate.getGraduateyear()).equals("未毕业")){
				pst.setString(4, graduate.getGraduateyear());
			}
			if(!(graduate.getGraduateyear()).equals("未毕业")){
				pst.setString(4, graduate.getGraduateyear());
			}
			pst.setString(5, graduate.getSex());
			pst.setString(6, graduate.getUniversity());
			pst.setString(7, graduate.getTel());
			pst.setString(8, graduate.getDescription());
			pst.setString(9, graduate.getPhoto());
			pst.setString(10, graduate.getNation());
			pst.setString(11, graduate.getSource());
			pst.setString(12, graduate.getMajor());
			pst.setString(13, graduate.getAspec());
			pst.setString(14, graduate.getStatus());
			//待业情况
			if(graduate.getStatus().equals("1")){
				pst.setString(15, null);
				pst.setString(16, null);
				pst.setString(17, null);
			}
			//就业情况
			if(graduate.getStatus().equals("0")){
				pst.setString(15, graduate.getCompany());
				pst.setString(16, graduate.getSalary());
				pst.setString(17, graduate.getAddress());
			}
			
			//增册改都是执行executeUpdate方法
			
			if(graduate.getStatus().equals("0")){
				Connection conn1 = ConnDB.getConn();
				String sql1 = "insert into employment_information(name,photo,salary,company,address,username,add_Date ) " +
					     "values(?,?,?,?,?,?,now())";
			try {
				
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, graduate.getName());
				pst1.setString(2, graduate.getPhoto());
				pst1.setString(3, graduate.getSalary());
				pst1.setString(4, graduate.getCompany());
				pst1.setString(5, graduate.getAddress());
				pst1.setString(6, graduate.getUsername());
				//增册改都是执行executeUpdate方法
				pst1.executeUpdate();
				pst1.close();
				conn1.close();
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
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
	public Graduate getGraduateById(String username){
		Graduate graduate = new Graduate();
		Connection conn = ConnDB.getConn();
		String sql = "select username,password,name,graduateyear,sex,university,tel,description,photo,"
				+ "nation,source,major,aspect,status,company,salary,address,add_date from graduate_info where username=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				graduate.setName(rs.getString("name"));
				graduate.setUsername(rs.getString("username"));
				graduate.setPassword(rs.getString("password"));
				graduate.setSex(rs.getString("sex"));
				graduate.setUniversity(rs.getString("university"));
				graduate.setAddress(rs.getString("address"));
				graduate.setTel(rs.getString("tel"));
				graduate.setDescription(rs.getString("description"));
				graduate.setPhoto(rs.getString("photo"));
				graduate.setAddDate(rs.getString("add_date"));
				graduate.setNation(rs.getString("nation"));
				graduate.setSource(rs.getString("source"));
				graduate.setMajor(rs.getString("major"));
				graduate.setAspec(rs.getString("aspect"));
				graduate.setStatus(rs.getString("status"));
				graduate.setCompany(rs.getString("company"));
				graduate.setSalary(rs.getString("salary"));
				graduate.setGraduateyear(rs.getString("graduateyear"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graduate;
	}
	
	
	
	/**
	 * 登录用
	 * 根据用户名取记录
	 * @param id 主键
	 * @return
	 */
	public Graduate getGraduateByUsername(String username){
		Graduate graduate = null;
		Connection conn = ConnDB.getConn();
		String sql = "select username,password,name,sex,university,address,tel,description,photo,add_date,relo from graduate_info where username=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				graduate  = new Graduate();
				graduate.setUsername(rs.getString("username"));
				graduate.setPassword(rs.getString("password"));
				graduate.setName(rs.getString("name"));
				graduate.setSex(rs.getString("sex"));
				graduate.setUniversity(rs.getString("university"));
				graduate.setAddress(rs.getString("address"));
				graduate.setTel(rs.getString("tel"));
				graduate.setDescription(rs.getString("description"));
				graduate.setPhoto(rs.getString("photo"));
				graduate.setAddDate(rs.getString("add_date"));
				graduate.setRelo(rs.getInt("relo"));
				
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graduate;
	}
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 */
	public void del(String username){
		Connection conn = ConnDB.getConn();
		String sql = "delete from graduate_info where username=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
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
	 * @param graduate
	 */
	public void update(Graduate graduate){
		Connection conn = ConnDB.getConn();
		String sql = "update graduate_info set password=?, name=?,sex=?,university=?,"
				+ "tel=?,description=?,photo=?,nation=?,source=?,major=?,aspect=?,status=?,company=?,salary=?,address=?,graduateyear=? where username=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, graduate.getPassword());
			pst.setString(2, graduate.getName());
			pst.setString(3, graduate.getSex());
			pst.setString(4, graduate.getUniversity());
			pst.setString(5, graduate.getTel());
			pst.setString(6, graduate.getDescription());
			pst.setString(7, graduate.getPhoto());
			pst.setString(8, graduate.getNation());
			pst.setString(9, graduate.getSource());
			pst.setString(10, graduate.getMajor());
			pst.setString(11, graduate.getAspec());
			pst.setString(12, graduate.getStatus());
			//待业情况
			if(graduate.getStatus().equals("1")){
				pst.setString(13, null);
				pst.setString(14, null);
				pst.setString(15, null);
			}
			//就业情况
			if(graduate.getStatus().equals("0")){
				pst.setString(13, graduate.getCompany());
				pst.setString(14, graduate.getSalary());
				pst.setString(15, graduate.getAddress());
			}
			//毕业年份
			if((graduate.getGraduateyear()).equals("未毕业")){
				pst.setString(16, graduate.getGraduateyear());
			}
			if(!(graduate.getGraduateyear()).equals("未毕业")){
				pst.setString(16, graduate.getGraduateyear());
			}
			pst.setString(17, graduate.getUsername());
			
			if(graduate.getStatus().equals("0")){
				Connection conn1 = ConnDB.getConn();
				String sql1 = "insert into employment_information(name,photo,salary,company,address,username,add_Date ) " +
					     "values(?,?,?,?,?,?,now())";
			try {
				
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, graduate.getName());
				pst1.setString(2, graduate.getPhoto());
				pst1.setString(3, graduate.getSalary());
				pst1.setString(4, graduate.getCompany());
				pst1.setString(5, graduate.getAddress());
				pst1.setString(6, graduate.getUsername());
				//增册改都是执行executeUpdate方法
				pst1.executeUpdate();
				pst1.close();
				conn1.close();
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 public List<Graduate> find(int page){
         
         //创建List
         List<Graduate> list = new ArrayList<Graduate>();
         //获取数据库连接
         Connection conn = ConnDB.getConn();
         //分页查询的SQL语句
         String sql = "select * from graduate_info order by salary  limit ?,? ";
         try {
             //获取PreparedStatement
             PreparedStatement ps = conn.prepareStatement(sql);
             //SQL语句中的第1个参数赋值
             ps.setInt(1, (page - 1) * Graduate.PAGE_SIZE);
             //对SQL语句中的第2个参数赋值
             ps.setInt(2, Graduate.PAGE_SIZE);
             //执行查询操作
             ResultSet rs = ps.executeQuery();
             //光标向后移动，并判断是否有效
            while(rs.next()) {
                 //实例化Product
            	Graduate graduate = new Graduate();
				graduate.setUsername(rs.getString("username"));
				graduate.setName(rs.getString("name"));
				graduate.setSex(rs.getString("sex"));
				graduate.setUniversity(rs.getString("university"));
				graduate.setSource(rs.getString("source"));
				graduate.setTel(rs.getString("tel"));
				graduate.setDescription(rs.getString("description"));
				graduate.setPhoto(rs.getString("photo"));
				graduate.setAddDate(rs.getString("add_date"));
				graduate.setStatus(rs.getString("status"));//就业情况
				graduate.setGraduateyear(rs.getString("graduateyear"));//毕业年份
				//把每一个对象放入list
				list.add(graduate);
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
         String sql = "select count(*) from graduate_info";
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
	 * 根据主键取密码
	 * @param id 主键
	 * @return
	 */
	public Graduate getGraduateByIdtopassword(String  username){
		Graduate graduate = new Graduate();
		Connection conn = ConnDB.getConn();
		String sql = "select username,password from graduate_info where username=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				graduate.setUsername(rs.getString("username"));
				graduate.setPassword(rs.getString("password"));
				graduate.setYpassword("");
				graduate.setXpassword("");
				graduate.setCfpassword("");
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return graduate;
	}
	
	
	/**
	 * 修改密码查询
	 * @param userInfo
	 */
	public void updatepassword(Graduate graduate){
		
		Connection conn = ConnDB.getConn();
		String sql = "update graduate_info set password=?where username=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, graduate.getXpassword());
			pst.setString(2, graduate.getUsername());
			pst.executeUpdate();
			System.out.println(sql);
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
