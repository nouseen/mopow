package cn.mopow.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mopow.dao.ClickDao;
import cn.mopow.entity.MpClick;
import cn.mopow.entity.MpResult;
import cn.mopow.util.MpUtil;
@Service
public class ClickServiceImpl implements ClickService {
	@Resource
	private ClickDao clickDao;
	@Override
	public MpResult newClick(String name,String description,int type) {
		MpClick click = new MpClick();
		click.setC_id(MpUtil.createPicId());
		click.setC_description(description);
		click.setC_name(name);
		click.setC_type(type);
		clickDao.newClick(click);
		MpResult result = new MpResult();
		result.setMsg("新建成功");
		result.setStatus(1);
		return result;
	}

	@Override
	public MpResult updateClick(String id) {
		// TODO Auto-generated method stub
		MpResult result = new MpResult();
		clickDao.clickPlusOne(id);
		result.setStatus(1);
		result.setMsg("更新成功");
		return result;
	}

	@Override
	public MpResult listAllData() {
		MpResult result = new MpResult();
		List<MpClick> click = clickDao.findAllClick();
		result.setData(click);
		result.setMsg("加载成功！");
		result.setStatus(1);
		return result;
		
	}

	@Override
	public MpResult findAClick(String id) {
		MpResult result = new MpResult();
		result.setData(clickDao.findById(id));
		result.setMsg("加载成功");
		result.setStatus(1);
		return result;
	}

	@Override
	public MpResult updateClickUrl(String url) {
		MpResult result = new MpResult();
		clickDao.clickPlusByUrl(url);
		clickDao.videoClickUpdate(url);
		result.setStatus(1);
		result.setMsg("更新成功");
		return result;
	}

	@Override
	public MpResult updateListByName(String name) {
		MpResult result = new MpResult();
		clickDao.listClickUpdate(name);
		result.setStatus(1);
		result.setMsg("更新成功！");
		return result;
	}

	
}
