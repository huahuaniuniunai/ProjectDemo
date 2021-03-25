package com.example.projectdemo.design.factory;

/**
 * 1、普通工厂模式
 */
public abstract class Factory {
    public abstract <T extends Iphone> T createIphone(Class<T> clz);
}
