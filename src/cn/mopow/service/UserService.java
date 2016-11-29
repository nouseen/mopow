package cn.mopow.service;

import javax.servlet.http.HttpServletRequest;

import cn.mopow.entity.MpResult;

public interface UserService {
	public MpResult checkName(HttpServletRequest request,String name,String pwd,String captcha);
}
