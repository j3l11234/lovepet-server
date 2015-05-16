package com.j3l11234.lovepet.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.FeedFavEntity;
import com.j3l11234.lovepet.entity.FeedReplyEntity;

@Repository
public interface FeedFavMapper {
	
	FeedFavEntity getFeedFav(@Param("id") int id);
	
	FeedFavEntity checkFeedFav(FeedFavEntity feedfav);
	
	int addFeedFav(FeedFavEntity feedfav);	
	
	int deleteFeedFav(@Param("id") int id);
	
	
//	List<FeedReplyEntity> getFeedReplyList(
//			@Param("feedId") int feedId, 
//			@Param("limitStart")  int limitStart, 
//			@Param("limitEnd")  int limitEnd);
}
