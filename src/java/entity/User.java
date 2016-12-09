/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Tran
 */
public class User implements Serializable{
    
    private long userID;
    private String username = "";
    private String password = "";
    private String fullname = "";
    private String email = "";
    private String phone = "";
    private String hobby = "";
    private int friendStatus;

    public User() {
        
    }
    
    public User(long userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public User(long userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    
    public User(long userID, String username, String fullname, String email, String phone, String hobby, int friendStatus) {
        this.userID = userID;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.hobby = hobby;
        this.friendStatus = friendStatus;
    }

    public User(long userID, String username, String password, String fullname, String email, String phone, String hobby) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.hobby = hobby;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }
    
}
