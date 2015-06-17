package com.j3l11234.lovepet.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j3l11234.lovepet.entity.FeedFavEntity;
import com.j3l11234.lovepet.entity.UserEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;
import com.j3l11234.lovepet.model.UserModel;
import com.j3l11234.lovepet.util.MyException;
import com.j3l11234.lovepet.util.PrivilegeCheck;
import com.j3l11234.lovepet.util.RespondCode;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserModel userModel;


	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public Object login(
			@RequestParam(value ="username") String username,
			@RequestParam(value ="password") String password,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();

		if(username.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "请输入用户名");
			return retutrnMap;
		}
		if(password.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "请输入密码");
			return retutrnMap;
		}

		try {
			UserEntity userEntity = userModel.login(username, password);
			session.setAttribute("user", userEntity);
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", "登录成功");
		} catch (MyException e) {
			//e.printStackTrace();
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}

	
	@RequestMapping(value = "/logout",method = RequestMethod.POST)
	@ResponseBody
	public Object logout(HttpSession session) {
		session.removeAttribute("user");
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		retutrnMap.put("error", RespondCode.OK);
		retutrnMap.put("data", "注销成功");
		return retutrnMap;
	}

	
	@RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object getUserInfo(
			@RequestParam(value ="user_id") int userID,
			HttpSession session) {
		
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		HashMap<String, Object> userInfo = null;
		try {
			userInfo = userModel.getUserInfo(userID, user.getId());
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", userInfo);
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			
		}
		return retutrnMap;
	}
	
	@RequestMapping(value = "/followUser", method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object followUser(
			@RequestParam(value ="user_id") int userId,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		UserFollowEntity userFollow = new UserFollowEntity();
		userFollow.setFollowUserId(userId);
		userFollow.setFollowFansId(user.getId());
		userFollow.setFollowTime(new Date());

		try {
			userFollow = userModel.followUser(userFollow);
			if(userFollow != null){
				retutrnMap.put("data", "关注成功");
			}else{
				retutrnMap.put("data", "取消关注成功");
			}
			retutrnMap.put("error", RespondCode.OK);
			
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	
	
//	@RequestMapping(value="/edit",  method = RequestMethod.POST)
//	public Object postedit(
//			@RequestParam(value="user_id", required = true) int user_id,
//			@RequestParam(value="username", required = true) String username,
//			@RequestParam(value="password", required = true) String password,
//			@RequestParam(value="alias", required = true) String alias,
//			ModelMap model,HttpSession session) {
//
//		UserEntity userEntity = null;
//		try {
//			userEntity = userModel.addUser(username, password, alias);
//
//			model.addAttribute("result", true);
//			model.addAttribute("resultText", "提交成功");
//		} catch (MyException e) {
//			model.addAttribute("result", false);
//			model.addAttribute("resultText", e.getMessage());
//			userEntity = new UserEntity();
//			e.printStackTrace();
//		}
//
//		return null;	
//	}


}
