package com.example.projectdemo.design.factory;

/**
 * 6、测试
 */
public class Test {
    public static void main(String[] args) {
        IphoneFactory factory = new IphoneFactory();
        IphoneX iphoneX = factory.createIphone(IphoneX.class);
        IphoneXS iphoneXS = factory.createIphone(IphoneXS.class);
        iphoneX.power();
        iphoneXS.power();
    }
}
