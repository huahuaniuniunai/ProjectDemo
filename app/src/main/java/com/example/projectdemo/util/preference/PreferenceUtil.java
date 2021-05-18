package com.example.projectdemo.util.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectdemo.application.MyApplication;

public class PreferenceUtil {

	// 用户文件 preference 名称
	public static final String SHARED_PREFERENCE_USER_INFO = "userinfo";

	private static PreferenceUtil mInstance;
	
	private SharedPreferences cache ;
	
	private PreferenceUtil(){
		cache = MyApplication.getContext().getSharedPreferences(SHARED_PREFERENCE_USER_INFO, Context.MODE_PRIVATE);
	}
	
	public static PreferenceUtil get(){
		if(mInstance == null)
			mInstance = new PreferenceUtil();
		return mInstance;
	}
	

	public void putCacheString(String key , String value){
		cache.edit().putString(key, value).commit() ;
	}
	
	public String getCacheString(String key , String defaultValue){
		return cache.getString(key, defaultValue);
	}
	
	public void putCacheBoolean(String key , boolean value){
		cache.edit().putBoolean(key, value).commit() ;
	}
	
	public boolean getCacheBoolean(String key , boolean defaultValue){
		return cache.getBoolean(key, defaultValue);
	}

	public void clear() {
		cache.edit().clear().commit();
	}

}
