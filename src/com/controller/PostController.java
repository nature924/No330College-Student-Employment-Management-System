package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.dao.PostDao;

import com.model.Post;
/**
 * 用户模块控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="post")
public class PostController {
private PostDao postDao = new PostDao();
	
	/**
	 * 列表
	 * @param post 查询条件
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Post post,Model model,HttpSession session){
		Post ui = (Post)session.getAttribute("post");
		System.out.println(ui);
		//把查询的数据放入model
		model.addAttribute("list", postDao.list(post));
		return "/view/Post/post_list";
	}
	
	
	@RequestMapping(value="add")
	public String add(Post post){
		System.out.println(post);
		//插入数据库
		
		postDao.add(post);
		return "redirect:/post/list";
	}
	/**
	 * 根据主键查看详情
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Integer id,Model model){
		System.out.println(id);
		model.addAttribute("post",postDao.getPostById(id));
		return "/view/Post/post_view";
	}
	
	/**
	 * 根据主键查询记录到修改页面
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="preUpdate")
	public String preUpdate(Integer id,Model model){
		model.addAttribute("post",postDao.getPostById(id));
		return "/view/Post/post_update";
	}
	
	/**
	 * 修改
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(Post post){
		postDao.update(post);
		return "redirect:/post/list";
	}
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="del")
	public String del(Integer id){
		postDao.del(id);
		//重定向跳转到userInfo/list方法
		return "redirect:/post/list";
	}
	
}
	
	
	