package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.GraduateDao;
import com.model.Employment;
import com.model.Graduate;
/**
 * 毕业生模块控制器
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="graduate")
public class GraduateController {

	private GraduateDao graduateDao = new GraduateDao();
	int result = 0;//传错误信息
	String id1 ;//传当前id
	/**
	 * 列表
	 * @param graduate 查询条件
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Graduate graduate,Model model){
		//把查询的数据放入model
		model.addAttribute("list", graduateDao.list(graduate));
		List<Graduate> list = graduateDao.list(graduate);
		if(null == list || list.size() ==0){
			result = 2;
			model.addAttribute("result", result);
		    return "/view/Graduate/graduate_list";
		}
		//没有使用查询逻辑
		if(graduateDao.list(graduate) == null){ //用户名错
			result = 1;
			model.addAttribute("result", result);
		return "/view/Graduate/graduate_list";
		}
		return "/view/Graduate/graduate_list";
	}

	
	/**
	 * @RequestParam("garaduatePhoto") 定义页面中提交到服务器的参数名
	 * 新增
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(@RequestParam("myPhoto") MultipartFile file,Graduate graduate,HttpSession session){
		//如果有文件上传则把文件复制到upload下
		if(!file.getOriginalFilename().equals("")){//文件上传
			//取upload的绝对路径
			String path = session.getServletContext().getRealPath("upload");
			//构造一个需要复制到的目标文件
			File dest = new File(path+"\\"+file.getOriginalFilename());
			//copy文件
			try{
			FileUtils.copyInputStreamToFile(file.getInputStream(),dest);
			
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	//把上传的文件名保存到model中
			graduate.setPhoto(file.getOriginalFilename());		
		
	  }
		//插入数据库
		graduateDao.add(graduate);
		
		return "redirect:graduatefind";
	}
	
	
	
	/**
	 * 根据主键查看详情
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(String username,Model model){
		model.addAttribute("graduate",graduateDao.getGraduateById(username));
		return "/view/Graduate/graduate_view";
	}

	
	/**
	 * 根据主键查询记录到修改页面
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="preUpdate")
	public String preUpdate(String username,Model model){
		model.addAttribute("graduate",graduateDao.getGraduateById(username));
		return "/view/Graduate/graduate_update";
	}
	
	/**
	 * 修改
	 * @param graduate
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(@RequestParam("myPhoto") MultipartFile file,Graduate graduate,HttpSession session,Integer relo){
		    //如果有文件上传则把文件复制到upload下
		    if(!file.getOriginalFilename().equals("")){//文件上传
			//取upload的绝对路径
			String path = session.getServletContext().getRealPath("upload");
			//构造一个需要复制到的目标文件
			File dest = new File(path+"\\"+file.getOriginalFilename());
			//copy文件
			try{
			FileUtils.copyInputStreamToFile(file.getInputStream(),dest);
			
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		  }
	        //把上传的文件名保存到model中
			graduate.setPhoto(file.getOriginalFilename());		
		}
		graduateDao.update(graduate);
		if(relo==3){
			id1=graduate.getUsername();
			return "show";//返回到
		}
		return "redirect:graduatefind";
	}
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="del")
	public String del(String username){
		graduateDao.del(username);
		//重定向跳转到graduate/list方法
		return "redirect:graduatefind";
	}
	

	@RequestMapping(value="graduatefind")
	public String graduatefind(Model model,HttpServletRequest request){
		//当前页码
        int currPage = 1;
      //判断传递页码是否有效
        if(request.getParameter("page") != null) {
       	//对当前页码赋值ֵ
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        model.addAttribute("page",currPage);
		model.addAttribute("list",graduateDao.find(currPage));
		
		 //总页数
        int pages;
      //查询总记录数
        int count = graduateDao.findCount();
        
      //计算总页数
        if(count %  Employment.PAGE_SIZE == 0) {
       	 //对总页数赋值ֵ
            pages = count /  Employment.PAGE_SIZE;
        }else {
       	//对总页数赋值ֵ
            pages = count /  Employment.PAGE_SIZE + 1;
        }
        int a=1;
        model.addAttribute("pages",pages);
        model.addAttribute("ab",a);
		return "view/Graduate/graduate_list";
	}
	/**
	 * 先获取密码，
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="graduateUpdatepassword")
	public String graduateUpdatepassword(String username,Model model){
		model.addAttribute("graduate",graduateDao.getGraduateByIdtopassword(username));
	     
		return "view/Graduate/graduateinfo_password";
	}
	/**
	 * 修改密码
	 * @param userInfoupdatepassword
	 * @return
	 */
	@RequestMapping(value="graduatepassword")
	public String graduatepassword(Graduate graduate,Model model){
		int result = 0;
		String password = graduate.getPassword();
		String xpassword = graduate.getXpassword();
		String cfpassword = graduate.getCfpassword();
		if (cfpassword.equals(xpassword)) {
		   if (password.equals(graduate.getYpassword())) {
			   graduateDao.updatepassword(graduate);
			return "show";
		   }
		    result = 2;
			model.addAttribute("result", result);
			return "view/Graduate/graduateinfo_password";  
		}
		result = 1;
		model.addAttribute("result", result);
		return "view/Graduate/graduateinfo_password";  
		
	}
	
	
	
}
