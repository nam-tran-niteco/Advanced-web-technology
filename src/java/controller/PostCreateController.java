/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Post;
import entity.User;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.FriendModel;
import model.PostModel;

/**
 *
 * @author Tran
 */
@ManagedBean(name = "postcreate")
@RequestScoped
public class PostCreateController {

    private Post mpost;
    private User mloggedUser;
    private PostModel mpostModel;
    private FriendModel mfriendModel;
    
    private Map<String, Integer> mpostStatus = new HashMap<>();
    private Map<String, Long> friendList = new HashMap<>();
    private long[] friendidCanSeePost;
    
    public PostCreateController() {
        mpost = new Post();
        mpostModel = new PostModel();
        mfriendModel = new FriendModel();
        
        /**
         * Get current logged user info from session
         */
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        mloggedUser = (User) session.getAttribute("user");
        
        /**
         * All post status
         */
        mpostStatus.put("No one can see this post", 0);
        mpostStatus.put("Just some of my friends can see this post", 1);
        mpostStatus.put("All my friend can see this post", 2);
        mpostStatus.put("Everyone can see this post", 3);
        
        try {
            /*
            Get all friends of logged user
            */
            friendList = mfriendModel.getFriendList(mloggedUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostCreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addPost() {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            mpost.setPublish_at(currentTime);
            mpost.setUpdate_at(currentTime);
            
            mpost.setUserid(mloggedUser.getUserID());
            mpostModel.addPost(mpost, friendidCanSeePost);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Post getMpost() {
        return mpost;
    }

    public void setMpost(Post mpost) {
        this.mpost = mpost;
    }

    public PostModel getMpostModel() {
        return mpostModel;
    }

    public void setMpostModel(PostModel mpostModel) {
        this.mpostModel = mpostModel;
    }

    public Map<String, Integer> getMpostStatus() {
        return mpostStatus;
    }

    public void setMpostStatus(Map<String, Integer> mpostStatus) {
        this.mpostStatus = mpostStatus;
    }

    public Map<String, Long> getFriendList() {
        return friendList;
    }

    public void setFriendList(Map<String, Long> friendList) {
        this.friendList = friendList;
    }

    public long[] getFriendidCanSeePost() {
        return friendidCanSeePost;
    }

    public void setFriendidCanSeePost(long[] friendidCanSeePost) {
        this.friendidCanSeePost = friendidCanSeePost;
    }
    
}
