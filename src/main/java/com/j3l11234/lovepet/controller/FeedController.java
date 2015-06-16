package com.j3l11234.lovepet.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.FeedFavEntity;
import com.j3l11234.lovepet.entity.FeedReplyEntity;
import com.j3l11234.lovepet.entity.UserEntity;
import com.j3l11234.lovepet.model.FeedModel;
import com.j3l11234.lovepet.model.UserModel;
import com.j3l11234.lovepet.util.MyException;
import com.j3l11234.lovepet.util.PrivilegeCheck;
import com.j3l11234.lovepet.util.RespondCode;



@Controller
@RequestMapping("/feed")
public class FeedController {
	@Autowired
	private UserModel userModel;
	@Autowired
	private FeedModel feedModel;
	
	
	@RequestMapping(value = "/postFeed",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object postFeed(
			@RequestParam(value ="content") String content,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		if(content.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "提交信息有误");
			return retutrnMap;
		}
		
		FeedEntity feed = new FeedEntity();
		feed.setUserId(user.getId());
		feed.setContent(content);
		feed.setSubmitTime(new Date());
		
		try {
			feedModel.postFeed(feed);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", "发布成功");
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	@RequestMapping(value = "/getFollowFeed",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object getFollowFeed(HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		
		try {
			List<Map<String, Object>> feedList = feedModel.getFollowFeed(user.getId());
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", feedList);
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	@RequestMapping(value = "/replyFeed", method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object replyFeed(
			@RequestParam(value ="feed_id") int feedId,
			@RequestParam(value ="content") String content,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		if(content.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "提交信息有误");
			return retutrnMap;
		}
		
		FeedReplyEntity feedReply = new FeedReplyEntity();
		feedReply.setUserId(user.getId());
		feedReply.setContent(content);
		feedReply.setFeedId(feedId);
		feedReply.setSubmitTime(new Date());

		try {
			feedModel.replyFeed(feedReply);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", "提交成功");
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	
	@RequestMapping(value = "/getFeedReply",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object getFeedReply(
			@RequestParam(value ="feed_id") int feedId,
			@RequestParam(value ="per_page", required=false, defaultValue="10") int perPage,
			@RequestParam(value ="page", required=false, defaultValue="1") int page,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		//UserEntity user =  (UserEntity) session.getAttribute("user");
		
		try {
			List<Map<String, Object>> feedReplyList = feedModel.getFeedReply(feedId, perPage, page);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", feedReplyList);
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	@RequestMapping(value = "/repostFeed", method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object repostFeed(
			@RequestParam(value ="original_id") int originalId,
			@RequestParam(value ="content") String content,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		if(content.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "提交信息有误");
			return retutrnMap;
		}
		
		FeedEntity feed = new FeedEntity();
		feed.setUserId(user.getId());
		feed.setContent(content);
		feed.setOriginalId(originalId);
		feed.setSubmitTime(new Date());

		try {
			feedModel.repostFeed(feed);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", "提交成功");
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	@RequestMapping(value = "/favFeed", method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object favFeed(
			@RequestParam(value ="feed_id") int feedId,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		
		FeedFavEntity feedFav = new FeedFavEntity();
		feedFav.setFeedId(feedId);
		feedFav.setUserId(user.getId());
		feedFav.setFavTime(new Date());

		try {
			feedFav = feedModel.favFeed(feedFav);
			if(feedFav != null){
				retutrnMap.put("data", "点赞成功");
			}else{
				retutrnMap.put("data", "取消成功");
			}
			retutrnMap.put("error", RespondCode.OK);
			
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	
}
