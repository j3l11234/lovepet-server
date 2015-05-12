package com.j3l11234.lovepet.entity;

import java.util.Date;

public class UserFollowEntity {

	private int id;
	private int followFansId;
	private int followUserId;
	private Date followTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFollowFansId() {
		return followFansId;
	}
	public void setFollowFansId(int followFansId) {
		this.followFansId = followFansId;
	}
	public int getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(int followUserId) {
		this.followUserId = followUserId;
	}
	public java.util.Date getFollowTime() {
		return followTime;
	}
	public void setFollowTime(java.util.Date followTime) {
		this.followTime = followTime;
	}
	
	
}
