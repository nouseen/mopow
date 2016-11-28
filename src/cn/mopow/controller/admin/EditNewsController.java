package cn.mopow.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.NewsService;
@Controller
@RequestMapping("/admin")
public class EditNewsController {
	@Resource
	private NewsService service;
	@RequestMapping("/modifythenews.do")
	@ResponseBody
	public MpResult execute(String id,String title,String body,String abstr,String time){
		MpResult result;
			result = service.editNews(id, title, body, abstr,time);
		return result;
	}
}
