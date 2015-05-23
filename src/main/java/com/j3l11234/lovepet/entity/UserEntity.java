package com.j3l11234.lovepet.entity;

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
	

	private int id;
	private String username;
	private String email;
	private String pwd;
	private String alias;
	private int privilege;
	private String portrait;
	private int sex;
	private String realName;
	private String phone;
	private String profile;
	private int feedMum;
	private int followNum;
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



	public String getProfile() {
		return profile;
	}



	public void setProfile(String profile) {
		this.profile = profile;
	}



	public int getFeedMum() {
		return feedMum;
	}



	public void setFeedMum(int feedMum) {
		this.feedMum = feedMum;
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
