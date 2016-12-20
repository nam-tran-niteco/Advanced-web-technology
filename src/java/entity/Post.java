/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Tran
 */
public class Post implements Serializable{
    
    private long postid;
    private String title;
    private String content;
    private Timestamp publish_at;
    private Timestamp update_at;
    private int status;
    private long userid;
    private boolean can_comment;

    public Post(long postid, String title, String content, Timestamp publish_at, int status, long userid) {
        this.postid = postid;
        this.title = title;
        this.content = content;
        this.publish_at = publish_at;
        this.status = status;
        this.userid = userid;
    }

    public Post() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    } 

    public boolean isCan_comment() {
        return can_comment;
    }

    public void setCan_comment(boolean can_comment) {
        this.can_comment = can_comment;
    }
}
