package com.example.projectdemo.serialize.parcelable;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Intent只能传递部分数据类型的对象（基本类型及String/CharSequence类型）
 * Parcelable原理是将一个完整的对象进行分解为Intent所支持的数据类型。不推荐使用Parcelable进行数据持久化。
 * 特点：Parcelable的性能比Serializable好，在内存开销方面较小，序列化的这些操作完全由底层实现。
 * 所以在内存间数据传输时推荐使用Parcelable，如activity间传输数据.
 */
public class Person implements Parcelable {
    private String name;
    private int age;

    public Person() {
    }

    // 读取数据进行恢复
    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    // 用来创建自定义的Parcelable的对象
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    // 写数据进行保存
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}

/**
 * 使用方法
 */
    // 对象属性赋值
    /*Person person = new Person();
    person.name = "Tony";
    person.age = 25;
    Intent intent = new Intent(this, Two.class);
    intent.putExtra("person", person);// 直接把一个对象赋值给intent
    startActivity(intent);*/

    // 对象属性取值
    /*Intent intent = getIntent();
    Person data = (Person)intent.getParcelableExtra("person");// 直接把一个对象取出来
    String name = data.name;
    int age = data.age;
    Log.d("demo", name + age);*/