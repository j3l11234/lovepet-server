package com.j3l11234.lovepet.entity;


public class PetEntity {

	private int id;
	private int userId;
	private String petName;
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
