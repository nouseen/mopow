package cn.mopow.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.NewsService;
@Controller
@RequestMapping("/admin")
public class NewsDeleteController {
	@Resource
	private NewsService service;
	@RequestMapping("/delenews.do")
	@ResponseBody
	public MpResult execute(String id){
		System.out.println("hahha:"+id);
		MpResult result=service.deleNews(id);
		return result;
	}
}
