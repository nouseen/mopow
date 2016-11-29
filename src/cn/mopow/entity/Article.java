package cn.mopow.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article  implements Serializable {
	private String n_id;
	private String  n_title;
	private String n_body;
	private Timestamp n_createtime; 
	private String n_position;
	private String n_view;
	private String n_img;
	private String n_abstract;
	public String getN_id() {
		return n_id;
	}
	public void setN_id(String n_id) {
		this.n_id = n_id;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_body() {
		return n_body;
	}
	public void setN_body(String n_body) {
		this.n_body = n_body;
	}
	public Timestamp getN_createtime() {
		return n_createtime;
	}
	public void setN_createtime(Timestamp n_createtime) {
		this.n_createtime = n_createtime;
	}
	public String getN_position() {
		return n_position;
	}
	public void setN_position(String n_position) {
		this.n_position = n_position;
	}
	public String getN_view() {
		return n_view;
	}
	public void setN_view(String n_view) {
		this.n_view = n_view;
	}
	public String getN_img() {
		return n_img;
	}
	public void setN_img(String n_img) {
		this.n_img = n_img;
	}
	public String getN_abstract() {
		return n_abstract;
	}
	public void setN_abstract(String n_abstract) {
		this.n_abstract = n_abstract;
	}
}
