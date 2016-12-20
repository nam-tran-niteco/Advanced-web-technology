/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Comment;
import entity.Post;
import entity.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tran
 */
public class CommentModel extends DBUtility implements Serializable{
    
    /**
     * Get all post comments
     *
     * @param postid
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Comment> getCommentbyPostid(long postid) throws ClassNotFoundException, SQLException {
        openConnection();
        ArrayList<Comment> comments = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM comment WHERE postid = ? "
                + "ORDER BY publish_at DESC";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, postid);
        mRs = mPst.executeQuery();
        while (mRs.next()) {
            Comment comment = new Comment();
            comment.setPostid(mRs.getLong("postid"));
            comment.setUserid(mRs.getLong("userid"));
            comment.setContent(mRs.getString("content"));
            comment.setPublish_at(mRs.getTimestamp("publish_at"));
            comments.add(comment);
        }
        closeAll();
        return comments;
    }
    
    /**
     * Add comment to a post
     *
     * @param comment
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public int addComment(Comment comment) throws ClassNotFoundException, SQLException {
        openConnection();
        int success;
        String sql = "INSERT INTO comment (postid, userid, content, publish_at) "
                + "VALUES (?, ?, ?, ?)";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, comment.getPostid());
        mPst.setLong(2, comment.getUserid());
        mPst.setString(3, comment.getContent());
        mPst.setTimestamp(4, comment.getPublish_at());
        success = mPst.executeUpdate();
        
        closeAll();
        return success;
    }
}
