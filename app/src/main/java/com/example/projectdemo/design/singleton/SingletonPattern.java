package com.example.projectdemo.design.singleton;

/**
 * 单例模式核心思想：
 * 1、构造函数私有化，通过一次静态方法获取一个唯一实例
 * 2、线程安全
 * 推荐使用DCL方式和静态内部类的方式来创建单例模式
 */

// 恶汉模式
/*public class SingletonPattern {
    private static SingletonPattern singletonDemo = new SingletonPattern();
    private SingletonPattern() {}
    private static SingletonPattern getInstance() {
        return singletonDemo;
    }
}*/

// 懒汉模式
/*public class SingletonPattern {
    private static SingletonPattern singletonDemo = null;
    private SingletonPattern() {}
    private static SingletonPattern getInstance() {
        if (singletonDemo == null) {
            singletonDemo = new SingletonPattern();
        }
        return singletonDemo;
    }
}*/

// 枚举
/*public enum  SingletonDemo {
    INSTANCE;
    public void dosomthing() {

    }
}*/

// DCL式(Double Check Lock)
/*public class SingletonDemo {
    private static SingletonDemo mInstance;
    private SingletonDemo() {}
    public static SingletonDemo getmInstance() {
        if (mInstance == null) {
            synchronized (SingletonDemo.class) {
                if (mInstance == null) {
                    mInstance = new SingletonDemo();
                }
            }
        }
        return mInstance;
    }
}*/

// 静态内部类模式
public class SingletonPattern {
    private SingletonPattern() {
    }

    private static class SingletonHolder {
        private static final SingletonPattern SINGLETON_DEMO = new SingletonPattern();
    }

    private static SingletonPattern getInstance() {
        return SingletonHolder.SINGLETON_DEMO;
    }
}
