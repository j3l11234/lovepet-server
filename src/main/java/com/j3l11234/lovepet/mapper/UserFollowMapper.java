package com.j3l11234.lovepet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.UserFollowEntity;

@Repository
public interface UserFollowMapper {
	
	UserFollowEntity getFollow(@Param("id") int id);
	
	List<UserFollowEntity> getFollowUser(@Param("userId") int userId);
	
	UserFollowEntity hasFollow(@Param("userId") int userId, @Param("fansId") int fansId);
	
	int addUserFollow(UserFollowEntity userfollow);
	
	int deleteUserFollow(@Param("id") int id);
	
}
