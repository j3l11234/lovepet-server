package com.j3l11234.lovepet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	/**
	 * 普通用户
	 */
	public static final int USER = 0x00000001;
	/**
	 * 内容管理权限
	 */
	public static final int ADMIN_CONTENT = 0x00000002;
	/**
	 * 系统管理权限
	 */
	public static final int ADMIN_SYSTEM = 0x00000004;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int id;
	 
	@Column(name = "user_name")
	private String username;

	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_pwd")
	private String pwd;
	
	@Column(name = "user_alias")
	private String alias;
	
	@Column(name = "user_privilege")
	private int privilege;
	
	@Column(name = "user_portrait")
	private String portrait;

	@Column(name = "user_sex")
	private int sex;
	
	@Column(name = "user_real_name")
	private String realName;
	
	@Column(name = "user_phone")
	private String phone;
	
	@Column(name = "user_follow_num")
	private int followNum;
	
	@Column(name = "user_fans_num")
	private int fansNum;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public int getPrivilege() {
		return privilege;
	}


	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}


	public String getPortrait() {
		return portrait;
	}


	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getFollowNum() {
		return followNum;
	}


	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}


	public int getFansNum() {
		return fansNum;
	}


	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}


	public static boolean checkPrivilege(int userPrivilege, int privilege) {
		return (userPrivilege & privilege) == privilege;
	}
}
