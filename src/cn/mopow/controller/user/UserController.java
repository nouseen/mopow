package cn.mopow.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mopow.entity.MpResult;
import cn.mopow.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService service; 
	@ResponseBody 
	@RequestMapping("/login.do")
	public MpResult execute(HttpServletRequest request,String name,String pwd,String captcha){ 
		MpResult result = service.checkName(request, name, pwd, captcha);
		return result; 
	} 
	
	@RequestMapping("/getUserName.do")
	@ResponseBody
	// 将NoteResult转成json输出
	public MpResult getUserName(HttpServletRequest request) {
		HttpSession session=request.getSession();
		MpResult result = new MpResult();
		result.setData(session.getAttribute("uName"));
		return result; 
	}
}
