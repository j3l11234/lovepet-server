package com.j3l11234.lovepet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j3l11234.lovepet.entity.UserEntity;
import com.j3l11234.lovepet.model.UserModel;
import com.j3l11234.lovepet.util.MyException;
import com.j3l11234.lovepet.util.PrivilegeCheck;
import com.j3l11234.lovepet.util.RespondCode;


@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private UserModel userModel;

	@RequestMapping("/getProfileInfo")
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object getProfileInfo(HttpSession session) {
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");

		try {
			user = userModel.getUserInfo(user.getId());
			retutrnMap.put("success", RespondCode.OK);
			retutrnMap.put("data", user);
		} catch (MyException e) {
			retutrnMap.put("success", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			
		}
		return retutrnMap;
	}

	


}
