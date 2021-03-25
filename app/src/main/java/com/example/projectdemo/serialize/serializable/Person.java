package com.example.projectdemo.serialize.serializable;

import android.content.Intent;

import java.io.Serializable;

/**
 * Intent只能传递部分数据类型的对象（基本类型及String/CharSequence类型）
 * Serializable原理是将对象序列化转换成可存储或可传输的状态。
 * 序列化后的对象可以保存对象的属性到本地文件、数据库、网络流、rmi以方便数据传输。
 * 特点：Serializable可将数据持久化方便保存，所以在需要保存或网络传输数据时选择Serializable。
 */
public class Person implements Serializable {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

/**
 * 使用方法
 */
    // 对象属性赋值
    /*Person person = new Person();
    person.name = "Tom";
    person.age = 23;
    Intent intent = new Intent(this, Two.class);
    intent.putExtra("person",person);// 直接把一个对象赋值给intent
    startActivity(intent);*/

    // 对象属性取值
    /*Intent intent = getIntent();
    Person data = (Person) intent.getSerializableExtra("person");// 直接把一个对象取出来
    String name = data.name;
    int age = data.age;
    Log.d("demo", name + age);*/