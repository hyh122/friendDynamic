package com.example.frienddynamicdemo;

import java.util.ArrayList;
import java.util.List;



public class FriendNewsModal {
	private byte[] protrait;//存放头像
	private boolean isZan;
	private String email;//发表动态的好友的头像
	private String userName;//发表动态的好友用户名
	private String date;//发表动态的日期
	private double distance;//跑步的距离
	private float totalTime;//跑步时间
	private int avgHeartRate;//平均心率
	private double avgSpeed;//平均速度
	private int numOfZan;//收到的总赞数
	private List<FriendComment> friendComments;
	
	public byte[] getProtrait() {
		return protrait;
	}
	
	public boolean isZan() {
		return isZan;
	}

	public void setZan(boolean isZan) {
		this.isZan = isZan;
	}

	public void setProtrait(byte[] protrait) {
		this.protrait = protrait;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double d) {
		this.distance = d;
	}
	public float getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}
	public int getAvgHeartRate() {
		return avgHeartRate;
	}
	public void setAvgHeartRate(int avgHeartRate) {
		this.avgHeartRate = avgHeartRate;
	}
	public double getAvgSpeed() {
		return avgSpeed;
	}
	public void setAvgSpeed(double d) {
		this.avgSpeed = d;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumOfZan() {
		return numOfZan;
	}
	public void setNumOfZan(int numOfZan) {
		this.numOfZan = numOfZan;
	}

	public List<FriendComment> getFriendComments() {
		return friendComments;
	}

	public void setFriendComments(List<FriendComment> friendComments) {
		this.friendComments = friendComments;
	}




	
	
}
