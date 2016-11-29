package cn.mopow.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;

import cn.mopow.dao.UserDao;
import cn.mopow.entity.MpAdmin;
import cn.mopow.entity.MpResult;
import cn.mopow.util.MpUtil;
@Service
//扫描控制组件
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	public MpResult checkName(HttpServletRequest request,String name,String pwd,String captcha) {
		MpResult result = new MpResult();
		HttpSession session=request.getSession();
		String sysCaptch=(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY); 
		System.out.println(captcha);
		MpAdmin admin = userDao.findByName(name); 
		if (sysCaptch==null||captcha==null||!sysCaptch.toLowerCase().equals(captcha.toLowerCase())){
			result.setMsg("验证码错误！"); 
			result.setStatus(0); 
			return result;
		} 
		
		if(admin==null||pwd==null){
			result.setMsg("用户名或密码错误！");
			result.setStatus(0); 
			return result;
		}
		
		try {
			if((MpUtil.md5(pwd).trim()).equals(admin.getUser_password().trim())){
				result.setMsg("登录成功！");
				session.setAttribute("uName", admin.getUser_name());
				result.setStatus(1);
				return result;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setMsg("用户名或密码错误");
		result.setStatus(0);
		return result;
	}

}
