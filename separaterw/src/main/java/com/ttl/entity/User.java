package com.ttl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="sys_user")
public class User {

	@Id
    @Column(name = "id")
	private String id;
	@Column(name = "nickname")
    private String nickname;
	@Column(name = "REALNAME")
    private String realname;
	@Column(name = "password")
	@Expose
    private String password;
	public User() {
		super();
	}
	public User(String id, String nickname, String realname, String password) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.realname = realname;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", realname=" + realname + ", password=" + password + "]";
	}
	
}
