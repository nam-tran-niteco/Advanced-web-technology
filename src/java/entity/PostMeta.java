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
public class PostMeta implements Serializable{
    
    private int postid;
    private int userid;
    private int friendid;

    public PostMeta(int postid, int userid, int friendid) {
        this.postid = postid;
        this.userid = userid;
        this.friendid = friendid;
    }
    
    public PostMeta(){}

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }
    
    
    
}
