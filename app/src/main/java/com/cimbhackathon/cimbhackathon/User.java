package com.cimbhackathon.cimbhackathon;

public class User {

    public String username;
    public String identityCard;
//    public String userEmail;
    public String phoneNo;
    public String address;

    public User(){

    }
    public User(String username, String identityCard  , String phoneNo , String address){
        this.username = username;
        this.identityCard = identityCard;
//        this.userEmail = userEmail;
        this.phoneNo = phoneNo;
        this.address = address;
    }
}
