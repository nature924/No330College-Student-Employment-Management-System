package com.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.TeacherDao;
import com.model.Teacher;
import com.model.UserInfo;
import com.util.ConnDB;

@Controller
@RequestMapping(value="teacher")
public class TeacherController {

	private TeacherDao teacherDao = new TeacherDao();
	int result = 0;//传错误信息
	Integer id1 ;
	
	@RequestMapping(value="list")
	public String list(Model model,  Teacher teacher){
		
		model.addAttribute("list", teacherDao.list(teacher));
		List<Teacher> list = teacherDao.list(teacher);
		if(null == list || list.size() ==0){
			result = 2;
			model.addAttribute("result", result);
		    return "/view/Teacher/teacher_list";
		}
		//没有使用查询逻辑
		if(teacherDao.list(teacher) == null){ //用户名错
			result = 1;
			model.addAttribute("result", result);
		return "/view/Teacher/teacher_list";
		}
		return "/view/Teacher/teacher_list";
	}
//显示
	@RequestMapping(value="teacherfind")
	public String teacherfind(Model model,HttpServletRequest request){
		//当前页码
        int currPage = 1;
      //判断传递页码是否有效
        if(request.getParameter("page") != null) {
       	//对当前页码赋值ֵ
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        model.addAttribute("page",currPage);
		model.addAttribute("list",teacherDao.find(currPage));
		
		 //总页数
        int pages;
      //查询总记录数
        int count = teacherDao.findCount();
        
      //计算总页数
        if(count %  Teacher.PAGE_SIZE == 0) {
       	 //对总页数赋值ֵ
            pages = count /  Teacher.PAGE_SIZE;
        }else {
       	//对总页数赋值ֵ
            pages = count /  Teacher.PAGE_SIZE + 1;
        }
        int a=1;
        model.addAttribute("pages",pages);
        model.addAttribute("ab",a);
		return "view/Teacher/teacher_list";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */

	@RequestMapping(value="add")
	public String add(@RequestParam("teacherPhoto") MultipartFile file,Teacher teacher,
			HttpSession session){
		TeacherDao teacherDao = new TeacherDao();
		System.out.println(file.getOriginalFilename());
		//如果有文件上传则把文件复制到upload下
		if(!file.getOriginalFilename().equals("")){//有文件写入
			//取upload的绝对路径
			String path = session.getServletContext().getRealPath("upload");
			//构造一个需要复制到的目标文件
			File dest = new File(path+"\\"+file.getOriginalFilename());
			//copy文件
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//把上传的文件名保存到model中
			teacher.setPhoto(file.getOriginalFilename());
			
		}
		teacherDao.add(teacher);
		return "redirect:teacherfind";
	}
	
	/**
	 * 根据主键查看详情
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Integer id,Model model){
		System.out.println(id);
		model.addAttribute("teacher",teacherDao.getTeacherById(id));
		return "/view/Teacher/teacher_view";
	}
	
	/**
	 * 根据主键查询记录到修改页面
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="preUpdate")
	public String preUpdate(Integer id,Model model){
		model.addAttribute("teacher",teacherDao.getTeacherById(id));
		return "/view/Teacher/teacher_update";
	}
	
	/**
	 * 修改
	 * @param company
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(@RequestParam("teacherPhoto") MultipartFile file,Teacher teacher,
			HttpSession session,Integer relo){
		System.out.println(file.getOriginalFilename());
		//如果有文件上传则把文件复制到upload下
		if(!file.getOriginalFilename().equals("")){//有文件上传
			//取upload的绝对路径
			String path = session.getServletContext().getRealPath("upload");
			//构造一个需要复制到的目标文件
			File dest = new File(path+"\\"+file.getOriginalFilename());
			//copy文件
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//把上传的文件名保存到model中
			teacher.setPhoto(file.getOriginalFilename());
			
		}
		teacherDao.update(teacher);
		if(relo==2){
			id1=teacher.getId();
			return "show";//返回到
		}
		return "redirect:teacherfind";
	}
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="del")
	public String del(Integer id){
		teacherDao.del(id);
		//重定向跳转到company/list方法
		return "redirect:teacherfind";
	}
	
	/**
	 * 根据主键取密码
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="getUserInfoByIdtopassword")
	public Teacher getUserInfoByIdtopassword(Integer id){
		Teacher  teacher = new Teacher();
		Connection conn = ConnDB.getConn();
		String sql = "select id,password from teacher_info where id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				teacher.setId(rs.getInt("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setYpassword("请输入原密码");
				teacher.setXpassword("请输入新密码");
				teacher.setCfpassword("请在输入新密码");
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
	
	
	/**
	 * 先获取密码，
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="teacherUpdatepassword")
	public String teacherUpdatepassword(Integer id,Model model){
		model.addAttribute("teacher",teacherDao.getTeacherByIdtopassword(id));
	     
		return "view/Teacher/teacherinfo_password";
	}
	/**
	 * 修改密码查询
	 * @param userInfo
	 */
	@RequestMapping(value="updatepassword")
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
	
		/**
		 * 修改密码
		 * @param teacherpassword
		 * @return
		 */
		@RequestMapping(value="teacherpassword")
		public String teacherpassword(Teacher teacher,Model model){
			int result = 0;
			String password = teacher.getPassword();
			String xpassword = teacher.getXpassword();
			String cfpassword = teacher.getCfpassword();
			if (cfpassword.equals(xpassword)) {
			   if (password.equals(teacher.getYpassword())) {
				   teacherDao.updatepassword(teacher);
				return "show";
			   }
			    result = 2;
				model.addAttribute("result", result);
				return "view/Teacher/teacherinfo_password";  
			}
			result = 1;
			model.addAttribute("result", result);
			return "view/Teacher/teacherinfo_password";
		}
}
