package com.j3l11234.lovepet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
public class UserFollowEntity {
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
	@Column(name = "follow_id", unique = true, nullable = false)
	private int id;
	 
	@Column(name = "follow_fans_id")
	private int followFansId;
	
	@Column(name = "follow_user_id")
	private int followUserId;
	
	@Column(name = "follow_time")
	private java.util.Date followTime;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public int getFollow_fans_id() {
		return followUserId;
	}


	public void setFollow_fans_id(int follow_fans_id) {
		this.followUserId = follow_fans_id;
	}

	public int getFollow_user_id() {
		return followUserId;
	}


	public void setFollow_user_id(int follow_user_id) {
		this.followUserId = follow_user_id;
	}
	
	public Date getFollow_time() {
		return followTime;
	}


	public void setFollow_time(Date follow_time) {
		this.followTime = follow_time;
	}


	public static boolean checkPrivilege(int userPrivilege, int privilege) {
		return (userPrivilege & privilege) == privilege;
	}
}
