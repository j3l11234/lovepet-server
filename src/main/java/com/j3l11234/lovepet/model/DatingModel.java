package com.j3l11234.lovepet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j3l11234.lovepet.entity.DatingEntity;
import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.FeedFavEntity;
import com.j3l11234.lovepet.entity.FeedReplyEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;
import com.j3l11234.lovepet.mapper.DatingMapper;
import com.j3l11234.lovepet.mapper.FeedFavMapper;
import com.j3l11234.lovepet.mapper.FeedMapper;
import com.j3l11234.lovepet.mapper.FeedReplyMapper;
import com.j3l11234.lovepet.mapper.UserFollowMapper;
import com.j3l11234.lovepet.mapper.UserMapper;
import com.j3l11234.lovepet.util.MyException;



@Service
public class DatingModel {
	@Autowired  
	private DatingMapper datingMapper;
	
	@Transactional(rollbackFor=MyException.class)
	public DatingEntity addDating(DatingEntity dating) throws MyException{
		try {
			int result = datingMapper.addDating(dating);
			if(result != 1){
				throw new MyException("数据插入错误");
			}
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return dating;
	}
	
	
	public List<Map<String, Object>> getDatingList(int perPage, int page) throws MyException{
		List<Map<String, Object>> datingList;
		try {
			datingList = datingMapper.getDatingList(perPage*(page-1), perPage*page);
		//} catch (MyException e) {
		//	throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return datingList;
	}
	
}
