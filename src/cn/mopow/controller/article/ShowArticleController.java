package cn.mopow.controller.article;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.ArticleService;

@Controller
@RequestMapping("/news")
public class ShowArticleController {
	@Resource
	
	private ArticleService service;
	@RequestMapping("/showthenews.do")
	@ResponseBody
	public MpResult execute(String id) {
		MpResult result = service.showTheArticle(id);
		return result;
	}
}
