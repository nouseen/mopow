package cn.mopow.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import cn.mopow.dao.PictureDao;
import cn.mopow.entity.MpResult;
import cn.mopow.entity.Picture;
import cn.mopow.entity.ShowPages;
import cn.mopow.util.MpUtil;

import com.alibaba.fastjson.JSONObject;
@Service
public class PicturServiceImpl implements PictureService{
		@Resource
		private PictureDao pictureDao;
		private int pageSize = 12; // 每页显示8条记录
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
		public void savePic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			Picture pic=new Picture();
			// TODO Auto-generated method stub
			try {
		   req.setCharacterEncoding("utf-8");  
	       resp.setContentType("text/html;charset=utf-8");  
	       ServletContext servletContext = req.getSession().getServletContext();
	       //为解析类提供配置信息  
	       DiskFileItemFactory factory = new DiskFileItemFactory();  
	       //创建解析类的实例  
	       ServletFileUpload sfu = new ServletFileUpload(factory);  
	       //开始解析  
	       sfu.setFileSizeMax(2048*1000);  
	       //每个表单域中数据会封装到一个对应的FileItem对象上  
	        
	       List<FileItem> items = sfu.parseRequest(req);  
	       //区分表单域  
	       for (int i = 0; i < items.size(); i++) {  
	               FileItem item = items.get(i);  
	               //isFormField为true，表示这不是文件上传表单域  
	               if(!item.isFormField()){  
	                   ServletContext sctx = servletContext;  
	                   //获得存放文件的物理路径  
	                   //upload下的某个文件夹   得到当前在线的用户  找到对应的文件夹  
	                   String path = sctx.getRealPath("/upload");  
	                   //获得文件名  
	                   String fileName = item.getName();  
	                   System.out.println(fileName);
	                   //该方法在某些平台(操作系统),会返回路径+文件名  
	                   fileName = fileName.substring(fileName.lastIndexOf("/")+1);  
	                   //测试路径
	                   String  path1="D:\\mkdepo\\";
	                   String id=MpUtil.createPicId();
	                   pic.setP_id(id);
	                   fileName=fileName.replaceAll("\\.[\\s\\S]{0,5}", "");
	                   System.out.println("文件名："+fileName);
	                   pic.setP_name(fileName);
	                   pic.setP_localSite(path1+id+".png");
	                   pic.setP_url("upload/"+id+".png");
	                   File file_tomcat = new File(path+"\\"+id+".png"); 
	                   
	                   File file_local = new File(path1+id+".png");
	                   System.out.println(path+"\\"+id+".png");
	                   if(!file_tomcat.exists()){ 
		                   	file_tomcat.createNewFile();
		                   	file_local.createNewFile();
		                    item.write(file_tomcat);  
		                    item.write(file_local);
		                    pictureDao.createPicture(pic);
		                       //将上传图片的名字记录到数据库中  
	                        System.out.println("图片上传成功");
	                        
	                   }  
	               }  
	           }  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("result", "ok"); 
			resp.getWriter().write(jsonObject.toString());  
	}
		
		
		/**
		 * 查询未删除的图片列表
		 */
		public MpResult listPic(int pageNow,int isdel){
			MpResult result = new MpResult();
			if(pageNow<=0){
				pageNow=1;
			}
			int n=pageNow*pageSize-pageSize;
			int m=pictureDao.conutPic(isdel);
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
			result.setData(pictureDao.findAllPic(n,pageSize,isdel));
			result.setMsg("加载成功！");
			result.setStatus(1);
			return result;
			
		}
		
		/**
		 * 图片列表分页（向页面发送封装好的页码）
		 */
		public MpResult picPage(int currentPage,int isdel){
			MpResult result = new MpResult();
			int m=pictureDao.conutPic(isdel);
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
		
		@Override
		public MpResult loadHomePic() {
			// TODO Auto-generated method stub
			MpResult result = new MpResult();
			List<Map<String,String>> pic=pictureDao.findHomePic();
			result.setMsg("加载成功！");
			result.setData(pic);
			result.setStatus(1);
			return result;
		}
		
		/**
		 * 把选中的图片移入回收站
		 */
		@Override
		public MpResult delPicHome(String id) {
			// TODO Auto-generated method stub
			  MpResult result = new MpResult();
			  String[] idArray = id.split(",");
			  
		        if (!"".equals(idArray[0])) {
		            for (int i = 0; i < idArray.length; i++) {
		            	System.out.println(idArray[i]);
		                pictureDao.delPicHome(idArray[i]);
		            }
		      }
			result.setMsg("加载成功！");
			result.setStatus(1);
			return result;
		}

		@Override
		public MpResult setPicHome(String id,String home) {
			MpResult result = new MpResult();
			Map<String,String> map=new HashMap<String,String>();
			map.put("home", home);
			map.put("id", id);
			pictureDao.setPicHome(map);
			result.setMsg("加载成功！");
			result.setStatus(1);
			return result;
		}


		/**
		 * 彻底删除
		 */
		@Override
		public MpResult deletePic(String id) {
			// TODO Auto-generated method stub
			  MpResult result = new MpResult();
			  String[] idArray = id.split(",");
			  
		        if (!"".equals(idArray[0])) {
		            for (int i = 0; i < idArray.length; i++) {
		            	System.out.println(idArray[i]);
		                pictureDao.deletePic(idArray[i]);
		            }
		      }
			result.setMsg("删除成功！");
			result.setStatus(1);
			return result;
		}


		/**
		 * 还原图片
		 */
		@Override
		public MpResult huanyuanPic(String id) {
			// TODO Auto-generated method stub
			  MpResult result = new MpResult();
			  String[] idArray = id.split(",");
			  
		        if (!"".equals(idArray[0])) {
		            for (int i = 0; i < idArray.length; i++) {
		            	System.out.println(idArray[i]);
		                pictureDao.huanyuanPic(idArray[i]);
		            }
		      }
			result.setMsg("删除成功！");
			result.setStatus(1);
			return result;
		}
		
		
}
