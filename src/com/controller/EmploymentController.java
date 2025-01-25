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

import com.dao.EmploymentDao;
import com.model.Employment;

@Controller
@RequestMapping(value="employment")
public class EmploymentController {
	/**
	 * 按照查询条件查询
	 * 跳转到list界面
	 * 
	 */

	private EmploymentDao employmentDao =new EmploymentDao();
	int result = 0;
	@RequestMapping(value="list")
	public String list(Model model, HttpSession session, Employment employment){
		Employment ui = (Employment)session.getAttribute("employment");
		System.out.println(ui);
		
		model.addAttribute("list", employmentDao.list(employment));
		List<Employment> list = employmentDao.list(employment);
		if(null == list || list.size() ==0){
			result = 2;
			model.addAttribute("result", result);
		    return "/view/Employment/employment_list";
		}
		if(employmentDao.list(employment) == null){ //输入框里面没有内容
			result = 1;
			model.addAttribute("result", result);
		return "/view/Employment/employment_list";
		}
		return "/view/Employment/employment_list";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */

	@RequestMapping(value="add")
	public String add(@RequestParam("employmentPhoto") MultipartFile file,Employment employment,
			HttpSession session){
		EmploymentDao employmentDao = new EmploymentDao();
		//如果有文件上传则把文件复制到upload
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
			employment.setPhoto(file.getOriginalFilename());
			
		}
		employmentDao.add(employment);
		return "redirect:/employment/employmentfind";
	}
	
	/**
	 * 根据主键查看详情
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Integer id,Model model){
		System.out.println(id);
		model.addAttribute("employment",employmentDao.getEmploymentById(id));
		return "/view/Employment/employment_view";
	}
	/**
	 * 根据主键查询记录到修改页面
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="preUpdate")
	public String preUpdate(Integer id,Model model){
		model.addAttribute("employment",employmentDao.getEmploymentById(id));
		return "/view/Employment/employment_update";
	}
	
	
	/**
	 * 修改
	 * @param company
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(Employment employment,
			HttpSession session){
		employmentDao.update(employment);
		return "redirect:/employment/employmentfind";
	}
	

	/**
	 * 根据主键删除记录
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="del")
	public String del(Integer id){
		employmentDao.del(id);
		//重定向跳转到company/list方法
		return "redirect:employmentfind";
	}
    /**
     * 查询用
     * @param id
     * @param model
     * @return
     */
	@RequestMapping(value="select")
	public String select(String username,Model model){
		model.addAttribute("e",employmentDao.getEmploymentselect(username));
		model.addAttribute("employment",employmentDao.getEmploymentByName(username));
		return "/view/Employment/employment_select";
	}
	/**
	 * admin 用
	 * @param model
	 * @return
	 */
	@RequestMapping(value="employmentadmin")
	public String employmentadmin(Model model){
		
		model.addAttribute("employment1",employmentDao.getEmploymentAdmin());
		return "admin";
	}

	/**
	 * 
	 * 分页查询用
	 */
	@RequestMapping(value="employmentfind")
	public String employmentfind(Model model,HttpServletRequest request){
		//当前页码
        int currPage = 1;
      //判断传递页码是否有效
        if(request.getParameter("page") != null) {
       	//对当前页码赋值ֵ
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        model.addAttribute("page",currPage);
		model.addAttribute("list",employmentDao.find(currPage));
		
		 //总页数
        int pages;
      //查询总记录数
        int count = employmentDao.findCount();
        
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
		return "/view/Employment/employment_list";
	}

     }