/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Post;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.PostModel;

/**
 *
 * @author Tran
 */
@ManagedBean(name = "singlepost")
@RequestScoped
public class PostController {

    private Post mpost;
    private PostModel mpostModel = new PostModel();

    public PostController() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        if (params.get("id") != null) {
            try {
                long postid = Long.parseLong(params.get("id"));
                mpost = mpostModel.getPostbyId(postid);
            } catch (NumberFormatException | ClassNotFoundException | SQLException ex) {
                NavigationHandler nh = context.getApplication().getNavigationHandler();
                nh.handleNavigation(context, null, "error");
            }
        } else {
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "index");
        }
    }

    public Post getMpost() {
        return mpost;
    }

    public void setMpost(Post mpost) {
        this.mpost = mpost;
    }

}
