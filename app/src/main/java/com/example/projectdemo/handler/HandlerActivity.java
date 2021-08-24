package com.example.projectdemo.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectdemo.R;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在Handler消息机制中，mHandler会作为成员变量保存在发送的消息msg中，即msg持有mHandler的引用，
 * 而mHandler是Activity的非静态内部类实例，即mHandler持有Activity的引用。
 * 例如：当我们发送一条延时消息的时候，如果此时Activity被finish了，按照JVM的垃圾回收机制，
 * 这个Activity理应被回收，但是由于我们刚刚发送延时消息，这个消息还存在于messageQueue消息队列中等待执行，
 * 这个延时消息持有对Activity的引用，所以导致Activity无法回收，进而导致内存泄漏问题。
 */

public class HandlerActivity extends AppCompatActivity {

    private Handler mHandler;
    @BindView(R.id.tv_content)
    TextView mContent;
    @BindView(R.id.iv_bit)
    ImageView mBit;
    @BindView(R.id.iv_img)
    ImageView mImage;

    /**
     * 正确写法：
     * 1、通过弱引用的方式，创建静态内部类Handler
     */
    static class MyHandler extends Handler {
        private WeakReference<HandlerActivity> activityWeakReference;
        public MyHandler(HandlerActivity activity){
            activityWeakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerActivity activity = activityWeakReference.get();
            if (activity != null) {
                //处理handler消息,比如加载图片进ImageView
                switch (msg.what) {
                    case 0:
                        Bitmap bmp = (Bitmap) msg.obj;
                        activity.mBit.setImageBitmap(bmp);
                        break;
                    case R.id.iv_img:
                        activity.mImage.setImageDrawable((Drawable) msg.obj);
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);

        /**
         * 2、在主线程创建Handler对象,应用起的时候自动创建一个Looper对象，并开启循环（ActivityThread的main()方法）
         */
        mHandler = new MyHandler(this);

        // 在子线程创建Handler对象
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        // TODO
                    }
                };
                Looper.loop();
            }
        }).start();*/

        /**
         * 3、主线程更新UI：不耗时任务直接在主线程之中通知对应的handler更新UI
         */
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_wechat);
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = bmp;
        mHandler.sendMessage(msg);

        /**
         * 3、子线程更新UI：耗时任务，在子线程进行耗时任务之后,根据结果通知对应的handler更新UI
         */
        loadImage("http://www.baidu.com/img/baidu_logo.gif", R.id.iv_img);
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 方法1：在子线程中创建消息，并使用handler对象发送
                 */
                Message message = Message.obtain();
                message.arg1 = 1;
                Bundle bundle = new Bundle();
                bundle.putString("data", "data");
                message.setData(bundle);
                mHandler.sendMessage(message);

                /**
                 * 方法2：直接切换主线程
                 */
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mContent.setText("子线程切换主线程，当前线程即为主线程，可以更新UI");
                    }
                });

                /**
                 * 方法3：在子线程中调用View的post()方法
                 */
                mContent.post(new Runnable() {
                    @Override
                    public void run() {
                        mContent.setText("子线程更新UI");
                    }
                });

                /**
                 * 方法4：在子线程中调用View.PostDelayed(Runnable,long)
                 * 对方式三对补充，long参数用于制定多少时间后运行后台进程
                 */
                mContent.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContent.setText("5秒延迟后，子线程更新UI");
                    }
                }, 5000);
            }
        }).start();
    }

    /**
     * 4、在Activity销毁时将mHandler的回调和发送的消息给移除掉
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;//防止内存泄漏
        }
    }

    /**
     * 事例：子线程加载网络图片，经三级缓存到本地内存
     * @param url
     * @param id
     */
    public void loadImage(final String url, final int id) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Drawable drawable = null;
                try {
                    drawable = Drawable.createFromStream(new URL(url).openStream(), "image.png");
                    // 模拟网络延时
                    SystemClock.sleep(2000);
                    // 更新UI需要放到主线程
                    Message message = mHandler.obtainMessage();
                    message.what = id;
                    message.obj = drawable;
                    mHandler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 错误示范：Handler的基本写法，易导致内存泄漏
    /*private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
        }

        @Override
        public boolean sendMessageAtTime(@NonNull Message msg, long uptimeMillis) {
            return super.sendMessageAtTime(msg, uptimeMillis);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };*/
}