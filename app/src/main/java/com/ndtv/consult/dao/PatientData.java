package com.ndtv.consult.dao;

import android.content.Context;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by arindamnath on 28/04/16.
 */
public class PatientData extends BaseDAO {

    private String name;
    private String address;
    private String gender;
    private String email;
    private String mobile;
    private String age;
    private String bloodGroup;
    private String image;

    public PatientData(Context context) {
        super(context);
    }

    @Override
    public void parse(JSONParser jsonParser, JSONObject jsonObject) {
        setName(jsonObject.get("name").toString());
        setAddress(jsonObject.get("address").toString());
        setGender(jsonObject.get("gender").toString());
        setEmail(jsonObject.get("emailId").toString());
        setMobile(jsonObject.get("mobileNo").toString());
        setAge(jsonObject.get("age").toString());
        setBloodGroup(jsonObject.get("bloodGroup").toString());
        setImage(jsonObject.get("profile").toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
