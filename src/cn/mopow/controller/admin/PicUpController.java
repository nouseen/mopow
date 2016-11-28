package cn.mopow.controller.admin;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.service.PictureService;

@Controller
@RequestMapping("/admin")
public class PicUpController {
	@Resource
	private PictureService service;
	@RequestMapping("/upload.do")
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 service.savePic(request, response);
	}  
	
	@RequestMapping("/deletepic.do")
	@ResponseBody
	public void deletepic(String id){
		service.deletePic(id);
	}
	
	@RequestMapping("/huanyuanpic.do")
	@ResponseBody
	public void huanyuanPic(String id){
		service.huanyuanPic(id);
	}
		
	}


