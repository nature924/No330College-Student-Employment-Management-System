package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dao.IssueDao;
import com.model.Issue;

@Controller
@RequestMapping(value = "issue")
public class IssueController {
    private IssueDao issueDao = new IssueDao();
    int result = 0;//传错误信息

    /**
     * 列表
     *
     * @param issue 查询条件
     * @return
     */
    @RequestMapping(value = "list")
    public String list(Issue issue, Model model, HttpSession session) {
        //把查询的数据放入model
        model.addAttribute("list", issueDao.list(issue));
        List<Issue> list = issueDao.list(issue);
        if (null == list || list.size() == 0) {
            result = 2;
            model.addAttribute("result", result);
            return "/view/Issue/issue_list";
        }
        //没有使用查询逻辑
        if (issueDao.list(issue) == null) { //没有运行
            result = 1;
            model.addAttribute("result", result);
            return "/view/Issue/issue_list";
        }
        return "/view/Issue/issue_list";
    }

    /**
     * 根据主键查询记录到回复页面
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "preUpdate")
    public String preUpdate(Integer id, Model model) {
        model.addAttribute("issue", issueDao.getIssueById(id));
        return "/view/Issue/issue_update";
    }

    /**
     * 回复
     *
     * @param graduate
     * @return
     */
    @RequestMapping(value = "update")
    public String update(Issue issue, HttpSession session) {
        issueDao.update(issue);
        return "redirect:issuefind";
    }

    /**
     * 根据主键删除记录
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "del")
    public String del(Integer id) {
        issueDao.del(id);
        //重定向跳转到graduate/list方法
        return "redirect:issuefind";
    }

    @RequestMapping(value = "add")
    public String add(Issue issue) {
        //插入数据库
        issueDao.add(issue);
        return "redirect:issuefind";
    }

    @RequestMapping(value = "issuefind")
    public String issuefind(Model model, HttpServletRequest request) {
        //当前页码
        int currPage = 1;
        //判断传递页码是否有效
        if (request.getParameter("page") != null) {
            //对当前页码赋值ֵ
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        model.addAttribute("page", currPage);
        model.addAttribute("list", issueDao.find(currPage));
        //总页数
        int pages;
        //查询总记录数
        int count = issueDao.findCount();
        //计算总页数
        if (count % Issue.PAGE_SIZE == 0) {
            //对总页数赋值ֵ
            pages = count / Issue.PAGE_SIZE;
        } else {
            //对总页数赋值ֵ
            pages = count / Issue.PAGE_SIZE + 1;
        }
        int a = 1;
        model.addAttribute("pages", pages);
        model.addAttribute("ab", a);
        return "view/Issue/issue_list";
    }
}
