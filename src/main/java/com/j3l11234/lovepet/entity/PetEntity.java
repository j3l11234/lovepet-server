package com.j3l11234.lovepet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
public class PetEntity {
	/**
	 * 普通用户
	 */
	public static final int USER = 0x00000001;
	/**
	 * 内容管理权限
	 */
	public static final int ADMIN_CONTENT = 0x00000002;
	/**
	 * 系统管理权限
	 */
	public static final int ADMIN_SYSTEM = 0x00000004;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id", unique = true, nullable = false)
	private int id;
	 
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "pet_name")
	private String petName;
	
	@Column(name = "pet_photo")
	private String petPhoto;

	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public int getUser_id() {
		return userId;
	}


	public void setUser_id(int user_id) {
		this.userId = user_id;
	}

	public String getPet_name() {
		return petName;
	}


	public void setPet_name(String pet_name) {
		this.petName = pet_name;
	}
	
	public String getPet_photo() {
		return petPhoto;
	}


	public void setPet_photo(String pet_photo) {
		this.petPhoto = pet_photo;
	}


	public static boolean checkPrivilege(int userPrivilege, int privilege) {
		return (userPrivilege & privilege) == privilege;
	}
}
