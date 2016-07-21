package com.beesightsoft.demo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tam on 20/07/2016.
 */
public class People  implements Serializable {
    private String mName;
    private  boolean mGender;
    private int mAge;
    private ArrayList<String> mHobbies;
    private String mCountry;

    public People() {
        mHobbies = new ArrayList<String>();
    }

    public People(String name,
                  boolean gender,
                  int age,
                  ArrayList<String> hobbies,
                  String country) {
        mName = name;
        mGender = gender;
        mAge = age;
        mHobbies = hobbies;
        mCountry = country;
    }

    public String getName() {
        return mName;
    }

    public boolean getGender() {
        return mGender;
    }

    public int getAge() {
        return mAge;
    }

    public ArrayList<String> getHobbies() {
        return mHobbies;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setGender(boolean gender) {
        mGender = gender;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        mHobbies = hobbies;
    }

    public void setCountry(String country) {
        mCountry = country;
    }
}
