package com.example.projectdemo.util.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

public class ComUtil {
	/**
	 * 获取手机串号
	 * @return
	 */
	public static String getIMEI(Context context){


		if(null != context){


			TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			try{


				@SuppressLint("MissingPermission") String deviceId = telephonemanage.getDeviceId();
                //android 10以上已经获取不了imei了 用 android id代替
				if(TextUtils.isEmpty(deviceId)){
					deviceId = Settings.System.getString(
							context.getContentResolver(), Settings.Secure.ANDROID_ID);
				}
				Globals.log("获取手机IMEI号",deviceId, context);
				return deviceId;
			}catch(Exception e) {
				Globals.log("获取手机IMEI号", e, context);
				return "";
			}
		}
		return "";
	}

	/**开启定时器*/
	public static void startAlarm(long intervalMillis, int requestCode, Class<?> clsReceiver, Context context){

	}
	/**是否存在SD卡*/
	public static boolean existsSdCard(){
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
	/**app是否在运行*/
	public static boolean isAppRunning(Context context){
		String myPackage = context.getPackageName();
		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(100);
		for (RunningTaskInfo info : list) {
			if(myPackage.equals(info.topActivity.getPackageName()) || myPackage.equals(info.baseActivity.getPackageName())){
				return true;
			}
		}
		return false;
	}
	/**运行APP*/
	public static void startApp(Context context, boolean hidden){
		try {
			//启动程序
			Intent launch = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
			launch.addCategory(Intent.CATEGORY_LAUNCHER);
			launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			context.startActivity(launch);

			//自动隐藏
			if(hidden){
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**用户、定位信息文件共享模式**/
	public static int getSharedPreferencesMode(){
		if (Build.VERSION.SDK_INT >= 11) {
			return Context.MODE_PRIVATE; // | Context.MODE_MULTI_PROCESS;
		}else{
			return Context.MODE_PRIVATE;
		}
	}
	/**点亮屏幕*/
	public static void wakeUpAndUnlock(Context context){
		/*KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
		//解锁
		kl.disableKeyguard();
		//获取电源管理器对象
		PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
		//获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
		//点亮屏幕
		wl.acquire();
		//释放
		wl.release();*/
	}
	//是否已授权
	public static boolean isPermissionGranted(String permission, Context context){
		PackageManager pkm = context.getPackageManager();
		int status = pkm.checkPermission(permission, context.getPackageName());
		return PackageManager.PERMISSION_GRANTED == status;
	}
	//android版本大于或等于19，检查是否授权
//	public static boolean isPermissionGranted(Context context, int op){
//		 final int version = Build.VERSION.SDK_INT;
//			if (version >= 19){
//				Object object = context.getSystemService("appops");
//				Class c = object.getClass();
//				try {
//					Class[] cArg = new Class[3];
//					cArg[0] = int.class;
//					cArg[1] = int.class;
//					cArg[2] = String.class;
//					Method lMethod = c.getDeclaredMethod("checkOp", cArg);
//					int mode= (Integer) lMethod.invoke(object, op, Binder.getCallingUid(), context.getPackageName());
//					if(mode==0){
//						  return true;
//					}else {
//						return false;
//					}
//
//				} catch(NoSuchMethodException e) {
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (IllegalArgumentException e) {
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					e.printStackTrace();
//				}
//			}
//			return false;
//	}
	private static WakeLock mWakeLock=null;
	//申请设备电源锁
//	public static void acquireWakeLock( Context context){
//		if (null == mWakeLock) {
//			PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
//			mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK| PowerManager.ON_AFTER_RELEASE,"");
//			if (null != mWakeLock){
//				mWakeLock.acquire();
//			}
//		}
//	}
	//释放设备电源锁
	public static void  releaseWakeLock(){
		if (null != mWakeLock){
			mWakeLock.release();
			mWakeLock = null;
		}
	}
	public static double availMemory(Context context){
		ActivityManager myActivityManager =(ActivityManager)context.getSystemService(Activity.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		myActivityManager.getMemoryInfo(memoryInfo);
		long memSize = memoryInfo.availMem;
		return ((double)memSize)/1024/1204;
	}
	//判断APP是否安装
	public static boolean isAppInstalled(Context context, String packageName) {
		boolean hasInstalled = false;
		PackageManager pm = context.getPackageManager();
		@SuppressLint("WrongConstant") List<PackageInfo> list = pm.getInstalledPackages(PackageManager.PERMISSION_GRANTED);
		for (PackageInfo p : list) {
			if (packageName != null && packageName.equals(p.packageName)) {
				hasInstalled = true;
				break;
			}
		}
		return hasInstalled;
	}
	public static void uninstallApp(Context context, String packageName) {
		Uri uri = Uri.parse("package:" + packageName);//获取删除包名的URI
		Intent i = new Intent();
		i.setAction(Intent.ACTION_DELETE);//设置我们要执行的卸载动作
		i.setData(uri);//设置获取到的URI
		context.startActivity(i);
	}
	/***
	 * 设置设备唯一的ID
	 * @param
	 * @return
	 */
	public static String getDeviceUUID(Context context){
		SharedPreferences preferences = context.getSharedPreferences("appRunInfo", ComUtil.getSharedPreferencesMode());
		String deviceUUID = StringUtil.null2String(preferences.getString("deviceUUID", "")).trim();
		if(deviceUUID.length() == 0){
			deviceUUID = CodeGenerator.getUUID();
			Editor editor = preferences.edit();
			editor.putString("deviceUUID", deviceUUID);
			editor.commit();
		}
		return deviceUUID;
	}
	/***
	 * 获取登录TOKEN
	 * @param
	 * @return
	 */
	public static String getLoginToken(Context context){
		SharedPreferences preferences = context.getSharedPreferences("appRunInfo", ComUtil.getSharedPreferencesMode());
		String loginToken = StringUtil.null2String(preferences.getString("loginToken", "")).trim();
		return loginToken;
	}
	/***
	 * 设置登录TOKEN
	 * @param
	 * @return
	 */
	public static void setLoginToken(Context context, String loginToken){
		SharedPreferences preferences = context.getSharedPreferences("appRunInfo", ComUtil.getSharedPreferencesMode());
		Editor editor = preferences.edit();
		editor.putString("loginToken", loginToken);
		editor.commit();
	}
	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    //验证包是否有效
//	public static void validatePackage(final Activity context) throws Exception {
//		if(Globals.debug == true){
////			Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
//			return ;
//		}
//		String sha1 = StringUtil.null2String(getSignSHA1(context)).trim();
//		if(sha1.equals("4F:BC:BC:6E:1C:E5:7B:16:2F:A5:7A:A1:20:6E:B8:0A:53:F1:5D:CF")){//合法签名
//			return ;
//		}
//		CoreService.mHandler.postDelayed(new Runnable() {
//			public void run() {
//				context.finish();
//			}
//		}, 200);
//	}
	public static String getSignSHA1(Activity context) throws Exception {
		PackageManager pm = context.getPackageManager();
		PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
		Signature[] signs = packageInfo.signatures;
		Signature sign = signs[0];
		return getSignSHA1(sign.toByteArray());
		//Log.e("SDL", encryptionMD5(sign.toByteArray()));
	}
	/**获取签名的SHA1值**/
	public static String getSignSHA1(byte[] signature) throws Exception {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature));
		//String pubKey = cert.getPublicKey().toString();
		//String signNumber = cert.getSerialNumber().toString();
		//Log.e("SDL", "signName:" + cert.getSigAlgName());
		//Log.e("SDL", "pubKey:" + pubKey);
		//Log.e("SDL", "signNumber:" + signNumber);
		//Log.e("SDL", "subjectDN:"+cert.getSubjectDN().toString());

		String hexString = null;
		//加密算法的类，这里的参数可以使MD4,MD5等加密算法
		MessageDigest md = MessageDigest.getInstance("SHA1");
		//获得公钥
		byte[] publicKey = md.digest(cert.getEncoded());
		//字节到十六进制的格式转换
		hexString = byte2HexFormatted(publicKey);
		return hexString;
	}
	//这里是将获取到得编码进行16进制转换
	private static String byte2HexFormatted(byte[] arr) {
		StringBuilder str = new StringBuilder(arr.length * 2);
		for (int i = 0; i < arr.length; i++) {
			String h = Integer.toHexString(arr[i]);
			int l = h.length();
			if (l == 1)
				h = "0" + h;
			if (l > 2)
				h = h.substring(l - 2, l);
			str.append(h.toUpperCase());
			if (i < (arr.length - 1))
				str.append(':');
		}
		return str.toString();
	}
	/**
	 * MD5加密
	 * @param byteStr 需要加密的内容
	 * @return 返回 byteStr的md5值
	 */
	public static String encryptionMD5(byte[] byteStr) {
	  MessageDigest messageDigest = null;
	  StringBuffer md5StrBuff = new StringBuffer();
	  try {
	    messageDigest = MessageDigest.getInstance("MD5");
	    messageDigest.reset();
	    messageDigest.update(byteStr);
	    byte[] byteArray = messageDigest.digest();
	    for (int i = 0; i < byteArray.length; i++) {
	      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
	        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
	      } else {
	        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
	      }
	    }
	  } catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	  }
	  return md5StrBuff.toString();
	}

	 /**
	  * 加载本地图片
	 * @param url
	 * @return
	 */
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.e("SDL", "getLoacalBitmap: ERROR" );
			return null;
		}
	}
}
