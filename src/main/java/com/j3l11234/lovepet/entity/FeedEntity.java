package com.j3l11234.lovepet.entity;

import java.util.Date;

public class FeedEntity {

	private int id;
	private int userId;
	private String content;
	private String photo;
	private Integer originalId;
	private int favNum;
	private int replyNum;
	private int repostNum;
	private Date submitTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Integer getOriginalId() {
		return originalId;
	}
	public void setOriginalId(Integer originalId) {
		this.originalId = originalId;
	}
	public int getFavNum() {
		return favNum;
	}
	public void setFavNum(int favNum) {
		this.favNum = favNum;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getRepostNum() {
		return repostNum;
	}
	public void setRepostNum(int repostNum) {
		this.repostNum = repostNum;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

}
