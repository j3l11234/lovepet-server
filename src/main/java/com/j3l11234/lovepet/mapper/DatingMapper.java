package com.j3l11234.lovepet.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.DatingEntity;
import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;

@Repository
public interface DatingMapper {
	
	FeedEntity getDating(@Param("id") int id);
	
	int addDating(DatingEntity dating);
	
	List<Map<String, Object>> getDatingList(
			@Param("limitStart")  int limitStart, 
			@Param("limitEnd")  int limitEnd);
}
