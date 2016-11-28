package cn.mopow.dao;

import cn.mopow.entity.MpAdmin;

public interface UserDao {
	public MpAdmin findByName(String name);
}
