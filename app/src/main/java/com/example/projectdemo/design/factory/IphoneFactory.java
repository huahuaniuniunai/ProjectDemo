package com.example.projectdemo.design.factory;

/**
 * 3、工厂生成产品抽象类，这里通过类名(ClassName)来实例化具体的类，用的是反射机制来实现。
 */
class IphoneFactory extends Factory {
    @Override
    public <T extends Iphone> T createIphone(Class<T> clz) {
        Iphone iphone = null;
        try {
            iphone = (Iphone) Class.forName(clz.getName()).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) iphone;
    }
}
