package cn.mopow.service;

import cn.mopow.entity.MpResult;


public interface ClickService {
	public MpResult newClick(String name,String description,int type);
	public MpResult updateClick(String id);
	public MpResult updateClickUrl(String url);
	public MpResult findAClick(String id);
	public MpResult listAllData();
	public MpResult updateListByName(String name);

}

