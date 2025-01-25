package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;











import com.dao.GraduateDao;
import com.dao.TeacherDao;
import com.dao.UserInfoDao;
import com.model.Graduate;
import com.model.Teacher;
import com.model.UserInfo;


/**
 * 登录的控制器类
 * @author Administrator
 *
 */
//注解Controller：告诉springmvc当前类是一个控制器
@Controller
//页面中如何访问,只要请求loginController就可以访问
@RequestMapping(value="loginController")
public class LoginController {

	//页面中如何访问控制器的方法,只要请求loginController/login就可以访问
	@RequestMapping(value="login")
	public String login(UserInfo userInfo,Model model,HttpSession session){
		UserInfoDao userInfoDao = new UserInfoDao();
		GraduateDao graduateDao =new GraduateDao();
		TeacherDao teacherDao =new TeacherDao();
		Teacher ti= teacherDao.getTeacherByUsername(userInfo.getUsername());
		Graduate gi =graduateDao.getGraduateByUsername(userInfo.getUsername());
		UserInfo ui = userInfoDao.getUserInfoByUsername(userInfo.getUsername());
		int result = 0;
		int relo = userInfo.getRelo();
		if(relo==3){
			//0 成功 1 用户名错 2 密码错
		    if(gi == null){ //用户名错
				result = 1;
				model.addAttribute("result", result);
				return "login";
			}else{ //用户存在
				if(!gi.getPassword().equals(userInfo.getPassword())){ //密码错
				result = 2;
					model.addAttribute("result", result);
					return "login";
				}else{ //登录成功
				//把用户对象放入Session(全局变量,所有的页面和controller都可以从session中取)
				session.setAttribute("graduateo", gi);
				int relo2 = gi.getRelo();
				session.setAttribute("relo", relo2);
			    System.out.println("当前权限:"+ relo2);
				}
		    }
		    System.out.println("登录系统");
		    return  "main";
			/*return "redirect:/index";*///返回index页面
		}
		if(relo==2){
			//0 成功 1 用户名错 2 密码错
		    if(ti == null){ //用户名错
				result = 1;
				model.addAttribute("result", result);
				return "login";
			}else{ //用户存在
				if(!ti.getPassword().equals(userInfo.getPassword())){ //密码错
				result = 2;
					model.addAttribute("result", result);
					return "login";
				}else{ //登录成功
				//把用户对象放入Session(全局变量,所有的页面和controller都可以从session中取)
				session.setAttribute("teacher", ti);
				int relo2 = ti.getRelo();
				session.setAttribute("relo", relo2);
				System.out.println("当前权限:"+ relo2);
				}
		    }
		    System.out.println("登录系统");
			return  "main";
		}
		//0 成功 1 用户名错 2 密码错
		if(ui == null){ //用户名错
			result = 1;
			model.addAttribute("result", result);
			return "login";
		}else{ //用户存在
			if(!ui.getPassword().equals(userInfo.getPassword())){ //密码错
				result = 2;
				model.addAttribute("result", result);
				return "login";
			}else{ //登录成功
				//把用户对象放入Session(全局变量,所有的页面和controller都可以从session中取)
				session.setAttribute("userInfo", ui);	
				int relo2 = ui.getRelo();
				session.setAttribute("relo", relo2);
				System.out.println("当前权限:"+ relo2);
			}
		}
		System.out.println("登录系统");
					return "main";
	}
	
	//页面中如何访问控制器的方法,只要请求loginController/logout就可以访问
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		System.out.println("退出系统");
		session.invalidate();
		return "login";
	}
	
}
