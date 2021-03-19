//package com.example.projectdemo.version;
//
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.projectdemo.bean.UserInfo;
//import com.example.projectdemo.util.tool.Globals;
//import com.example.projectdemo.util.tool.HttpUtil;
//import com.example.projectdemo.util.tool.MTInfoUtil;
//import com.example.projectdemo.util.tool.StringUtil;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Map;
//
//import javax.net.ssl.HttpsURLConnection;
//
//
///**
// * APK更新管理类
// *
// * @author Royal
// *
// */
//public class VersionManager {
//
//	// 上下文对象
//	private Context mContext;
//	//更新版本信息对象
//	private VersionInfo info = null;
//	// 下载进度条
//	private ProgressBar progressBar;
//	// 是否终止下载
//	private boolean isInterceptDownload = false;
//	//进度条显示数值
//	private int progress = 0;
//	private String groupid = "";
//	private String userid = "";
//	public static boolean isDialogShow = false;
//	public static Object synObj = new Object();
//
//	/**
//	 * 参数为Context(上下文activity)的构造函数
//	 *
//	 * @param context
//	 */
//	public VersionManager(Context context) {
//		this.mContext = context;
//	}
//
//	public void checkUpdateApp() {
//		if(StringUtil.null2String(UserInfo.getUserid()).trim().equals("")){
//			//登录后再进行版本控制
//			return ;
//		}
//		if(isDialogShow){
//			return ;
//		}
//		try {
//			final PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
//			final String mTId = MTInfoUtil.getMtid(mContext);
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						groupid = UserInfo.getGroupid();
//						userid = UserInfo.getUserid();
//						Map<String, String> params = new HashMap<String, String>();
//						params.put("AppOS", "Android");
//						params.put("AppName", mContext.getString(R.string.app_name_en));
//						params.put("versionName", pi.versionName);
//						params.put("groupid", UserInfo.getGroupid());
//						params.put("userid", UserInfo.getUserid());
//						params.put("unitid", UserInfo.getUnitid());
//						params.put("mTId", mTId);
//						params.put("opType", "3");
//						//插件更新信息
//						File file = new File(Globals.LOCAL_PAGE_PATH + "/" + USER_PLUGIN_FILE_NAME);
//						if(file.exists()){
//							JSONObject plugins = fileToJson(file);
//							if(plugins.has("pluginVersions")){
//								params.put("pluginVersions", plugins.getString("pluginVersions"));
//							}
//						}
//						//从服务器取更新信息
//						final String retStr = StringUtil.null2String(HttpUtil.post(
//							Globals.WEB_URL + "/app/plugins/sys/main/bz/version.xp?doAction=getVersion",
//							params
//						)).trim();
//
//						SshionActivity.mHandler.post(new Runnable() {
//							@Override
//							public void run() {
//								synchronized (synObj) {
//									if(isDialogShow){
//										return ;
//									}
//									checkUpdate(retStr);
//								}
//							}
//						});
//					}catch (Exception e) {
//						Globals.log("检查版本更新", e, mContext);
//					}
//				}
//			}).start();
//		} catch (Exception e) {
//			Globals.log("检查版本更新", e, mContext);
//			return ;
//		}
//	}
//	public int checkUpdate(String retStr) {
//		try {
//			if(retStr.length() == 0){
//				return 1;//不做处理
//			}else{
//				JSONObject retObj = new JSONObject(retStr);
//				if("false".equals(retObj.getString("updated"))){
//					return 1;//不需要更新
//				}
//
//				info = getVersionInfo(retObj.getJSONObject("verObj"));
//
//				if("-1".equals(info.getAppfileurl())){
//					return 1;
//				}
//
//				showTipsDialog();
//				return 0;//检测有更新
//			}
//		} catch (Exception e) {
//			Globals.log("更新APP", e, mContext);
//			return -1;//获取版本失败服务器端通信失败
//		}
//	}
//
//	/**版本信息**/
//	private VersionInfo getVersionInfo(JSONObject obj) throws Exception {
//		VersionInfo info = new VersionInfo();
//		if(obj.has("appfileurl")){
//			info.setAppfileurl(obj.getString("appfileurl"));
//		}
//		if(obj.has("appname")){
//			info.setAppname(obj.getString("appname"));
//		}
//		info.setAppupdatedesc(obj.getString("appupdatedesc"));
//		info.setForceupdatecnt(obj.getInt("forceupdatecnt"));
//		info.setType(obj.getString("type"));
//		return info;
//	}
//
//	/**
//	 * 提示更新对话框
//	 */
//	private void showTipsDialog() {
//		if(isDialogShow){
//			return ;
//		}
//		isDialogShow = true;
//		final Map<String, String> clickMap = new Hashtable<String, String>();
//		if(TYPE_PLUGIN.equals(info.getType())){//插件更新
//			showPluginDownloadDialog(groupid, userid, "系统更新中...");
//			return ;
//		}else{//框架更新
//			Builder builder = new Builder(mContext);
//			builder.setTitle("提示");
//			builder.setCancelable(false);
//			builder.setMessage(info.getAppupdatedesc());
//			builder.setPositiveButton("下载", new OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					if(clickMap.get("isclick") != null){//防止重复点击
//						return ;
//					}
//					clickMap.put("isclick", "true");
//					dialog.dismiss();
//					showAppDownloadDialog();
//				}
//			});
//			String cancelMsg = "退出";
//			if(info.getForceupdatecnt() == 0){
//				//不需要强制更新
//				cancelMsg = "稍后再说";
//			}
//			builder.setNegativeButton(cancelMsg, new OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					isDialogShow = false;
//					dialog.dismiss();
//					if(info.getForceupdatecnt() > 0){
//						//要强制更新 强制退出程序
//						android.os.Process.killProcess(android.os.Process.myPid());
//						System.exit(0);//退出程序
//					}
//				}
//			});
//			builder.create().show();
//		}
//
//	}
//
//	/**
//	 * 弹出框架下载框
//	 */
//	private void showAppDownloadDialog() {
//		Builder builder = new Builder(mContext);
//		builder.setTitle("下载中...");
//		builder.setCancelable(false);
//		final LayoutInflater inflater = LayoutInflater.from(mContext);
//		View v = inflater.inflate(R.layout.update_progress, null);
//		progressBar = (ProgressBar) v.findViewById(R.id.pb_update_progress);
//		builder.setView(v);
//		builder.setNegativeButton("取消", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				isDialogShow = false;
//				dialog.dismiss();
//				//终止下载
//				isInterceptDownload = true;
//				if(info.getForceupdatecnt() > 0){
//					//要强制更新 强制退出程序
//					android.os.Process.killProcess(android.os.Process.myPid());
//					System.exit(0);//退出程序
//				}
//			}
//		});
//		builder.create().show();
//		//下载apk
//		downloadApk();
//	}
//
//	/**
//	 * 下载apk
//	 */
//	private void downloadApk(){
//		//开启另一线程下载
//		Thread downLoadThread = new Thread(downApkRunnable);
//		downLoadThread.start();
//	}
//	/**下载路径*/
//	public static String getDownloadPath(){
//		return Environment.getExternalStorageDirectory().getAbsolutePath() + "/updateApkFile/";
//	}
//	/**
//	 * 从服务器下载新版apk的线程
//	 */
//	private Runnable downApkRunnable = new Runnable(){
//		@Override
//		public void run() {
//			if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//				//如果没有SD卡
//				Builder builder = new Builder(mContext);
//				builder.setTitle("提示");
//				builder.setCancelable(false);
//				builder.setMessage("当前设备无SD卡，数据无法下载");
//				builder.setPositiveButton("确定", new OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//					}
//				});
//				builder.show();
//				return;
//			}else{
//
//				// Must be done during an initialization phase like onCreate
//				RxPermissions rxPermissions=RxPermissions.getInstance(mContext);
//				rxPermissions
//						.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION)
//						.subscribe(new Action1<Boolean>() {
//							@Override
//							public void call(Boolean granted) {
//								if (granted) { // Always true pre-M
//									// I can control the camera now
//                                    try {
//                                        //服务器上新版apk地址
//                                        URL url = new URL(info.getAppfileurl());
//                                        HttpURLConnection conn = null;//(HttpURLConnection)url.openConnection();
//                                        if (info.getAppfileurl().startsWith("https://")) {
//                                            HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
//                                            https.setHostnameVerifier(HttpsUtil.SunHostnameVerifier);
//                                            conn = https;
//                                        } else {
//                                            conn = (HttpURLConnection)url.openConnection();
//                                        }
//                                        conn.connect();
//                                        int length = conn.getContentLength();
//                                        InputStream is = conn.getInputStream();
//                                        File file = new File(getDownloadPath());
//                                        if(!file.exists()){
//                                            //如果文件夹不存在,则创建
//                                            file.mkdir();
//                                        }
//                                        //下载服务器中新版本软件（写文件）
//                                        String apkFile = getDownloadPath() + info.getAppname();
//                                        File ApkFile = new File(apkFile);
//                                        FileOutputStream fos = new FileOutputStream(ApkFile);
//                                        int count = 0;
//                                        byte buf[] = new byte[1024];
//                                        do{
//                                            int numRead = is.read(buf);
//                                            count += numRead;
//                                            //更新进度条
//                                            progress = (int) (((float) count / length) * 100);
//                                            handler.sendEmptyMessage(1);
//                                            if(numRead <= 0){
//                                                //下载完成通知安装
//                                                handler.sendEmptyMessage(1);
//                                                handler.sendEmptyMessage(0);
//                                                break;
//                                            }
//                                            fos.write(buf,0,numRead);
//                                            //当点击取消时，则停止下载
//                                        }while(!isInterceptDownload);
//
//                                        fos.close();
//                                    } catch (Exception e) {
//                                        Globals.log("下载更新包", e, mContext);
//                                    }
//								} else {
//									// Oups permission denied
//									//只要有一个权限被拒绝，就会执行
//									Toast.makeText(mContext, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
//								}
//							}
//						});
//
//
//
//			}
//		}
//	};
//
//	/**
//	 * 声明一个handler来跟进进度条
//	 */
//	@SuppressLint("HandlerLeak")
//	private Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case 1:
//				// 更新进度情况
//				progressBar.setProgress(progress);
//				break;
//			case 0:
//				isDialogShow = false;
//				if(TYPE_PLUGIN.equals(info.getType())){//安装插件
//					afterDownloadPlugin();
//					progressDialog.dismiss();
//				}else{// 安装apk文件
//					progressBar.setVisibility(View.INVISIBLE);
//					installApk();
//				}
//				break;
//			default:
//				break;
//			}
//		};
//	};
//
//	/**
//	 * 安装apk
//	 */
//	private void installApk() {
//		// 获取当前sdcard存储路径
//		File apkfile = new File(getDownloadPath() + info.getAppname());
//		if (!apkfile.exists()) {
//			return;
//		}
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		// 安装，如果签名不一致，可能出现程序未安装提示
//		i.setDataAndType(Uri.fromFile(new File(apkfile.getAbsolutePath())), "application/vnd.android.package-archive");
//		mContext.startActivity(i);
//
//		//强制退出程序
//		android.os.Process.killProcess(android.os.Process.myPid());
//		System.exit(0);//退出程序
//	}
//	/**用户及插件配置文件名*/
//	public static String USER_PLUGIN_FILE_NAME = "user_plugin.json";
//	public static String PLUGIN_FILE_NAME = "plugin.json";
//	public static String PLUGIN_DIR = "plugins";
//	public static String TYPE_APP = "APP";//下载类型-框架
//	public static String TYPE_PLUGIN = "PLUGIN";//下载类型-插件
//	AlertDialog progressDialog = null;
//	public static String isPageReload = "false";
//	/**是否需要下载插件
//	 * @throws Exception
//	 * **/
//	public static boolean neededDownloadPlugin(Context context, String groupid, String userid){
//		File file = new File(Globals.LOCAL_PAGE_PATH + "/" + USER_PLUGIN_FILE_NAME);
//		if(!file.exists()){
//			return true;
//		}
//		try{
//			JSONObject plugins = fileToJson(file);
//			if(!plugins.has("groupid")){//不存在集团编号
//				return true;
//			}
//			if(!groupid.equals(plugins.getString("groupid"))){//切换集团需要重新下载
//				return true;
//			}
//			if(!userid.equals(plugins.getString("userid"))){//切换用户
//				if(!loadPluginsOfUser(groupid, userid)){//加载用户插件失败，重新加载插件
//					return true;
//				}
//			}
//		}catch(Exception e){
//			Globals.log("解析插件配置", e, context);
//			return true;
//		}
//		return false;
//	}
//	/**将文件的内容读出为JSON**/
//	public static JSONObject fileToJson(File file)throws Exception {
//		String jsonStr = "";
//		int readLen = 0;
//		FileInputStream fis = new FileInputStream(file);
//		byte[] bytes = new byte[fis.available()];
//		while((readLen = fis.read(bytes)) > 0){
//			jsonStr += new String(bytes, 0, readLen, "utf-8");
//		}
//		fis.close();
//		return new JSONObject(jsonStr);
//	}
//	/**
//	 * 弹出插件下载进度框
//	 */
//	public void showPluginDownloadDialog(String groupid, String userid, String builderTitle) {
//		isDialogShow = true;
//		this.groupid = groupid;
//		this.userid = userid;
//
//		Builder builder = new Builder(mContext);
//		builder.setTitle(builderTitle);
//		builder.setCancelable(false);
//		final LayoutInflater inflater = LayoutInflater.from(mContext);
//		View v = inflater.inflate(R.layout.update_progress, null);
//		progressBar = (ProgressBar) v.findViewById(R.id.pb_update_progress);
//		builder.setView(v);
//		builder.setNegativeButton("取消", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				isDialogShow = false;
//				//要强制更新 强制退出程序
//				android.os.Process.killProcess(android.os.Process.myPid());
//				System.exit(0);//退出程序
//				//终止下载
//				isInterceptDownload = true;
//			}
//		});
//		progressDialog = builder.create();
//		progressDialog.show();
//
//		info = new VersionInfo();
//		info.setAppfileurl(Globals.WEB_URL + "/web/common/page/downloadPlugin.jsp?groupid=" + groupid);
//		info.setAppname(groupid + ".zip");
//		info.setType(TYPE_PLUGIN);
//		downloadApk();
//	}
//	/**插件下载完成后*/
//	public void afterDownloadPlugin(){
//		try {
//			//解压插件包
//			UnzipUtil.unzipFile(getDownloadPath() + info.getAppname(), Globals.LOCAL_PAGE_PATH);
//			//加载用户插件
//			loadPluginsOfUser(groupid, userid);
//			//重新加载页面
//			isPageReload = "true";
//			((SshionActivity)mContext).webView.reload();
//		} catch (Exception e) {
//			Globals.log("解压插件", e, mContext);
//		}
//	}
//	/**加载用户插件*/
//	public static boolean loadPluginsOfUser(String groupid, String userid) throws Exception {
//		File file = new File(Globals.LOCAL_PAGE_PATH + "/" + USER_PLUGIN_FILE_NAME);
//		if(file.exists()){
//			file.delete();
//		}
//		//获取配置数据
//		Map<String, String> params = new Hashtable<String, String>();
//		params.put("groupid", groupid);
//		params.put("userid", userid);
//		String plugins = StringUtil.null2String(HttpUtil.post(
//			Globals.WEB_URL + "/app/plugins/sys/main/bz/version.xp?doAction=getPluginsOfUser", params
//		)).trim();
//		if(plugins.length() == 0){
//			return false;
//		}
//		new JSONObject(plugins);//防止非法JSON数据
//		//生成配置文件
//		file.createNewFile();
//		FileOutputStream fos = new FileOutputStream(file);
//		fos.write(plugins.getBytes("utf-8"));
//		fos.flush();
//		fos.close();
//
//		return true;
//	}
//	/**获取我的插件列表**/
//	public static JSONArray getMyPluginList() throws Exception {
//		JSONArray retArray = new JSONArray();
//		File file = new File(Globals.LOCAL_PAGE_PATH + "/" + USER_PLUGIN_FILE_NAME);
//		if(file.exists()){//存在用户配置文件
//			JSONObject plugins = fileToJson(file);
//			if(plugins.has("plugins")){
//				JSONArray pluginArray = plugins.getJSONArray("plugins");
//				for(int i=0; i<pluginArray.length(); i++){
//					JSONObject plugin = pluginArray.getJSONObject(i);
//					String pluginfolderapp = plugin.getString("pluginfolderapp");
//					File pluginFile = new File(Globals.LOCAL_PAGE_PATH + "/" + PLUGIN_DIR + "/" + pluginfolderapp + "/" + PLUGIN_FILE_NAME);
//					if(!pluginFile.exists()){
//						continue ;
//					}
//
//					JSONObject infoJson = fileToJson(pluginFile);
//					JSONObject retJson = new JSONObject();
//					retJson.put("plugininstname", plugin.getString("plugininstname"));
//					retJson.put("buiid", plugin.getString("buiid"));
//					retJson.put("url", infoJson.getString("url"));
//					retJson.put("plugindesc", plugin.getString("plugindesc"));
//					//背景颜色
//					String plugininsticonbgcolor = StringUtil.null2String(plugin.getString("plugininsticonbgcolor")).trim();
//					if(plugininsticonbgcolor.length() == 0){
//						retJson.put("plugininsticonbgcolor", "#6CD0A1");
//					}else{
//						retJson.put("plugininsticonbgcolor", plugininsticonbgcolor);
//					}
//					//插件图标
//					String icon = "/plugininsticon/" + plugin.getString("plugininsticon") + ".jpg";
//					if(!new File(Globals.LOCAL_PAGE_PATH + icon).exists()){//插件图标不存在
//						icon = "/common/img/default_plugin.png";
//					}
//					retJson.put("icon", icon);
//
//					if(infoJson.has("opentype")){
//						retJson.put("opentype", infoJson.getString("opentype"));
//					}else{
//						retJson.put("opentype", "");
//					}
//					retArray.put(retJson);
//				}
//			}
//		}
//		return retArray;
//	}
//}
