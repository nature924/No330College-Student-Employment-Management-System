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

import com.dao.NewsDao;
import com.model.News;

@Controller
@RequestMapping(value="news")
public class NewsController {
    private NewsDao newsDao = new NewsDao();
    int result = 0;//传错误信息
	
	@RequestMapping(value="list")
	public String list(News news, Model model,HttpSession session){
		model.addAttribute("list", newsDao.list(news));
		List<News> list = newsDao.list(news);
		if(null == list || list.size() ==0){
			result = 2;
			model.addAttribute("result", result);
		    return "/view/New/news_list";
		}
		//没有使用查询逻辑
		if(newsDao.list(news) == null){ //用户名错
			result = 1;
			model.addAttribute("result", result);
		return "/view/New/news_list";
		}
		return "/view/New/news_list";
	}

	/**
	 * @RequestParam("newPhoto") 定义页面中提交到服务器的参数名
	 * 新增
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(@RequestParam("myPhoto") MultipartFile file,News news,HttpSession session){
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
			news.setPhoto(file.getOriginalFilename());		
		
	  }
		//插入数据库
		NewsDao.add(news);
		
		return "redirect:newsfind";
	}
	
	/**
	 * 根据主键查看详情
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="view")
	public String view(Integer id,Model model){
		model.addAttribute("news",newsDao.getNewsById(id));
		return "/view/New/news_view";
	}
	
	/**
	 * 根据主键查询记录到修改页面
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="preUpdate")
	public String preUpdate(Integer id,Model model){
		model.addAttribute("news",newsDao.getNewsById(id));
		return "/view/New/news_update";
	}
	
	/**
	 * 修改
	 * @param graduate
	 * @return
	 */
	@RequestMapping(value="update")
	public String update(@RequestParam("myPhoto") MultipartFile file,News news,HttpSession session){
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
			news.setPhoto(file.getOriginalFilename());		
		}
		    newsDao.update(news);
		return "redirect:newsfind";
	}
	
	/**
	 * 根据主键删除记录
	 * @param id 主键
	 * @return
	 */
	@RequestMapping(value="del")
	public String del(Integer id){
		newsDao.del(id);
		//重定向跳转到graduate/list方法
		return "redirect:newsfind";
	}
	
	

	@RequestMapping(value="newsfind")
	public String newsfind(Model model,HttpServletRequest request){
		//当前页码
        int currPage = 1;
      //判断传递页码是否有效
        if(request.getParameter("page") != null) {
       	//对当前页码赋值ֵ
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        model.addAttribute("page",currPage);
		model.addAttribute("list",newsDao.find(currPage));
		
		 //总页数
        int pages;
      //查询总记录数
        int count = newsDao.findCount();
        
      //计算总页数
        if(count %  News.PAGE_SIZE == 0) {
       	 //对总页数赋值ֵ
            pages = count /  News.PAGE_SIZE;
        }else {
       	//对总页数赋值ֵ
            pages = count /  News.PAGE_SIZE + 1;
        }
        int a=1;
        model.addAttribute("pages",pages);
        model.addAttribute("ab",a);
		return "view/New/news_list";
	}
}
