package com.j3l11234.lovepet.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.j3l11234.lovepet.entity.MsgEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;


@Repository
public interface MsgMapper {
	
	
	int addMsg(MsgEntity msg);
	
	List<Map<String, Object>> getMsgList(
			@Param("userId") int userId);

	
	
}
