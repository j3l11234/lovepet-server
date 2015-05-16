package com.j3l11234.lovepet.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;

@Repository
public interface FeedMapper {
	
	FeedEntity getFeed(@Param("id") int id);
	
	int addFeed(FeedEntity feed);
	
	List<Map<String, Object>> getFeedFollow(
			@Param("followUserList") List<UserFollowEntity> followUserList, 
			@Param("userId") int userId);

	int addReplyNum(@Param("id") int id);
	
	int addRepostNum(@Param("id") int id);
	
	int addFavNum(@Param("id") int id);
	
	int minusFavNum(@Param("id") int id);
}
