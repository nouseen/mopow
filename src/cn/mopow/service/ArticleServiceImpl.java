package cn.mopow.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.mopow.dao.ArticleDao;
import cn.mopow.entity.MpResult;
import cn.mopow.entity.Article;
import cn.mopow.entity.ShowPages;
import cn.mopow.util.MpUtil;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	private ArticleDao ArticleDao;
	 
	private int pageSize = 15; // 每页显示8条记录
	private int currentPage=1;//当前页
	private int pageCount=0; 
	private String pageBar;
	 /**
	  * 计算页数
	  * @param a
	  * @param b
	  * @return
	  */
	int   calPageCount(int   a,int   b){   
	    return   (a+b   -1)/b;   
	} 
	
	@Override
	public MpResult showTheArticle(String id) {
		// TODO Auto-generated method stub
		Article Article=new Article();
		MpResult result = new MpResult();
		Article=ArticleDao.findById(id);
		if(Article==null){
			result.setMsg("系统繁忙！！");
			result.setStatus(0);
			return result;
		}
		result.setData(Article);
		result.setMsg("加载成功！");
		result.setStatus(1);
		
		return result;
	}
	@Override
	public MpResult listArticle(int pageNow) {
		List<Map<String,String>> ArticleList ;
		MpResult result = new MpResult();
		if(pageNow<=0){
			pageNow=1;
		}
		int n=pageNow*pageSize-pageSize;
		int m=ArticleDao.countArticle();
		if(n>=m){
			 if(m==0){
				 n=0;
			 }
			 else if(m % pageSize==0){
				 n=m-pageSize;
			 }else {
				 n=m-(m % pageSize);
			}
		}
		Map<String,Integer> pageMap=new HashMap<String,Integer>();
		pageMap.put("start",n);
		pageMap.put("end",pageSize);
		ArticleList = ArticleDao.findAllArticle(pageMap);
		if(ArticleList==null){
			result.setMsg("加载失败！");
			result.setStatus(0);
			return result;
		}
		result.setMsg("加载成功");
		result.setStatus(1);
		result.setData(ArticleList);
		return result;
	}
	
	/**
	 * 新闻列表分页
	 */
	@Override
	public MpResult pageArticle(int currentPage) {
		MpResult result = new MpResult();
		int m=ArticleDao.countArticle();
		pageCount=calPageCount(m,pageSize);
		
		if(currentPage>=pageCount){
			currentPage=pageCount;
		}
		ShowPages sp=new ShowPages();
		sp.setCurrPage(currentPage);
		//sp.setPageBar("sss");
		sp.setPageCodeCount(10);
		sp.setPageCount(pageCount);
		String strPage=sp.getPageBar();
		pageBar=strPage;
		
		result.setData(pageBar);
		result.setMsg("加载成功！");
		result.setStatus(1);
		return result;
		
	}
	
	
	
	
	
	
	public MpResult createArticle(String title, String body,String abstr) {
		MpResult result=new MpResult();
		Article Article = new Article();
		
		Article.setN_id(MpUtil.createPicId());
		Article.setN_title(title);
		Article.setN_body(body);
		Article.setN_abstract(abstr);
		ArticleDao.createArticle(Article);
		ArticleDao.deleArticleHome(Article.getN_position());
		result.setMsg("新闻添加成功！");
		result.setStatus(1); 
		return result;
	}
	public MpResult listHomeArticle(){
		List<Map<String,String>> ArticleList ;
		MpResult result = new MpResult();
		ArticleList = ArticleDao.findHomeArticle();
		result.setData(ArticleList);
		result.setMsg("加载成功");
		result.setStatus(1);
		return result;
		
	}
	@Override
	public MpResult removeRecommend(String id) {
		MpResult result = new MpResult();
		ArticleDao.removeRecommend(id);
		result.setMsg("移除热点成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult setRecommend(String id) {
		MpResult result = new MpResult();
		ArticleDao.setRecommend(id);
		result.setMsg("设置热点成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult deleArticle(String id) {
		MpResult result = new MpResult();
		ArticleDao.deleArticle(id);
		result.setMsg("新闻删除成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	/**
	 * 推荐新闻列表
	 */
	public MpResult findRecommendArticle(int page) {
		List<Map<String,String>> ArticleList ;
		MpResult result = new MpResult();
		int start =page*10-10;
		ArticleList=ArticleDao.findRecommendArticle(start);
		result.setMsg("加载成功！");
		result.setData(ArticleList);
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult findPreArticle(String time) {
		// TODO Auto-generated method stub
		Map<String,String> Article ;
		MpResult result = new MpResult();
		Article=ArticleDao.findPreArticle(time);
		result.setMsg("加载成功！");
		result.setData(Article);
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult findNextArticle(String time) {
		// TODO Auto-generated method stub
		Map<String,String> Article ;
		MpResult result = new MpResult();
		Article=ArticleDao.findNextArticle(time);
		result.setMsg("加载成功！");
		result.setData(Article);
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult updateClick(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MpResult result = new MpResult();
		String id=request.getRequestURI();
		int start=0;
		for(int i=0;i<id.length();i++){
			char a='-';
			if(a==id.charAt(i)){
				start=i+1;
				break;
			}
		}
		id=id.substring(start, start+35);
		ArticleDao.updateClick(id);
		result.setMsg("更新成功");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult listHotArticle() {
		// TODO Auto-generated method stub
		MpResult result = new MpResult();
		result.setData(ArticleDao.findHotArticle());
		result.setMsg("加载成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult editArticle(String id,String title, String body, String abstr,String time){
		// TODO Auto-generated method stub
		MpResult result=new MpResult();
		Article Article = new Article();
		DateFormat dateFormat; 
	    dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
	    dateFormat.setLenient(false); 
	    java.util.Date timeDate;
		try {
			timeDate = dateFormat.parse(time);
		} catch (ParseException e) {
			result.setStatus(0);
			result.setMsg("时间转换出错");
			return result;
		}
	    java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		Article.setN_createtime(dateTime); 
		Article.setN_id(id);
		Article.setN_title(title);
		Article.setN_body(body);
		Article.setN_abstract(abstr);
		ArticleDao.editArticle(Article);
		result.setMsg("更新成功！");
		result.setStatus(1);
		return result;
	}

}
