/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.RoleHRM;
import entity.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.RoleModel;
import model.UserModel;
import util.JsfUtil;

/**
 * Description: UserController Class Date: 26/04/2014 11:10:48 Version: 1.0
 *
 * @author:Mr.Son
 */
@ManagedBean
@ViewScoped
public class UserController implements Serializable {

    /**
     * Fields
     */
    private User muser;
    private List<User> mlistUser;
    private UserModel muserModel;
    private List<User> mlistUserFilter;
    private User[] mlistUserSelected;
    private List<RoleHRM> mlistRole;
    private boolean mbView;
    private PersistAction mflag;

    // Operation Flag -- Enum
    public static enum PersistAction {

        SELECT,
        CREATE,
        DELETE,
        UPDATE
    }

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        try {
            //Initial
            muserModel = new UserModel();
            RoleModel temp0 = new RoleModel();
            mlistRole = temp0.getAllRole();
            mflag = PersistAction.SELECT;
            //Set Enable
            this.mbView = true;
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Add User
     *
     */
    ////////////////////////////////////////////////////////
    public void addUser(ActionEvent evt) {
        mflag = PersistAction.CREATE;
        muser = new User();
        mbView = false;
    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Edit User
     *
     * @param user *
     */
    ////////////////////////////////////////////////////////
    public void editUser(User user) {
        mflag = PersistAction.UPDATE;
        mbView = false;
        this.muser = user;

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Prepare Delete User
     *
     * @param user *
     */
    ////////////////////////////////////////////////////////
    public void preDeleteUser(User user) {
        mflag = PersistAction.DELETE;
        this.muser = user;

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Delete User
     *
     */
    ////////////////////////////////////////////////////////
    public void deleteUser() {
        try {
            //Delete database
            muserModel.deleteUser(muser);
            mlistUser.remove(muser);
            mflag = PersistAction.SELECT;
            mlistUserFilter = null;
            //Message to client
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Save User
     *
     */
    ////////////////////////////////////////////////////////
    public void saveUser(ActionEvent evt) {
        try {
            //Update database
            if (mflag == PersistAction.CREATE) {
                muserModel.addUser(muser);
                //Message to client
                JsfUtil.AddSuccessMsg();
            } else if (mflag == PersistAction.UPDATE) {
                muserModel.updateUser(muser);
                //Message to client
                JsfUtil.UpdateSuccessMsg();
            }
            //Set Status
            mlistUserFilter = null;
            mbView = true;
            mflag = PersistAction.SELECT;
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Back
     *
     */
    ////////////////////////////////////////////////////////
    public void backUser(ActionEvent evt) {
        mflag = PersistAction.SELECT;
        muser = null;
        mbView = true;
    }

    //Getter and Setter
    public PersistAction getMflag() {
        return mflag;
    }

    public void setMflag(PersistAction mflag) {
        this.mflag = mflag;
    }

    public boolean isMbView() {
        return mbView;
    }

    public void setMbView(boolean bView) {
        this.mbView = bView;
    }

    public User getMuser() {
        return muser;
    }

    public void setMuser(User user) {
        this.muser = user;
    }

    public List<User> getMlistUser() {
        return mlistUser;
    }

    public void setmlistUser(List<User> listUser) {
        this.mlistUser = listUser;
    }

    public User[] getMlistUserSelected() {
        return mlistUserSelected;
    }

    public void setMlistUserSelected(User[] listUserSelected) {
        this.mlistUserSelected = listUserSelected;
    }

    public List<User> getMlistUserFilter() {
        return mlistUserFilter;
    }

    public void setMlistUserFilter(List<User> listUserFilter) {
        this.mlistUserFilter = listUserFilter;
    }

    public List<RoleHRM> getMlistRole() {
        return mlistRole;
    }

    public void setMlistRole(List<RoleHRM> listRole) {
        this.mlistRole = listRole;
    }
}
