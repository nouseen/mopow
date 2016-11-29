package cn.mopow.controller.article;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.ArticleService;
@Controller
@RequestMapping("/news")
public class NextAndPreArticleController {
	@Resource
	private ArticleService service;
	@RequestMapping("/nextandpre.do")
	@ResponseBody
	public MpResult execute(String time,String mark){
		MpResult result;
		if("-1".equals(mark)){
			result=service.findPreArticle(time);
		}else if("1".equals(mark)){
			result=service.findNextArticle(time);
		}else{  
			result=null;
		}
		return result;
		
	}
}
