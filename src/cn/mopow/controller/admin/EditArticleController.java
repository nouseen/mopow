package cn.mopow.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.ArticleService;
@Controller
@RequestMapping("/admin")
public class EditArticleController {
	@Resource
	private ArticleService service;
	@RequestMapping("/modifythenews.do")
	@ResponseBody
	public MpResult execute(String id,String title,String body,String abstr,String time,String type,String img){
		MpResult result;
			result = service.editArticle(id, title, body, abstr,time,type,img);
		return result;
	}
}
