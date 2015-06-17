package com.j3l11234.lovepet.model;

import java.security.MessageDigest;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j3l11234.lovepet.entity.UserEntity;
import com.j3l11234.lovepet.entity.UserFollowEntity;
import com.j3l11234.lovepet.mapper.UserFollowMapper;
import com.j3l11234.lovepet.mapper.UserMapper;
import com.j3l11234.lovepet.util.MyException;



@Service
public class UserModel {
	
	@Autowired  
    private UserMapper userMapper; 
	@Autowired  
    private UserFollowMapper userFollowMapper; 
	
	public UserEntity login(String username, String password) throws MyException{
		UserEntity user = null;
		try {
			user = userMapper.getUserLogin(username, string2MD5(password));
			
			if(user == null){
				throw new MyException("用户名或密码错误");
			}
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return user;
	}
	
	public HashMap<String, Object> getProfileInfo(int userId) throws MyException{
		HashMap<String, Object> profileInfo = null;
		try {
			profileInfo = userMapper.getProfileInfo(userId);
			if(profileInfo == null){
				throw new MyException("用户不存在");
			}
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return profileInfo;
	}
	
	public HashMap<String, Object> getUserInfo(int userId,int fansId) throws MyException{
		HashMap<String, Object> userInfo = null;
		try {
			userInfo = userMapper.getUserInfo(userId);
			if(userInfo == null){
				throw new MyException("用户不存在");
			}
			
			UserFollowEntity hasFollow = userFollowMapper.hasFollow(userId, fansId);
			if(hasFollow == null){
				userInfo.put("follow", 0);
			}else{
				userInfo.put("follow", 1);
			}
			
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return userInfo;
	}
	
	
	@Transactional(rollbackFor=MyException.class) 
	public UserFollowEntity followUser(UserFollowEntity userFollow) throws MyException{
		if(userFollow.getFollowUserId() == userFollow.getFollowFansId()){
			throw new MyException("您不能关注自己");
		}
		try {
			//判断用户是否存在
			UserEntity user = userMapper.getUser(userFollow.getFollowUserId());
			if(user == null){
				throw new MyException("用户不存在");
			}
			UserFollowEntity hasFollow = userFollowMapper.hasFollow(userFollow.getFollowUserId(), userFollow.getFollowFansId());
			int result;
			if(hasFollow == null){ //未关注
				result = userFollowMapper.addUserFollow(userFollow);
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				//增加粉丝数
				result = userMapper.addFansNum(userFollow.getFollowUserId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				//增加关注数
				result = userMapper.addFollowNum(userFollow.getFollowFansId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				
			}else{ //已关注
				result = userFollowMapper.deleteUserFollow(hasFollow.getId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				//减少粉丝数
				result = userMapper.minusFansNum(userFollow.getFollowUserId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				//减少关注数
				result = userMapper.minusFollowNum(userFollow.getFollowFansId());
				if(result != 1){
					throw new MyException("数据更新错误");
				}
				userFollow = null;
			}
		} catch (MyException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("数据库访问错误");
		}
		return userFollow;
	}
	
//	
//	public UserEntity register(String username,String password, String alias) throws MyException{
//		Session session = null;
//		UserEntity user = null;
//		try {
//			session = sessionFactory.openSession();	
//			String hql="FROM UserEntity AS user WHERE user.username=:username";
//			Query query=session.createQuery(hql);
//			query.setString("username", username);
//			user = (UserEntity)query.uniqueResult();
//			//不存在则新建
//			if(user != null){
//				throw new MyException("用户名重复");	
//			}
//			if(password.length() == 0){
//				throw new MyException("密码不能为空");
//			}
//			user = new UserEntity();
//			user.setPwd(string2MD5(password));
//			user.setUsername(username);		
//			user.setPrivilege(user.USER);
//			user.setAlias(alias);
//			
//			Transaction tran = session.beginTransaction();  
//			session.save(user);
//			tran.commit();
//		} catch (MyException e) {
//			e.printStackTrace();
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new MyException("数据库访问错误");
//		}finally{
//			if(session != null){
//				session.close();
//			}	
//		}
//		return user;
//	}
	
	private static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
