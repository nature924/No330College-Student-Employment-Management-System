package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.RegsistrDao;
import com.model.UserInfo;

@Controller
@RequestMapping(value="regsistr")
public class RegsistrController {
	
	private RegsistrDao regsistrDao = new RegsistrDao();
	@RequestMapping(value="add")
	public String add(@RequestParam("userPhoto") MultipartFile file, UserInfo userInfo,
			HttpSession session){
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
			userInfo.setPhoto(file.getOriginalFilename());
			
		}
		
		regsistrDao.add(userInfo);
		return "login";
	}
}
