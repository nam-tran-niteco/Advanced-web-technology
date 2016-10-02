/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.RoleHRM;
import entity.UserHRM;
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
    private UserHRM muser;
    private List<UserHRM> mlistUser;
    private UserModel muserModel;
    private List<UserHRM> mlistUserFilter;
    private UserHRM[] mlistUserSelected;
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
            mlistUser = muserModel.getAll();
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
     * Add UserHRM
     *
     */
    ////////////////////////////////////////////////////////
    public void addUser(ActionEvent evt) {
        mflag = PersistAction.CREATE;
        muser = new UserHRM();
        mbView = false;
    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Edit UserHRM
     *
     * @param user *
     */
    ////////////////////////////////////////////////////////
    public void editUser(UserHRM user) {
        mflag = PersistAction.UPDATE;
        mbView = false;
        this.muser = user;

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Prepare Delete UserHRM
     *
     * @param user *
     */
    ////////////////////////////////////////////////////////
    public void preDeleteUser(UserHRM user) {
        mflag = PersistAction.DELETE;
        this.muser = user;

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Delete UserHRM
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
     * Delete Multi UserHRM
     *
     */
    ////////////////////////////////////////////////////////
    public void deleteAllUser() {
        try {
            //Delete database
            muserModel.deleteUser(mlistUserSelected);
            mlistUser = muserModel.getAll();
            mlistUserSelected = null;
            mlistUserFilter = null;
            mflag = PersistAction.SELECT;
            //Message to client

        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }

    }

    //Methods
    ////////////////////////////////////////////////////////
    /**
     * Save UserHRM
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
            mlistUser = muserModel.getAll();
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

    public UserHRM getMuser() {
        return muser;
    }

    public void setMuser(UserHRM user) {
        this.muser = user;
    }

    public List<UserHRM> getMlistUser() {
        return mlistUser;
    }

    public void setmlistUser(List<UserHRM> listUser) {
        this.mlistUser = listUser;
    }

    public UserHRM[] getMlistUserSelected() {
        return mlistUserSelected;
    }

    public void setMlistUserSelected(UserHRM[] listUserSelected) {
        this.mlistUserSelected = listUserSelected;
    }

    public List<UserHRM> getMlistUserFilter() {
        return mlistUserFilter;
    }

    public void setMlistUserFilter(List<UserHRM> listUserFilter) {
        this.mlistUserFilter = listUserFilter;
    }

    public List<RoleHRM> getMlistRole() {
        return mlistRole;
    }

    public void setMlistRole(List<RoleHRM> listRole) {
        this.mlistRole = listRole;
    }
}
