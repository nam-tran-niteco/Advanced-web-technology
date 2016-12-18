/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Post;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.PostModel;

/**
 *
 * @author Tran
 */

@ManagedBean(name = "postController")
@ViewScoped
public class PostsController {
    
    private Post post;
    private PostModel postModel;
    private ArrayList<Post> listPost;
    
    /**
     * Constructor
     */
    public PostsController() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(params.get("id"));
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostModel getPostModel() {
        return postModel;
    }

    public void setPostModel(PostModel postModel) {
        this.postModel = postModel;
    }

    public ArrayList<Post> getListPost() {
        return listPost;
    }

    public void setListPost(ArrayList<Post> listPost) {
        this.listPost = listPost;
    }
    
    
    
}
