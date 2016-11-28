package cn.mopow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.mopow.entity.Picture;

public interface PictureDao {
	public void createPicture(Picture pic);
	public List<Map<String,String>> findHomePic();
	public void setPicHome(Map<String,String> picMap);
	public void delPicHome(String id);
	public int conutPic(@Param("isdel")int isdel);
	public List<Map<String,String>> findAllPic(@Param("n")int n,@Param("pageSize")int pageSize,@Param("isdel")int isdel);
	public void deletePic(String id);
	public void huanyuanPic(String id);

}
