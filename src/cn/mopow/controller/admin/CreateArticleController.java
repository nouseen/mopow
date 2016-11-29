package cn.mopow.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.ArticleService;

@Controller  
@RequestMapping("/admin")
public class CreateArticleController {
	@Resource 
	private ArticleService service;
	@RequestMapping("/createnews.do")
	@ResponseBody
	public MpResult execute(String title,String body,String abstr,String img,String type){
		MpResult result = service.createArticle(title, body, abstr,img,type);
		return result;
	}
}
