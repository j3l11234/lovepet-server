package com.j3l11234.lovepet.mapper;

import java.util.Date;
import java.util.List;

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
public interface UserFollowMapper {
	
	UserFollowEntity getFollow(@Param("id") int id);
	
	List<UserFollowEntity> getFollowUser(@Param("userId") int userId);
	
	int hasFollow(@Param("userId") int userId, @Param("fansId") int fansId);
}
