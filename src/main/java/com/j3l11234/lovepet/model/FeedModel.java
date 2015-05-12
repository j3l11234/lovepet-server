package com.j3l11234.lovepet.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;
import com.j3l11234.lovepet.mapper.FeedMapper;
import com.j3l11234.lovepet.mapper.UserFollowMapper;
import com.j3l11234.lovepet.util.MyException;



@Service
public class FeedModel {

	@Autowired  
	private FeedMapper feedMapper; 
	@Autowired  
	private UserFollowMapper userFollowMapper;
	
	public FeedEntity postFeed(FeedEntity feed) throws MyException{
		try {
			int result = feedMapper.addFeed(feed);
			System.out.println(result);
			if(result != 1){
				throw new MyException("数据插入错误");
			}
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feed;
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws MyException
	 */
	public List<Map<String, Object>> getFollowFeed(int userId) throws MyException{
		List<Map<String, Object>> feedList;
		try {
			List<UserFollowEntity> userFollowList = userFollowMapper.getFollowUser(userId);
			UserFollowEntity userFollow = new UserFollowEntity();
			userFollow.setFollowFansId(userId);
			userFollow.setFollowUserId(userId);
			userFollowList.add(userFollow);
			feedList = feedMapper.getFeedFollow(userFollowList);
//		} catch (MyException e) {
//			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feedList;
	}
}
