/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.FriendModel;
import model.UserModel;
import util.Constant;

/**
 *
 * @author Tran
 */
@ManagedBean(name = "profile")
@RequestScoped
public class UserProfileController {

    /**
     * Fields
     */
    private User mloggedUser;
    private UserModel muserModel;
    private FriendModel mfriendModel;
    private ArrayList<User> mlistUser;
    private Constant constant;
    /**
     * Constructor
     */
    public UserProfileController() {
        
        /**
         *  Get current logged user info from session
         */
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        mloggedUser = (User) session.getAttribute("user");
        
        try {
            
            /**
             *  Get User Profile by logged user ID
             */
            muserModel = new UserModel();
            mfriendModel = new FriendModel();
            mloggedUser = muserModel.getUserByID(mloggedUser);
            mlistUser = muserModel.getAllUserByUserId(mloggedUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  User profile Updated
     */
    public void saveUser() {
        if ( mloggedUser!= null && muserModel != null ) {
            try {
                muserModel.updateUser(mloggedUser);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * void addFriend
     * @param friend
     */
    public void addFriend(User friend) {
        if ( friend != null ) {
            try {
                mfriendModel.addFriend(mloggedUser, friend);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * updateFriendStatus
     * @param friend
     * @param status
     */
    public void updateFriendStatus(User friend, int status) {
        if ( friend != null ) {
            try {
                mfriendModel.updateFriendStatus(mloggedUser, friend, status);
                mlistUser = muserModel.getAllUserByUserId(mloggedUser);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /** 
     *  Getter and Setter
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

    public ArrayList<User> getMlistUser() {
        return mlistUser;
    }

    public void setMlistUser(ArrayList<User> mlistUser) {
        this.mlistUser = mlistUser;
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
    }
    
}
