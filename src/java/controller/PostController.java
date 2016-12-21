/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Comment;
import entity.Post;
import entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.CommentModel;
import model.FriendModel;
import model.PostModel;

/**
 *
 * @author Tran
 */
@ManagedBean(name = "singlepost")
@ViewScoped
public class PostController {

    private Post mpost;
    private PostModel mpostModel = new PostModel();
    private FriendModel friendModel = new FriendModel();

    private Comment comment = new Comment();
    private ArrayList<Comment> commentList;
    private CommentModel commentModel = new CommentModel();

    private boolean canSeePost = true;

    private boolean canEdit;

    private long currentPostId;

    private User mloggedUser;

    public PostController() {
        /**
         * Get current logged user info from session
         */
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        mloggedUser = (User) session.getAttribute("user");

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        if (params.get("id") != null) {
            try {
                currentPostId = Long.parseLong(params.get("id"));
                mpost = mpostModel.getPostbyId(currentPostId);

                // Post owner can edit his/her own post
                canEdit = mpostModel.canEditPost(currentPostId, mloggedUser.getUserID());

                // Check user can see this post (if user access this page form URL by GET)
                if (!canEdit) {
                    switch (mpost.getStatus()) {
                        case 3:
                            canSeePost = true;
                            break;
                        case 2:
                            User postOwner = new User();
                            postOwner.setUserID(mpost.getUserid());
                            canSeePost = friendModel.isFriend(mloggedUser, postOwner);
                            break;
                        case 1:
                            canSeePost = mpostModel.canSeePost(currentPostId, mloggedUser.getUserID());
                            break;
                        case 0:
                            canSeePost = false;
                            break;
                    }
                }

                if (canSeePost) commentList = commentModel.getCommentbyPostid(currentPostId);

            } catch (NumberFormatException | ClassNotFoundException | SQLException ex) {
                NavigationHandler nh = context.getApplication().getNavigationHandler();
                nh.handleNavigation(context, null, "error");
            }
        } else {
            NavigationHandler nh = context.getApplication().getNavigationHandler();
            nh.handleNavigation(context, null, "index");
        }
    }

    /**
     * void addComment
     */
    public void addComment() {
        if (!comment.getContent().equals("")) {
            try {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                comment.setPublish_at(currentTime);
                comment.setUserid(mloggedUser.getUserID());
                comment.setPostid(currentPostId);

                commentModel.addComment(comment);
                commentList = commentModel.getCommentbyPostid(currentPostId);

                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("/pages/postdetail.xhtml?id=" + currentPostId);
            } catch (IOException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FriendController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Post getMpost() {
        return mpost;
    }

    public void setMpost(Post mpost) {
        this.mpost = mpost;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    public boolean isCanSeePost() {
        return canSeePost;
    }

    public void setCanSeePost(boolean canSeePost) {
        this.canSeePost = canSeePost;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}
