package cn.mopow.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mopow.entity.MpResult;

public interface PictureService {
	public void savePic(HttpServletRequest req, HttpServletResponse resp) throws IOException;
	public MpResult listPic(int page, int isdel);
	public MpResult loadHomePic();
	public MpResult delPicHome(String id);
	public MpResult setPicHome(String id,String home);
	public MpResult deletePic(String id);
	public MpResult huanyuanPic(String id);
	public MpResult picPage(int page, int isdel);
}
