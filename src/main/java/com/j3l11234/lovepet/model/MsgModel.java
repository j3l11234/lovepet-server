package com.j3l11234.lovepet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.FeedReplyEntity;
import com.j3l11234.lovepet.entity.MsgEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;
import com.j3l11234.lovepet.mapper.FeedFavMapper;
import com.j3l11234.lovepet.mapper.FeedMapper;
import com.j3l11234.lovepet.mapper.FeedReplyMapper;
import com.j3l11234.lovepet.mapper.MsgMapper;
import com.j3l11234.lovepet.mapper.UserFollowMapper;
import com.j3l11234.lovepet.mapper.UserMapper;
import com.j3l11234.lovepet.util.MyException;


@Service
public class MsgModel {
	@Autowired  
	private UserMapper userMapper;
	
	@Autowired 
	private MsgMapper msgMapper;
	
	@Transactional(rollbackFor=MyException.class)
	public void postMsg(MsgEntity msg) throws MyException{
		try {
			int result = msgMapper.addMsg(msg);
			if(result != 1){
				throw new MyException("数据插入错误");
			}
			
			if(result != 1){
				throw new MyException("数据更新错误");
			}
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws MyException
	 */
	public  List<Map<String, Object>> getMsgList(int userId) throws MyException{
		//List<Map<String, Object>> msgList;
		List<Map<String, Object>> msgList = null;
		try {
			msgList = msgMapper.getMsgList(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return msgList;
	}
	
	
}
