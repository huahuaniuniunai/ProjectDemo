package com.example.projectdemo.util.tool;

import android.content.Context;
import android.content.res.AssetManager;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UnzipUtil {
	/****
	 * 解压
	 * 
	 * @param zipPath zip文件路径
	 * @param desPath
	 *            解压的目的地点
	 * @param ecode
	 *            文件名的编码字符集
	 */
	public static void unzipFile(String zipPath, String desPath) {

		File zipFile = new File(zipPath);
		if (!zipFile.exists())
			throw new RuntimeException("zip file " + zipPath + " does not exist.");
		File desFile = new File(desPath);
		if(desFile.exists()){
			deleteDir(desFile);
		}

		Project proj = new Project();
		Expand expand = new Expand();
		expand.setProject(proj);
		expand.setTaskType("unzip");
		expand.setTaskName("unzip");
		expand.setSrc(zipFile);
		expand.setDest(desFile);
		expand.setEncoding("UTF-8");
		expand.execute();
	}
	/**拷贝assests下面的文件**/
	public static String DEFAULT_ZIP_NAME = "app.zip";
	public static void unzipAssets(Context context) {
		AssetManager assetManager = context.getAssets();
		try {
			File file = new File(context.getFilesDir() + "/" + DEFAULT_ZIP_NAME);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			InputStream is = assetManager.open(DEFAULT_ZIP_NAME);
			int ret = 0;
			while ((ret = is.read(bytes)) > 0) {

				fos.write(bytes, 0, ret);
			}
			fos.flush();
			fos.close();
			
			unzipFile(context.getFilesDir() + "/" + DEFAULT_ZIP_NAME, Globals.LOCAL_PAGE_PATH);
			new File(context.getFilesDir() + "/" + DEFAULT_ZIP_NAME).delete();
		} catch (Exception e) {
			Globals.log("拷贝assests下文件", e, context);
		}
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

}
