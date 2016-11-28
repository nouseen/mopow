package cn.mopow.controller.article;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.NewsService;
@Controller
@RequestMapping("/news")
public class ListArticleController {
	@Resource
	private NewsService newsService;
	@RequestMapping("/listnews.do")
	@ResponseBody
	public MpResult execute(int page){
		MpResult result = newsService.listNews(page);
		return result;
	}
	
	@RequestMapping("/pagenews.do")
	@ResponseBody
	public MpResult pagenews(int page){
		MpResult result = newsService.pageNews(page);
		return result;
	}
	
	@RequestMapping("/listhomenews.do")
	@ResponseBody
	public MpResult execute(){
		MpResult result = newsService.listHomeNews();
		return result;
		
	}
}
