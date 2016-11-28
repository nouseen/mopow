package cn.mopow.dao;

import java.util.List;
import java.util.Map;

import cn.mopow.entity.Article;

public interface NewsDao {
	public Article findById(String id);
	public List<Map<String,String>>  findAllNews(Map<String,Integer> pageMap);
	public List<Map<String,String>> findHomeNews();
	public void createNews(Article news);
	public void editNews(Article news);
	public void deleNewsHome(String n_home);
	public void setRecommend(String n_id);
	public void removeRecommend(String n_id);
	public void deleNews(String n_id);
	public List<Map<String,String>> findRecommendNews(int start);
	public Map<String,String> findPreNews(String n_createtime);
	public Map<String,String> findNextNews(String n_createtime);
	public void updateClick(String n_id);
	public List<Map<String,String>> findHotNews();
	public int countNews();
}