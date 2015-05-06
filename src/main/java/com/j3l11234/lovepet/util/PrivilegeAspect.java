package com.j3l11234.lovepet.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.j3l11234.lovepet.entity.UserEntity;

//import org.springframework.web.servlet.handler.

@Aspect
@Component
public class PrivilegeAspect {
	@Around("execution(* com.j3l11234.lovepet.controller.*.*(..)) && @annotation(privilegeCheck)")
	public Object addLogSuccess(ProceedingJoinPoint joinPoint ,PrivilegeCheck privilegeCheck) throws Throwable{
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		UserEntity user =  (UserEntity) session.getAttribute("user");

		//登录检测
		if(privilegeCheck.needLogin() && user == null){
			Map<String, Object> retutrnMap = new HashMap<String, Object>();
			retutrnMap.put("error", RespondCode.NOT_LOGIN);
			retutrnMap.put("data", "未登录");
			return retutrnMap;
		}
		//权限检测
		if(!UserEntity.checkPrivilege(user.getPrivilege(), privilegeCheck.privilege())){
			return "redirect:/noPrivilege.do";
		}

		return joinPoint.proceed();
	}  
}
