package com.j3l11234.lovepet.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;

@Repository
public interface FeedMapper {
	
	FeedEntity getFeed(@Param("id") int id);
	
	int addFeed(FeedEntity feed);
	
	List<Map<String, Object>> getFeedFollow(@Param("followUserList") List<UserFollowEntity> followUserList);
}
