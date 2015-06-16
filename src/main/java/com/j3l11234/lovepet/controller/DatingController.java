package com.j3l11234.lovepet.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.j3l11234.lovepet.entity.DatingEntity;
import com.j3l11234.lovepet.entity.FeedEntity;
import com.j3l11234.lovepet.entity.FeedFavEntity;
import com.j3l11234.lovepet.entity.FeedReplyEntity;
import com.j3l11234.lovepet.entity.UserEntity;
import com.j3l11234.lovepet.model.DatingModel;
import com.j3l11234.lovepet.model.FeedModel;
import com.j3l11234.lovepet.model.UserModel;
import com.j3l11234.lovepet.util.MyException;
import com.j3l11234.lovepet.util.PrivilegeCheck;
import com.j3l11234.lovepet.util.RespondCode;



@Controller
@RequestMapping("/dating")
public class DatingController {
	@Autowired
	private DatingModel datingModel;

	@RequestMapping(value = "/addDating",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object addDating(
			@RequestParam(value ="content") String content,
			@RequestParam(value ="pet") String pet,
			@RequestParam(value ="location") String location,
			@RequestParam(value ="time") String time,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		UserEntity user =  (UserEntity) session.getAttribute("user");
		
		if(content.equals("") || pet.equals("") || location.equals("") || time.equals("")){
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "提交信息有误");
			return retutrnMap;
		}
		Date time2;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time2 = sdf.parse(time);
        }catch (ParseException e) {
        	retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", "提交信息有误");
			return retutrnMap;
        }
		
		DatingEntity dating = new DatingEntity();
		dating.setUserId(user.getId());
		dating.setContent(content);
		dating.setPet(pet);
		dating.setLocation(location);
		dating.setTime(time2);
		dating.setSubmitTime(new Date());

		
		try {
			datingModel.addDating(dating);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", "提交成功");
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
	
	
	@RequestMapping(value = "/getDatingList",method = RequestMethod.POST)
	@PrivilegeCheck(privilege = UserEntity.USER, needLogin = true)
	@ResponseBody
	public Object getDatingList(
			@RequestParam(value ="per_page", required=false, defaultValue="10") int perPage,
			@RequestParam(value ="page", required=false, defaultValue="1") int page,
			HttpSession session){
		Map<String, Object> retutrnMap = new HashMap<String, Object>();
		
		try {
			List<Map<String, Object>> datingList = datingModel.getDatingList(perPage, page);
			
			retutrnMap.put("error", RespondCode.OK);
			retutrnMap.put("data", datingList);
		} catch (MyException e) {
			retutrnMap.put("error", RespondCode.ERROR);
			retutrnMap.put("data", e.getMessage());
			return retutrnMap;
		}

		return retutrnMap;
	}
}
