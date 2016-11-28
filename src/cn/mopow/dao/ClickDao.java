package cn.mopow.dao;

import java.util.List;

import cn.mopow.entity.MpClick;

public interface ClickDao {
	public void newClick(MpClick click);
	public void clickPlusOne(String id);
	public void clickPlusByUrl(String url);
	public MpClick findById(String id);
	public List<MpClick> findAllClick();
	public void listClickUpdate(String name);
	public void videoClickUpdate(String url);
}
