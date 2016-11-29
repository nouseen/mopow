package cn.mopow.service;

import javax.servlet.http.HttpServletRequest;

import cn.mopow.entity.MpResult;

public interface ArticleService {
	public MpResult showTheArticle(String id);
	public MpResult listArticle(int page);
	public MpResult createArticle(String title,String body,String abstr,String img,String type);
	public MpResult editArticle(String id,String title,String body,String abstr,String time,String type,String img);
	public MpResult listHomeArticle();
	public MpResult removeRecommend(String id); 
	public MpResult setRecommend(String id);
	public MpResult deleArticle(String id);
	public MpResult findRecommendArticle(int page);
	public MpResult findPreArticle(String time);
	public MpResult findNextArticle(String time);
	public MpResult updateClick(HttpServletRequest request);
	public MpResult listHotArticle();
	public MpResult pageArticle(int page);
}
