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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
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
@ManagedBean(name = "userController")
@ViewScoped
public class UserController {

    /**
     * Fields
     */
    private User mloggedUser;
    private User msiteUser;
    private UserModel muserModel;
    private FriendModel mfriendModel;
    private PostModel mpostModel;
    private ArrayList<Post> mlistPosts;

    private boolean isFriend;

    /**
     * Constructor
     */
    public UserController() {

        /**
         * Get current logged user info from session
         */
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        mloggedUser = (User) session.getAttribute("user");

        /**
         * Get parameters from url
         */
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        try {

            /**
             * Get User Profile by logged user ID
             */
            muserModel = new UserModel();
            mfriendModel = new FriendModel();
            mpostModel = new PostModel();

            if (params.get("id") != null) {
                try {
                    msiteUser = new User();
                    msiteUser.setUserID( Long.parseLong(params.get("id")) );
                    msiteUser = muserModel.getUserByID(msiteUser);
                } catch (NumberFormatException | ClassNotFoundException | SQLException ex) {
                    NavigationHandler nh = context.getApplication().getNavigationHandler();
                    nh.handleNavigation(context, null, "error");
                }
            } else {
                NavigationHandler nh = context.getApplication().getNavigationHandler();
                nh.handleNavigation(context, null, "index");
            }

            mloggedUser = muserModel.getUserByID(mloggedUser);
            
            // Check thw relationship between logged User and a searched user
            isFriend = mfriendModel.isFriend(mloggedUser, msiteUser);
            
            // get all posts by the searched user id
            mlistPosts = mpostModel.getPostsbySiteUserId(msiteUser, mloggedUser, isFriend);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * User profile Updated
     */
    public void saveUser() {
        if (mloggedUser != null && muserModel != null) {
            try {
                muserModel.updateUser(mloggedUser);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Getter and Setter
     */
    public User getMloggedUser() {
        return mloggedUser;
    }

    public void setMloggedUser(User mloggedUser) {
        this.mloggedUser = mloggedUser;
    }

    public User getMsiteUser() {
        return msiteUser;
    }

    public void setMsiteUser(User msiteUser) {
        this.msiteUser = msiteUser;
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
