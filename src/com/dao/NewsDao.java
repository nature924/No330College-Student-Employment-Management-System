package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;







import com.model.News;


import com.util.ConnDB;

public class NewsDao {
	
	public List<News> list(News qnews){
		//查询条件
		        String content = qnews.getContent();//内容
		        String title = qnews.getTitle();//标题
		        String classify=qnews.getClassify();//分类
				String startDate = qnews.getStartDate(); //起始日期
			    String endDate = qnews.getEndDate();     //结束日期
		List<News> list = new ArrayList<News>();
		if((content != null && !content.equals(""))||(startDate != null && !startDate.equals(""))
				||(endDate != null && !endDate.equals(""))||(title != null && !title.equals(""))
				||(classify != null && !classify.equals(""))){
		
		String sql = "select id,title,classify,content,photo,add_date from news_info where 1=1 " ;
		
		if(content != null && !content.equals("")){
			sql += "and content like '%" + content + "%' ";}
		if(title != null && !title.equals("")){
			sql += "and title like '%" + title + "%' ";}
		if(classify != null && !classify.equals("")){
			sql += "and classify like '" + classify + "' ";}
		if(startDate != null && !startDate.equals("")){
		     sql += "and add_date>='" + startDate + "' ";}
		if(endDate != null && !endDate.equals("")){
		   endDate += " 23:59:59";
		   sql += "and add_date<='" + endDate + "' ";}
		  Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
			//把取的一条的记录所有的字段，赋给graduate对象的属性
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setClassify(rs.getString("classify"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setPhoto(rs.getString("photo"));
				news.setAdd_date(rs.getString("add_date"));
				list.add(news);
						
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
	
	public List<News> list(int num){
		List<News> list = new ArrayList<News>();
		String sql = "select title,content,add_date from news_info " ;
		Connection conn = ConnDB.getConn();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			//pst.setString(1, qUserInfo.getUsername()+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){ //遍历获取每一条记录
				//把取的一条的记录所有的字段，赋给userInfo对象的属性
				News news = new News();
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setAdd_date(rs.getString("add_date"));
				//把每一个对象放入list
				list.add(news);
				
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
 * 添加
 * @param news
 */
	public static void add(News news) {
		Connection conn = ConnDB.getConn();
		String sql = "insert into news_info(classify,title,content,photo,add_date)values(?,?,?,?,now())";
		try {
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, news.getClassify());
			pst.setString(2, news.getTitle());
			pst.setString(3, news.getContent());
			pst.setString(4, news.getPhoto());
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
	public News getNewsById(Integer id){
		News news = new News();
		Connection conn = ConnDB.getConn();
		String sql = "select id,classify,title,content,photo,add_date from news_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setPhoto(rs.getString("photo"));
				news.setClassify(rs.getString("classify"));
				news.setAdd_date(rs.getString("add_date"));
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 */
	public void del(Integer id){
		Connection conn = ConnDB.getConn();
		String sql = "delete from news_info where id=?";
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
	 * @param graduate
	 */
	public void update(News news){
		Connection conn = ConnDB.getConn();
		String sql = "update news_info set title=?,content=?, photo=? where id=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, news.getTitle());
			pst.setString(2, news.getContent());
			pst.setString(3, news.getPhoto());
			pst.setInt(4, news.getId());
			
			pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<News> find(int page){
        
        //创建List
        List<News> list = new ArrayList<News>();
        //获取数据库连接
        Connection conn = ConnDB.getConn();
        //分页查询的SQL语句
        String sql = "select * from news_info ORDER BY add_date DESC limit ?,? ";
        try {
            //获取PreparedStatement
            PreparedStatement ps = conn.prepareStatement(sql);
            //SQL语句中的第1个参数赋值
            ps.setInt(1, (page - 1) * News.PAGE_SIZE);
            //对SQL语句中的第2个参数赋值
            ps.setInt(2, News.PAGE_SIZE);
            //执行查询操作
            ResultSet rs = ps.executeQuery();
            //光标向后移动，并判断是否有效
           while(rs.next()) {
                //实例化Product
        	 //把取的一条的记录所有的字段，赋给graduate对象的属性
				News news = new News();
				news.setClassify(rs.getString("classify"));
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setPhoto(rs.getString("photo"));
				news.setAdd_date(rs.getString("add_date"));
				list.add(news);
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
        String sql = "select count(*) from news_info";
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
