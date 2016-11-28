package cn.mopow.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.ClickService;

@Controller
@RequestMapping("/admin")
public class ClickAdminController {
	@Resource
	private ClickService clickService;
	@ResponseBody
	@RequestMapping("/newclick.do")
	public MpResult execute(String name,String description,int type){
		MpResult result= clickService.newClick(name,description,type);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/allclick.do")
	public MpResult execute01(){
		MpResult result = 
				clickService.listAllData();
		return result;
	}
}
