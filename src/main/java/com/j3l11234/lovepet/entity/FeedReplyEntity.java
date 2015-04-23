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
public class FeedReplyEntity {
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
	@Column(name = "freply_id", unique = true, nullable = false)
	private int id;
	 
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "feed_id")
	private int feedId;
	
	@Column(name = "freply_submit_time")
	private java.util.Date freplySubmitTime;
	
	@Column(name = "freply_content")
	private String freplyContent;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getUser_id() {
		return userId;
	}


	public void setUser_id(int user_id) {
		this.userId = user_id;
	}

	public int getFeed_id() {
		return feedId;
	}


	public void setFeed_id(int feed_id) {
		this.feedId = feed_id;
	}
	
	public Date getFreplySubmit_time() {
		return freplySubmitTime;
	}


	public void setFreplySubmit_time(Date submit_time) {
		this.freplySubmitTime = submit_time;
	}

	public String getFreply_content() {
		return freplyContent;
	}
	
	public void setFreply_content(String freply_content) {
		this.freplyContent = freply_content;
	}
	
	

	public static boolean checkPrivilege(int userPrivilege, int privilege) {
		return (userPrivilege & privilege) == privilege;
	}
}
