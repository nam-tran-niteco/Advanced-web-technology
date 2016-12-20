/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Tran
 */
public class Comment {
    
    private long postid;
    private long userid;
    private String content;
    private Timestamp publish_at;

    public Comment() {
        content = "";
    }
    
    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(Timestamp publish_at) {
        this.publish_at = publish_at;
    }
    
    
    
}
