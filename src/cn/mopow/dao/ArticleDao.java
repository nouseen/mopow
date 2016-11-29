package cn.mopow.dao;

import java.util.List;
import java.util.Map;

import cn.mopow.entity.Article;

public interface ArticleDao {
	public Article findById(String id);
	public List<Map<String,String>>  findAllArticle(Map<String,Integer> pageMap);
	public List<Map<String,String>> findHomeArticle();
	public void createArticle(Article article);
	public void editArticle(Article article);
	public void setRecommend(String n_id); 
	public void removeRecommend(String n_id);
	public void deleArticle(String n_id);
	public List<Map<String,String>> findRecommendArticle(int start);
	public Map<String,String> findPreArticle(String n_createtime);
	public Map<String,String> findNextArticle(String n_createtime);
	public void updateClick(String n_id);
	public List<Map<String,String>> findHotArticle();
	public int countArticle(); 
}