package com.example.projectdemo.util.tool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 *分享文字、图片、多张图片、文件
 */
public class ShareUtil {
	  /**
     * 分享文字
     * @param activityTitle
     * 分享时的标题
     * @param shareText
     * 分享的文本信息
     * mContext
     * 上下文
     */
    public static void shareText(String activityTitle, String shareText, Context mContext) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,shareText );
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
        shareIntent.setType("text/plain");
        //设置分享列表的标题，并且每次都显示分享列表
        mContext.startActivity(Intent.createChooser(shareIntent, activityTitle));
    }
 
    /**
     * 分享单张图片
     * @param activityTitle
     * 分享时的标题
     * @param imagePath
     * 图片路径
     * mContext
     * 上下文
     */
    public static void shareSingleImage(String activityTitle, String imagePath, Context mContext) {
        //由文件得到uri
		File f=new File(imagePath);
		 if (f != null && f.exists() && f.isFile()) {  
			 Uri imageUri = Uri.fromFile(new File(imagePath));
		        Log.d("share", "uri:" + imageUri);  //输出：file:///storage/emulated/0/test.jpg
		        Intent shareIntent = new Intent();
		        shareIntent.setAction(Intent.ACTION_SEND);
		        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
		        shareIntent.setType("image/*");
		        mContext.startActivity(Intent.createChooser(shareIntent, activityTitle));
		 }else {
			Toast.makeText(mContext, "文件存在", Toast.LENGTH_LONG).show();
		}
    }
 
    /**
     * 分享多张图片
     * @param activityTitle
     * 分享时的标题
     * @param imagePathList
     * 图片路径的List集合
     * mContext
     * 上下文
     */
    public static void shareMultipleImage(String activityTitle, List<String> imagePathList, Context mContext) {
        ArrayList<Uri> uriList = new ArrayList<Uri>();
        Iterator<String> imagePathIterable=imagePathList.iterator();
        while(imagePathIterable.hasNext()){
        	String imagePath=imagePathIterable.next();
        		File f=new File(imagePath);
        	 if (f != null && f.exists() && f.isFile()) {  
        		 uriList.add(Uri.fromFile(f));
        	 }
        	
        }
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        shareIntent.setType("image/*");
        mContext.startActivity(Intent.createChooser(shareIntent, activityTitle));
    } 
    
    /**
     * 分享单个文件
     * @param activityTitle
     * 分享时的标题
     * @param filePath
     * 文件的路径
     * mContext
     * 上下文
     */
    public static void shareSingleFile(String activityTitle, String filePath, Context mContext){
    	Intent shareIntent = new Intent(Intent.ACTION_SEND);
    	 File f = new File(filePath);
         if (f != null && f.exists() && f.isFile()) {  
        	 shareIntent.setType("*/*");//此处可发送多种文件 
             Uri u = Uri.fromFile(f);
             shareIntent.putExtra(Intent.EXTRA_STREAM, u);
         	mContext.startActivity(Intent.createChooser(shareIntent, activityTitle));
         }else{
        	 Toast.makeText(mContext, "文件不存在", Toast.LENGTH_LONG).show();
         }
    }
}
