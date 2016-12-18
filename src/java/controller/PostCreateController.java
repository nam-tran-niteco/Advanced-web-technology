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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.PostModel;
import util.Constant;

/**
 *
 * @author Tran
 */
@ManagedBean(name = "postcreate")
@ViewScoped
public class PostCreateController {

    private Post mpost;
    private PostModel mpostModel;
    private Map<String, Integer> mpostStatus = new HashMap<>();
    
    public PostCreateController() {
        mpost = new Post();
        mpostModel = new PostModel();
        
        /**
         * All post status
         */
        mpostStatus.put("Private", 0);
        mpostStatus.put("Protected 1", 1);
        mpostStatus.put("Protected 2", 2);
        mpostStatus.put("Public", 3);
    }

    public void addPost() {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            mpost.setPublish_at(currentTime);
            mpost.setUpdate_at(currentTime);
            
            /**
             * Get current logged user info from session
             */
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            User mloggedUser = (User) session.getAttribute("user");
            mpost.setUserid(mloggedUser.getUserID());
            mpostModel.addPost(mpost);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
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

}
