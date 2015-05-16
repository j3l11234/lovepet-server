package com.j3l11234.lovepet.entity;

import java.util.Date;

public class FeedFavEntity{
	private int id;
	private int userId;
	private int feedId;
	private Date favTime;
	
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
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public Date getFavTime() {
		return favTime;
	}
	public void setFavTime(Date favTime) {
		this.favTime = favTime;
	}
	
	

}