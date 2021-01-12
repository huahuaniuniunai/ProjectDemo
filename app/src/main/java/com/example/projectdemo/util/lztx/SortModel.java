package com.example.projectdemo.util.lztx;


import android.os.Parcel;
import android.os.Parcelable;

public class SortModel implements Parcelable {
    private String name;     //联系人姓名
    private String telPhone;    //电话号码
    private String letter;

    public SortModel() {
    }

    public SortModel(String name, String telPhone) {
        this.name = name;
        this.telPhone = telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    protected SortModel(Parcel in) {
        name = in.readString();
        telPhone = in.readString();
        letter = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telPhone);
        dest.writeString(letter);
    }

    public static final Creator<SortModel> CREATOR = new Creator<SortModel>() {
        @Override
        public SortModel createFromParcel(Parcel in) {
            return new SortModel(in);
        }

        @Override
        public SortModel[] newArray(int size) {
            return new SortModel[size];
        }
    };

    @Override
    public String toString() {
        return "SortModel{" +
                "name='" + name + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", letter='" + letter + '\'' +
                '}';
    }
}
