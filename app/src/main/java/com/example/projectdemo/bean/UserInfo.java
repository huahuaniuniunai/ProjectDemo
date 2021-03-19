package com.example.projectdemo.bean;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectdemo.util.tool.ComUtil;
import com.example.projectdemo.util.tool.StringUtil;


public class UserInfo {
	private static String groupid="";
	private static String unitid="";
	private static String userid="";
	private static String loginno="";
	private static String username="";
	private static String loginpwd="";
	public static void initUserInfo(Context context){
		SharedPreferences preferences = context.getSharedPreferences("userInfoSettings", ComUtil.getSharedPreferencesMode());
		initUserInfo(preferences);
	}
	public static void initUserInfo(SharedPreferences preferences){
		setGroupid(StringUtil.null2String(preferences.getString("groupid", "")).trim());
		setUnitid(StringUtil.null2String(preferences.getString("unitid", "")).trim());
		setUserid(StringUtil.null2String(preferences.getString("userid", "")).trim());
		setLoginno(StringUtil.null2String(preferences.getString("loginno", "")).trim());
		setUsername(StringUtil.null2String(preferences.getString("username", "")).trim());
		setLoginpwd(StringUtil.null2String(preferences.getString("loginpwd", "")).trim());
	}
	public static String getGroupid() {
		return groupid;
	}
	public static void setGroupid(String groupid) {
		UserInfo.groupid = groupid;
	}
	public static String getUnitid() {
		return unitid;
	}
	public static void setUnitid(String unitid) {
		UserInfo.unitid = unitid;
	}
	public static String getUserid() {
		return userid;
	}
	public static void setUserid(String userid) {
		UserInfo.userid = userid;
	}
	public static String getLoginno() {
		return loginno;
	}
	public static void setLoginno(String loginno) {
		UserInfo.loginno = loginno;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		UserInfo.username = username;
	}
	public static String getLoginpwd() {
		return loginpwd;
	}
	public static void setLoginpwd(String loginpwd) {
		UserInfo.loginpwd = loginpwd;
	}
	
}
