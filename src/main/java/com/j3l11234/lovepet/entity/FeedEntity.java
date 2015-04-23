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
public class FeedEntity {
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
	@Column(name = "feed_id", unique = true, nullable = false)
	private int feedId;
	 
	@Column(name = "user_id")
	private int userId;

	@Column(name = "feed_content")
	private String content;
	
	@Column(name = "feed_photo")
	private String photo;
	
	@Column(name = "feed_original")
	private int original;
	
	@Column(name = "feed_fav_num")
	private int favNum;
	
	@Column(name = "feed_reply_num")
	private int replyNum;

	@Column(name = "feed_repost_num")
	private int repostNum;
	
	@Column(name = "feed_submit_time")
	private java.util.Date submitTime;
	
	
	public int getId() {
		return feedId;
	}


	public void setId(int id) {
		this.feedId = id;
	}


	public int getUser_id() {
		return userId;
	}


	public void setUser_id(int user_id) {
		this.userId = user_id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public int getOriginal() {
		return original;
	}


	public void setOriginal(int original) {
		this.original = original;
	}


	public int getFav_num() {
		return favNum;
	}


	public void setFav_num(int fav_num) {
		this.favNum = fav_num;
	}


	public int getReply_num() {
		return replyNum;
	}


	public void setReply_num(int reply_num) {
		this.replyNum = reply_num;
	}


	public int getRepost_num() {
		return repostNum;
	}


	public void setRepost_num(int repost_num) {
		this.repostNum = repost_num;
	}


	public Date getSubmit_time() {
		return submitTime;
	}


	public void setSubmit_time(Date submit_time) {
		this.submitTime = submit_time;
	}


	

	public static boolean checkPrivilege(int userPrivilege, int privilege) {
		return (userPrivilege & privilege) == privilege;
	}
}
