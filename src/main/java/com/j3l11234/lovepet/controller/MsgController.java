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
import com.j3l11234.lovepet.entity.MsgEntity;
import com.j3l11234.lovepet.entity.UserEntity;
import com.j3l11234.lovepet.model.FeedModel;
import com.j3l11234.lovepet.model.MsgModel;
import com.j3l11234.lovepet.model.UserModel;
import com.j3l11234.lovepet.util.MyException;
import com.j3l11234.lovepet.util.PrivilegeCheck;
import com.j3l11234.lovepet.util.RespondCode;


@Controller
@RequestMapping("/msg")
public class MsgController {
	@Autowired
	private UserModel userModel;
	@Autowired
	private MsgModel msgModel;
	
	/*发消息，需给出接收人ID*/
	@RequestMapping(value = "/postMsg",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object postMsg(
			@RequestParam(value ="receiveuser") int receiveUserId,
			@RequestParam(value ="content") String content,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		//UserEntity reuser = (UserEntity) session.getAttribute("reuser");
		
		if(content.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "提交信息有误");
			return retutrnMap;
		}
		
		MsgEntity msg = new MsgEntity();
		msg.setUserId(user.getId());
		msg.setReceiveUserId(receiveUserId);
		msg.setContent(content);
		msg.setDatetime(new Date());
		
		try {
			msgModel.postMsg(msg);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", "发布成功");
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	/*获得当前登录者收到的所有消息列表*/
	@RequestMapping(value = "/getMsgList",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object getMsgList(HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		
		try {
			List<Map<String, Object>> msgList = msgModel.getMsgList(user.getId());
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", msgList);
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
}
