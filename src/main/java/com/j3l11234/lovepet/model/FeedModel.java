package com.j3l11234.lovepet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.FeedFavEntity;
import com.j3l11234.lovepet.entity.FeedReplyEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;
import com.j3l11234.lovepet.mapper.FeedFavMapper;
import com.j3l11234.lovepet.mapper.FeedMapper;
import com.j3l11234.lovepet.mapper.FeedReplyMapper;
import com.j3l11234.lovepet.mapper.UserFollowMapper;
import com.j3l11234.lovepet.util.MyException;



@Service
public class FeedModel {

	@Autowired  
	private FeedMapper feedMapper; 
	@Autowired  
	private UserFollowMapper userFollowMapper;
	@Autowired 
	private FeedReplyMapper feedReplyMapper;
	@Autowired 
	private FeedFavMapper feedFavMapper;
	
	@Transactional(rollbackFor=MyException.class)
	public FeedEntity postFeed(FeedEntity feed) throws MyException{
		try {
			int result = feedMapper.addFeed(feed);
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
			feedList = feedMapper.getFeedFollow(userFollowList, userId);
			if(feedList == null){
				feedList = new ArrayList<Map<String,Object>>();
			}
//		} catch (MyException e) {
//			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feedList;
	}
	
	@Transactional(rollbackFor=MyException.class) 
	public FeedReplyEntity replyFeed(FeedReplyEntity feedReply) throws MyException{
		try {
			FeedEntity feed = feedMapper.getFeed(feedReply.getFeedId());
			if(feed == null){
				throw new MyException("动态不存在");
			}
			//新增回复
			int result = feedReplyMapper.addFeedReply(feedReply);
			if(result != 1){
				throw new MyException("数据插入错误");
			}
			//增加回复数
			result = feedMapper.addReplyNum(feedReply.getFeedId());
			if(result != 1){
				throw new MyException("数据更新错误");
			}	
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feedReply;
	}
	
	
	public List<FeedReplyEntity> getFeedReply(int feedId, int perPage, int page) throws MyException{
		List<FeedReplyEntity> feedReplyList;
		try {
			FeedEntity feed = feedMapper.getFeed(feedId);
			if(feed == null){
				throw new MyException("动态不存在");
			}
			
			feedReplyList = feedReplyMapper.getFeedReplyList(feedId, perPage*(page-1), perPage*page);
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feedReplyList;
	}
	
	@Transactional(rollbackFor=MyException.class) 
	public FeedEntity repostFeed(FeedEntity feed) throws MyException{
		try {
			FeedEntity originalFeed = feedMapper.getFeed(feed.getOriginalId());
			if(originalFeed == null){
				throw new MyException("动态不存在");
			}
			//判断是否为原始微博
			if(originalFeed.getOriginalId() != null){
				originalFeed = feedMapper.getFeed(originalFeed.getOriginalId());
				if(originalFeed == null){
					throw new MyException("动态不存在");
				}
				feed.setOriginalId(originalFeed.getId());
			}	
			//插入转发
			int result = feedMapper.addFeed(feed);
			if(result != 1){
				throw new MyException("数据插入错误");
			}
			//增加转发数
			result = feedMapper.addRepostNum(feed.getOriginalId());
			if(result != 1){
				throw new MyException("数据更新错误");
			}	
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feed;
	}
	
	
	@Transactional(rollbackFor=MyException.class) 
	public FeedFavEntity favFeed(FeedFavEntity feedFav) throws MyException{
		try {
			//判断微博是否存在
			FeedEntity feed = feedMapper.getFeed(feedFav.getFeedId());
			if(feed == null){
				throw new MyException("动态不存在");
			}
			FeedFavEntity alreadyFav = feedFavMapper.checkFeedFav(feedFav);
			int result;
			if(alreadyFav == null){ //未点赞
				result = feedFavMapper.addFeedFav(feedFav);
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				//增加转发数
				result = feedMapper.addFavNum(feedFav.getFeedId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
			}else{ //已点赞
				result = feedFavMapper.deleteFeedFav(alreadyFav.getId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				//减少转发数
				result = feedMapper.minusFavNum(feedFav.getFeedId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				feedFav = null;
			}
			if(result != 1){
				throw new MyException("数据插入错误");
			}
				
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return feedFav;
	}
}
