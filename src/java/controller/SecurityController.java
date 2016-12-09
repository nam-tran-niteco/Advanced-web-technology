/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.UserModel;
import util.JsfUtil;

/**
 *
 * @author Mr.Son
 */
@ManagedBean
@RequestScoped
public class SecurityController {

    User user;
    UserModel userModel;

    /**
     * Creates a new instance of SecurityController
     */
    public SecurityController() {
        user = new User();
        userModel = new UserModel();
    }

    //Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void logIn(ActionEvent evt) {
        try {
            User temp = userModel.checkUser(user.getUsername(), user.getPassword());
            FacesContext context = FacesContext.getCurrentInstance();
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            if (temp != null) {
                HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                session.setAttribute("user", temp);
                nh.handleNavigation(context, null, "index");
                //System.out.println("OK 123");
            } else {
                JsfUtil.addErrorMessage("Username or password is wrong");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logOut(ActionEvent evt) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("user", null);
        NavigationHandler nh = context.getApplication().getNavigationHandler();
        nh.handleNavigation(context, null, "logout");
    }
    
    public void register(ActionEvent evt) {
        try {
            int isAddSuccess = userModel.addUser(user);
            FacesContext context = FacesContext.getCurrentInstance();
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            if ( isAddSuccess == 1 ) {
                nh.handleNavigation(context, null, "login");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
