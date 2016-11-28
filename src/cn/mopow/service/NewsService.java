package cn.mopow.service;

import javax.servlet.http.HttpServletRequest;

import cn.mopow.entity.MpResult;

public interface NewsService {
	public MpResult showTheNews(String id);
	public MpResult listNews(int page);
	public MpResult createNews(String title,String body,String abstr);
	public MpResult editNews(String id,String title,String body,String abstr,String time);
	public MpResult listHomeNews();
	public MpResult removeRecommend(String id);
	public MpResult setRecommend(String id);
	public MpResult deleNews(String id);
	public MpResult findRecommendNews(int page);
	public MpResult findPreNews(String time);
	public MpResult findNextNews(String time);
	public MpResult updateClick(HttpServletRequest request);
	public MpResult listHotNews();
	public MpResult pageNews(int page);
}
