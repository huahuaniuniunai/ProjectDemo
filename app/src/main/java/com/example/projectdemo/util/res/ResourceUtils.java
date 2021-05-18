package com.example.projectdemo.util.res;

import android.graphics.drawable.Drawable;

import com.example.projectdemo.application.MyApplication;

public class ResourceUtils {

	public static String getString(int id){
		return MyApplication.getContext().getResources().getString(id);
	}
	
	public static int getColor(int id){
		return MyApplication.getContext().getResources().getColor(id);
	}
	
	public static Drawable getDrawable(int id){
		return MyApplication.getContext().getResources().getDrawable(id);
	}
}
