package com.j3l11234.lovepet.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.FeedReplyEntity;

@Repository
public interface FeedReplyMapper {
	
	FeedReplyEntity getFeedReply(@Param("id") int id);
	
	int addFeedReply(FeedReplyEntity feedReply);
	
	List<FeedReplyEntity> getFeedReplyList(
			@Param("feedId") int feedId, 
			@Param("limitStart")  int limitStart, 
			@Param("limitEnd")  int limitEnd);
}
