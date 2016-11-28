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

import cn.mopow.dao.NewsDao;
import cn.mopow.entity.MpResult;
import cn.mopow.entity.Article;
import cn.mopow.entity.ShowPages;
import cn.mopow.util.MpUtil;
@Service
public class NewsServiceImpl implements NewsService{
	@Resource
	private NewsDao newsDao;
	 
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
	public MpResult showTheNews(String id) {
		// TODO Auto-generated method stub
		Article news=new Article();
		MpResult result = new MpResult();
		news=newsDao.findById(id);
		if(news==null){
			result.setMsg("系统繁忙！！");
			result.setStatus(0);
			return result;
		}
		result.setData(news);
		result.setMsg("加载成功！");
		result.setStatus(1);
		
		return result;
	}
	@Override
	public MpResult listNews(int pageNow) {
		List<Map<String,String>> newsList ;
		MpResult result = new MpResult();
		if(pageNow<=0){
			pageNow=1;
		}
		int n=pageNow*pageSize-pageSize;
		int m=newsDao.countNews();
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
		newsList = newsDao.findAllNews(pageMap);
		if(newsList==null){
			result.setMsg("加载失败！");
			result.setStatus(0);
			return result;
		}
		result.setMsg("加载成功");
		result.setStatus(1);
		result.setData(newsList);
		return result;
	}
	
	/**
	 * 新闻列表分页
	 */
	@Override
	public MpResult pageNews(int currentPage) {
		MpResult result = new MpResult();
		int m=newsDao.countNews();
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
	
	
	
	
	
	
	public MpResult createNews(String title, String body,String abstr) {
		MpResult result=new MpResult();
		Article news = new Article();
		
		news.setN_id(MpUtil.createPicId());
		news.setN_title(title);
		news.setN_body(body);
		news.setN_abstract(abstr);
		newsDao.createNews(news);
		newsDao.deleNewsHome(news.getN_type());
		result.setMsg("新闻添加成功！");
		result.setStatus(1);
		return result;
	}
	public MpResult listHomeNews(){
		List<Map<String,String>> newsList ;
		MpResult result = new MpResult();
		newsList = newsDao.findHomeNews();
		result.setData(newsList);
		result.setMsg("加载成功");
		result.setStatus(1);
		return result;
		
	}
	@Override
	public MpResult removeRecommend(String id) {
		MpResult result = new MpResult();
		newsDao.removeRecommend(id);
		result.setMsg("移除热点成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult setRecommend(String id) {
		MpResult result = new MpResult();
		newsDao.setRecommend(id);
		result.setMsg("设置热点成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult deleNews(String id) {
		MpResult result = new MpResult();
		newsDao.deleNews(id);
		result.setMsg("新闻删除成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	/**
	 * 推荐新闻列表
	 */
	public MpResult findRecommendNews(int page) {
		List<Map<String,String>> newsList ;
		MpResult result = new MpResult();
		int start =page*10-10;
		newsList=newsDao.findRecommendNews(start);
		result.setMsg("加载成功！");
		result.setData(newsList);
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult findPreNews(String time) {
		// TODO Auto-generated method stub
		Map<String,String> news ;
		MpResult result = new MpResult();
		news=newsDao.findPreNews(time);
		result.setMsg("加载成功！");
		result.setData(news);
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult findNextNews(String time) {
		// TODO Auto-generated method stub
		Map<String,String> news ;
		MpResult result = new MpResult();
		news=newsDao.findNextNews(time);
		result.setMsg("加载成功！");
		result.setData(news);
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
		newsDao.updateClick(id);
		result.setMsg("更新成功");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult listHotNews() {
		// TODO Auto-generated method stub
		MpResult result = new MpResult();
		result.setData(newsDao.findHotNews());
		result.setMsg("加载成功！");
		result.setStatus(1);
		return result;
	}
	@Override
	public MpResult editNews(String id,String title, String body, String abstr,String time){
		// TODO Auto-generated method stub
		MpResult result=new MpResult();
		Article news = new Article();
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
		news.setN_createtime(dateTime); 
		news.setN_id(id);
		news.setN_title(title);
		news.setN_body(body);
		news.setN_abstract(abstr);
		news=MpUtil.viewCreate(news);
		newsDao.editNews(news);
		result.setMsg("更新成功！");
		result.setStatus(1);
		return result;
	}

}
