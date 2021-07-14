package com.example.yoogad_task.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class PostModel{

	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getBody(){
		return body;
	}

	public int getUserId(){
		return userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}