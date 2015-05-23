package com.j3l11234.lovepet.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.UserEntity;

@Repository
public interface UserMapper {
	
//	@Select("SELECT * FROM user WHERE user_id = #{id} LIMIT 0,1")
//	@Results(value = {
//			@Result(id = true, property = "id", column = "user_id"),
//			@Result(property = "username", column = "user_name"),
//			@Result(property = "email", column = "user_email"),
//			@Result(property = "pwd", column = "user_pwd"),
//			@Result(property = "alias", column = "user_alias"),
//			@Result(property = "privilege", column = "user_privilege"),
//			@Result(property = "portrait", column = "user_portrait"),
//			@Result(property = "user_sex", column = "user_sex"),			
//			@Result(property = "realName", column = "user_real_name"),
//			@Result(property = "phone", column = "user_phone"),
//			@Result(property = "followNum", column = "user_follow_num"),
//			@Result(property = "fansNum", column = "user_fans_num"),
//	})
	
	UserEntity getUser(@Param("id") int id);

	UserEntity getUserLogin(
			@Param("username") String username, 
			@Param("password") String password);
	
	HashMap<String, Object> getProfileInfo(@Param("id") int id);
	
	HashMap<String, Object> getUserInfo(@Param("id") int id);
	
	int addFeedNum(@Param("id") int id);
	
	int minusFeedNum(@Param("id") int id);
	
	int addFollowNum(@Param("id") int id);
	
	int minusFollowNum(@Param("id") int id);
	
	int addFansNum(@Param("id") int id);
	
	int minusFansNum(@Param("id") int id);
	
}
