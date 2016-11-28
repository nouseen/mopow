package cn.mopow.controller.article;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mopow.service.NewsService;
@Controller
public class ArticleJumpController {
	@Resource
	private NewsService service;
	@RequestMapping("/news-*.do")
public ModelAndView execute(HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv=new ModelAndView("news.html");
	return mv;
}
	@RequestMapping("/mobile/m_news-*.do")
public ModelAndView execute2(HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv=new ModelAndView("m_news.html");
	return mv;
}
	
	@RequestMapping("/edit-*.do")
public ModelAndView execute1(HttpServletRequest request, HttpServletResponse response){
	ModelAndView mv = new ModelAndView("admin/newsedit.html");
	return mv;
}
}
