package com.example.projectdemo.design.factory;

/**
 * 2、普通工厂模式
 * 普通工厂模式：只生产同类产品
 * 抽象工厂模式：可以生产不同类产品
 */
public abstract class Factory {
    public abstract <T extends Iphone> T createIphone(Class<T> clz);
}
