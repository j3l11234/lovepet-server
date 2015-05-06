package com.j3l11234.lovepet.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;  
import org.apache.ibatis.annotations.Results;  
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.UserEntity;

@Repository
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE user_id = #{id}")
	@Results(value = {
			@Result(id = true, property = "id", column = "user_id"),
			@Result(property = "username", column = "user_name"),
			@Result(property = "email", column = "user_email"),
			@Result(property = "pwd", column = "user_pwd"),
			@Result(property = "alias", column = "user_alias"),
			@Result(property = "privilege", column = "user_privilege"),
			@Result(property = "portrait", column = "user_portrait"),
			@Result(property = "user_sex", column = "user_sex"),			
			@Result(property = "realName", column = "user_real_name"),
			@Result(property = "phone", column = "user_phone"),
			@Result(property = "followNum", column = "user_follow_num"),
			@Result(property = "fansNum", column = "user_fans_num"),
	})
	UserEntity getUser(@Param("id") int id);

	@Select("SELECT user_id,user_privilege FROM user WHERE user_name = #{username} AND user_pwd = #{password}") 
	@Results(value = {
			@Result(id = true, property = "id", column = "user_id"),
			@Result(property = "username", column = "user_name"),
			@Result(property = "privilege", column = "user_privilege"),
	})
	UserEntity login(
			@Param("username") String username, 
			@Param("password") String password);
	
	@Select("SELECT * FROM user WHERE user_id = #{id}")
	@Results(value = {
			@Result(id = true, property = "id", column = "user_id"),
			@Result(property = "username", column = "user_name"),
			@Result(property = "email", column = "user_email"),
			@Result(property = "alias", column = "user_alias"),
			@Result(property = "portrait", column = "user_portrait"),
			@Result(property = "sex", column = "user_sex"),			
			@Result(property = "realName", column = "user_real_name"),
			@Result(property = "phone", column = "user_phone"),
			@Result(property = "followNum", column = "user_follow_num"),
			@Result(property = "fansNum", column = "user_fans_num"),
	})
	UserEntity getUserInfo(@Param("id") int id);
}
