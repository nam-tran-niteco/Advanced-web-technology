/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.RoleHRM;
import model.RoleModel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author MRSON Annotation
 */
@ManagedBean
@ViewScoped
public class RoleController implements Serializable {

    private RoleHRM mrole;
    private List<RoleHRM> mlistRole;
    private RoleModel roleModel;
    private boolean mblnView;
    private String mflag;

    /**
     * Creates a new instance of RoleController
     */
    public RoleController() {
        try {
            roleModel = new RoleModel();
            mlistRole = roleModel.getAllRole();
            mblnView = true;
            mflag = "Select";
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method
    public void preAdd(ActionEvent evt) {
        mblnView = false;
        mflag = "add";
        mrole = new RoleHRM();
    }

    public void preEdit(RoleHRM role) {
        mblnView = false;
        mflag = "edit";
        mrole = role;
    }

    public void preDelete(RoleHRM role) {
        mrole = role;
    }

    public void back(ActionEvent evt) {
        mblnView = true;
        mflag = "select";
    }

    public void save(ActionEvent evt) {
        try {
            switch (mflag) {
                case "add":
                    roleModel.addRole(mrole);
                    break;
                case "edit":
                    roleModel.updateRole(mrole);
                    break;
            }
            mlistRole = roleModel.getAllRole();
            mblnView = true;
            mflag = "select";
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(RoleHRM role) {
        try {
            roleModel.deleteRole(role);
            mrole = new RoleHRM();
            mlistRole = roleModel.getAllRole();
            mblnView = true;
            mflag = "select";
        } catch (SQLException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RoleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    // GETTER AND SETTER
    public RoleHRM getMrole() {
        return mrole;
    }

    public void setMrole(RoleHRM mrole) {
        this.mrole = mrole;
    }

    public List<RoleHRM> getMlistRole() {
        return mlistRole;
    }

    public void setMlistRole(List<RoleHRM> mlistRole) {
        this.mlistRole = mlistRole;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public boolean isMblnView() {
        return mblnView;
    }

    public void setMblnView(boolean mblnView) {
        this.mblnView = mblnView;
    }

    public String getMflag() {
        return mflag;
    }

    public void setMflag(String flag) {
        this.mflag = flag;
    }

}
