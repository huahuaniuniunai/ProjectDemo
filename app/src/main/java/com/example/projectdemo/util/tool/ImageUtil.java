package com.example.projectdemo.util.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
	/**
	 * imgPath 原图片的地址 savedPath处理后保存的路径   newWidth图片新的宽度   newHight图片新的高度
	 * 新宽度和高度有一个<=0 则按原图的比例进行缩放
	 * @throws Exception
	 */
	public static void imageScaleZoom(String imgPath, String savedPath, int newWidth, int newHeight, int quality, Context context) throws Exception {
		Bitmap desBitmap = null;
		desBitmap = startZoom(imgPath, newWidth, newHeight);
		int bmpWidth = desBitmap.getWidth();
		int bmpHeight=desBitmap.getHeight(); 
		//只缩小图片，不进行放大
		if(newWidth > bmpWidth){
			newWidth = bmpWidth;
		}
		if(newHeight > bmpHeight){
			newHeight = bmpHeight;
		}
		
		//原始图片的宽高比
		float scale = (float) bmpWidth / (float) bmpHeight;
		try {
			if (newHeight <= 0) {//按比例缩放高度
				newHeight = (int) (newWidth / scale);
			}
			if (newWidth <= 0) {//按比例绽放宽度
				newWidth = (int) (newHeight * scale);
			}
			desBitmap= ThumbnailUtils.extractThumbnail(desBitmap, newWidth, newHeight, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
			File file = getOrCreateFile(savedPath, context);
			FileOutputStream out = new FileOutputStream(file);
			
			if (desBitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			Globals.log("缩放图片", e, context);
		}
	}
	//先压缩到接近的宽高，防止内存溢出
	@SuppressWarnings("finally")
	protected static Bitmap startZoom(String imgPath, int newWidth, int newHeight) throws Exception {
		Bitmap desBitmap = null;
		try {
			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			//开始读入图片，此时把options.inJustDecodeBounds 设回true了  
			newOpts.inJustDecodeBounds = true;  
			desBitmap  = BitmapFactory.decodeStream(new FileInputStream(imgPath),null,newOpts);
			newOpts.inJustDecodeBounds = false;
			int bmpWidth= newOpts.outWidth;  
			int bmpHeight= newOpts.outHeight;  
	        int scale = 1;
	        if(newWidth>0){
	        	scale = bmpWidth/newWidth;
	        }else {
				scale = bmpHeight/newHeight;
			}
	        if(scale<1){
	        	scale = 1;
	        }
	        newOpts.inSampleSize = scale;//设置缩放比例  
	        desBitmap = BitmapFactory.decodeStream(new FileInputStream(imgPath), null, newOpts);
		} finally {
			return  desBitmap ;
		}
	}
	/**获取或创建File对象*/
	public static File getOrCreateFile(String filePath, Context context) {
		try {
			// 目录
			File path = new File(filePath.substring(0, filePath.lastIndexOf("/")));
			File file = new File(filePath);// 文件
			if (!path.exists()) {
				path.mkdirs();
			}
			// 如果文件不存在就创建文件
			if (!file.exists()) {
				file.createNewFile();
			}
			return file;
		} catch (Exception e) {
			Globals.log("创建文件", e, context);
		}
		return null;
	}
	/**
	 * 将图片保存到指定的路径
	 * @param photoBitmap
	 * @param path
	 * @param context
	 */
	public static void savePhotoToSDCard(Bitmap photoBitmap, String path, Context context){
		if (checkSDCardAvailable()) {
			File photoFile =getOrCreateFile(path,context);
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
						fileOutputStream.flush();
					}
				}
			} catch (Exception e) {
				photoFile.delete();
				e.printStackTrace();
			} finally{
				try {
					fileOutputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
	}
	
	/**
	 * Check the SD card 
	 * @return
	 */
	public static boolean checkSDCardAvailable(){
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
	
	public static void createWatermark(String imgPath, String markText, Context context) throws Exception {
		Bitmap targetBitmap = getLoacalBitmap(imgPath, true);
		Bitmap desBitmap = createWatermark(targetBitmap, markText);
		savePhotoToSDCard(desBitmap, imgPath, context);
	}
	/***为图片target添加水印文字  
		Bitmap target：被添加水印的图片  
		String mark：水印文章  ***/
	public static Bitmap createWatermark(Bitmap targetBitmap, String markText) {
		int bitmapWidth = targetBitmap.getWidth();
		int bitmapHeight = targetBitmap.getHeight();
		int textHeight = 20 * markText.split("\n").length;
		
		Bitmap bmp = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		
		//添加蒙板
		for(int y=bitmapHeight-1; y >= bitmapHeight - textHeight - 25; y--){
			for(int x=0; x<bitmapWidth; x++){
				int color = targetBitmap.getPixel(x, y);
				
				float alpha = 0.3f;
				int red = (int)(Color.red(color) * alpha);
				int green = (int)(Color.green(color) * alpha);
				int blue = (int)(Color.blue(color) * alpha);
				
				targetBitmap.setPixel(x, y, Color.argb( Color.alpha(color), red, green, blue));
			}
		}
		
		//加载原图
		Paint p = new Paint();
		p.setColor(Color.WHITE);// 水印的颜色
		p.setTextSize(12);// 水印的字体大小  
		p.setAntiAlias(true);// 去锯齿  
		canvas.drawBitmap(targetBitmap, 0, 0, p);
		
		//写字
		TextPaint textPaint = new TextPaint();
		textPaint.setColor(Color.parseColor("#ffffff"));
		textPaint.setTextSize(14.0F);
		textPaint.setAntiAlias(true);
		StaticLayout layout = new StaticLayout(
			markText, textPaint, bitmapWidth - 20, Layout.Alignment.ALIGN_NORMAL, 1.5F, 0.0F, true
		);//这里的参数300，表示字符串的长度，当满300时，就会换行
		canvas.save();
		canvas.translate(10, bitmapHeight - textHeight - 15);//写字的位置
		layout.draw(canvas);
		
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return bmp;
	}
	/**
	* 加载本地图片
	* http://bbs.3gstdy.com
	* @param url
	* @return
	*/
	public static Bitmap getLoacalBitmap(String url, boolean isMutable) throws Exception {
		Bitmap targetBitmap = getLoacalBitmap(url);
		return targetBitmap.copy(targetBitmap.getConfig(), isMutable);
	}
	/**
	* 加载本地图片
	* http://bbs.3gstdy.com
	* @param url
	* @return
	*/
	public static Bitmap getLoacalBitmap(String url) throws Exception {
		FileInputStream fis = new FileInputStream(url);
		return BitmapFactory.decodeStream(fis);
	}
	
	/**
	 * 读取图片属性：旋转的角度
	 * @param path 图片绝对路径
	 * @return degree旋转的角度
	 */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
                ExifInterface exifInterface = new ExifInterface(path);
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
        } catch (IOException e) {
                e.printStackTrace();
        }
        return degree;
    }
    /*
     * 旋转图片 
     * @param angle 
     * @param bitmap 
     * @return Bitmap 
     */  
    public static void rotaingImageView(String url, int angle, Context context) throws Exception {
		Bitmap bitmap = getLoacalBitmap(url);
        //旋转图片 动作   
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);  
        // 创建新的图片   
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);  
        savePhotoToSDCard(resizedBitmap, url, context);
    }
   /*
    * 旋转图片 
    * @param angle 
    * @param bitmap 
    * @return Bitmap 
    */  
   public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
       //旋转图片 动作   
       Matrix matrix = new Matrix();
       matrix.postRotate(angle);  
       System.out.println("angle2=" + angle);
       // 创建新的图片   
       Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
               bitmap.getWidth(), bitmap.getHeight(), matrix, true);  
       return resizedBitmap;  
   }
}
