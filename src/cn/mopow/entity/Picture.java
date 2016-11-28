package cn.mopow.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Picture implements Serializable{
	private String p_id;
	private String p_url;
	private String p_localSite;
	private String p_name;
	private Timestamp p_createtime;
	private int p_home;
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_url() {
		return p_url;
	}
	public void setP_url(String p_url) {
		this.p_url = p_url;
	}
	public String getP_localSite() {
		return p_localSite;
	}
	public void setP_localSite(String p_localSite) {
		this.p_localSite = p_localSite;
	}
	public int getP_home() {
		return p_home;
	}
	public void setP_home(int p_home) {
		this.p_home = p_home;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String name) {
		this.p_name = name;
	}
	public Timestamp getP_createtime() {
		return p_createtime;
	}
	public void setP_createtime(Timestamp p_createtime) {
		this.p_createtime = p_createtime;
	}
	
}
