package cn.mopow.controller.pic;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.PictureService;
@Controller
@RequestMapping("/pic")
public class PicLoadController {
	@Resource
	private PictureService service;
	@RequestMapping("/loadpic.do")
	@ResponseBody
	public MpResult execute(int page,int isdel){
		MpResult result = service.listPic(page,isdel);
		return result;
	} 
	

	@RequestMapping("/picpage.do")
	@ResponseBody
	public MpResult picpage(int page,int isdel){
		MpResult result = service.picPage(page,isdel);
		return result;
	}
	
	
	
}
