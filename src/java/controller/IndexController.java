/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Post;
import entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.FriendModel;
import model.PostModel;
import model.UserModel;

/**
 *
 * @author Tran
 */
@ManagedBean(name = "profile")
@ViewScoped
public class IndexController {

    /**
     * Fields
     */
    private User mloggedUser;
    private UserModel muserModel;
    private PostModel mpostModel;
    private ArrayList<Post> mlistPosts;
    
    
    /**
     * Constructor
     */
    public IndexController() {

        /**
         * Get current logged user info from session
         */
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        mloggedUser = (User) session.getAttribute("user");

        try {

            /**
             * Get User Profile by logged user ID
             */
            muserModel = new UserModel();
            mpostModel = new PostModel();
            mloggedUser = muserModel.getUserByID(mloggedUser);
            mlistPosts = mpostModel.getPostsbyUserId(mloggedUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     * GETTER AND SETTER
     */
    
    public User getMloggedUser() {
        return mloggedUser;
    }

    public void setMloggedUser(User mloggedUser) {
        this.mloggedUser = mloggedUser;
    }

    public UserModel getMuserModel() {
        return muserModel;
    }

    public void setMuserModel(UserModel muserModel) {
        this.muserModel = muserModel;
    }

    public ArrayList<Post> getMlistPosts() {
        return mlistPosts;
    }

    public void setMlistPosts(ArrayList<Post> mlistPosts) {
        this.mlistPosts = mlistPosts;
    }
    
}
