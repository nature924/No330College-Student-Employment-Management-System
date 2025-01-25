package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.UserInfoDao;
import com.model.UserInfo;

/**
 * 用户模块控制器
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="userInfo")
public class UserInfoController {

	private UserInfoDao userInfoDao = new UserInfoDao();
	
	
	
	/**
	 * 根据主键查看详情
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Integer id,Model model){
		model.addAttribute("userInfo",userInfoDao.getUserInfoById(id));
		return "/view/User/user_view";
	}
	
	/**
	 * 根据主键查询记录到修改页面
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="preUpdate")
	public String preUpdate(Integer id,Model model){
		model.addAttribute("userInfo",userInfoDao.getUserInfoById(id));
		return "/view/User/user_update";
	}
	
	/**
	 * 修改
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(@RequestParam("userPhoto") MultipartFile file,UserInfo userInfo,
			HttpSession session){
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
			userInfo.setPhoto(file.getOriginalFilename());
			
		}
		userInfoDao.update(userInfo);
		return "show";
	}
	
	
	@RequestMapping(value="userInfopreUpdatepassword")
	public String userInfopreUpdatepassword(Integer id,Model model){
		model.addAttribute("userInfo",userInfoDao.getUserInfoByIdtopassword(id));
		return "view/User/userinfo_password";
	}
	/**
	 * 修改密码
	 * @param userInfoupdatepassword
	 * @return
	 */
	@RequestMapping(value="userInfoupdatepassword")
	public String userInfoupdatepassword(UserInfo userInfo,Model model){
		int result = 0;
		String password = userInfo.getPassword();
		String xpassword = userInfo.getXpassword();
		String cfpassword = userInfo.getCfpassword();
		if (cfpassword.equals(xpassword)) {
		   if (password.equals(userInfo.getYpassword())) {
		   userInfoDao.updatepassword(userInfo);
			return "show";
		   }
		    result = 2;
			model.addAttribute("result", result);
			return "view/User/userinfo_password";  
		}
		result = 1;
		model.addAttribute("result", result);
		return "view/User/userinfo_password";
	}
	

}
