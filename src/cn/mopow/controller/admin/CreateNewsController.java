package cn.mopow.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.NewsService;

@Controller
@RequestMapping("/admin")
public class CreateNewsController {
	@Resource
	private NewsService service;
	@RequestMapping("/createnews.do")
	@ResponseBody
	public MpResult execute(String title,String body,String abstr){
		MpResult result = service.createNews(title, body, abstr);
		return result;
	}
}
