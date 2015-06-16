package com.j3l11234.lovepet.controller;

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

import com.j3l11234.lovepet.entity.UserEntity;
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
	public Object getUserInfo(HttpSession session) {
		session.removeAttribute("user");
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		retutrnMap.put("error", RespondCode.OK);
		retutrnMap.put("data", "注销成功");
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
