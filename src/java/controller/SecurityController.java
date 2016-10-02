/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.UserHRM;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
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

    UserHRM user;
    UserModel userModel;

    /**
     * Creates a new instance of SecurityController
     */
    public SecurityController() {
        user = new UserHRM();
        userModel = new UserModel();
    }

    //Getter and Setter
    public UserHRM getUser() {
        return user;
    }

    public void setUser(UserHRM user) {
        this.user = user;
    }

    public void logIn(ActionEvent evt) {
        try {
            UserHRM temp = userModel.checkUser(user.getEmail(), user.getPassword());
            FacesContext context = FacesContext.getCurrentInstance();
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            if (temp != null) {
                HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
                session.setAttribute("user", temp);
                nh.handleNavigation(context, null, "index");
                //System.out.println("OK 123");
            } else {
                JsfUtil.addErrorMessage("Email hoặc mật khẩu bị sai");
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
}
