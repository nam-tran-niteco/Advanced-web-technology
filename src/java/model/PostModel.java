/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Post;
import entity.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tran
 */
public class PostModel extends DBUtility implements Serializable {

    /**
     * Get All posts by user id
     *
     * @param user
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Post> getPostsbyUserId(User user) throws ClassNotFoundException, SQLException {
        openConnection();
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM post WHERE userid = ?";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, user.getUserID());
        mRs = mPst.executeQuery();
        while (mRs.next()) {
            Post post = new Post();
            post.setPostid(mRs.getLong("postid"));
            post.setTitle(mRs.getString("title"));
            post.setContent(mRs.getString("content"));
            post.setPublish_at(mRs.getTimestamp("publish_at"));
            post.setStatus(mRs.getInt("status"));
            post.setUserid(mRs.getLong("userid"));
            posts.add(post);
        }
        closeAll();
        return posts;
    }

    /**
     * Get All posts by friend id
     *
     * @param user
     * @param friend
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Post> getPostsbyFriendId(User user, User friend) throws ClassNotFoundException, SQLException {
        openConnection();
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "SELECT * \n"
                + "  FROM post, postmeta WHERE post.postid = postmeta.postid AND postmeta.userid = ? AND friendid = ?";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, user.getUserID());
        mPst.setLong(2, friend.getUserID());
        mRs = mPst.executeQuery();
        while (mRs.next()) {
            Post post = new Post();
            post.setPostid(mRs.getLong("postid"));
            post.setTitle(mRs.getString("title"));
            post.setContent(mRs.getString("content"));
            post.setPublish_at(mRs.getTimestamp("publish_at"));
            post.setStatus(mRs.getInt("status"));
            post.setUserid(mRs.getLong("userid"));
        }
        closeAll();
        return posts;
    }

    /**
     * Get post by post id
     *
     * @param postid
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Post getPostbyId(long postid) throws ClassNotFoundException, SQLException {
        openConnection();
        Post post = null;
        String sql = "SELECT * \n"
                + "  FROM post WHERE postid = ? ";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, postid);
        mRs = mPst.executeQuery();
        if (mRs.next()) {
            post = new Post();
            post.setPostid(mRs.getLong("postid"));
            post.setTitle(mRs.getString("title"));
            post.setContent(mRs.getString("content"));
            post.setPublish_at(mRs.getTimestamp("publish_at"));
            post.setUpdate_at(mRs.getTimestamp("update_at"));
            post.setStatus(mRs.getInt("status"));
            post.setUserid(mRs.getLong("userid"));
        }
        closeAll();
        return post;
    }

    /**
     * Add a new post
     *
     * @param post
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public int addPost(Post post) throws ClassNotFoundException, SQLException {
        openConnection();
        int result;
        String sql = "INSERT INTO post (title, content, publish_at, update_at, status, userid) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        mPst = mConn.prepareStatement(sql);
        mPst.setString(1, post.getTitle());
        mPst.setString(2, post.getContent());
        mPst.setTimestamp(3, post.getPublish_at());
        mPst.setTimestamp(4, post.getUpdate_at());
        mPst.setInt(5, post.getStatus());
        mPst.setLong(6, post.getUserid());
        result = mPst.executeUpdate();
        closeAll();
        return result;
    }

    /**
     * Update a post
     *
     * @param post
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public int updatePost(Post post) throws ClassNotFoundException, SQLException {
        openConnection();
        int result;
        String sql = "UPDATE post "
                + "SET title = ?, content = ?, update_at = ?, status = ?) "
                + "WHERE postid = ?";
        mPst = mConn.prepareStatement(sql);
        mPst.setString(1, post.getTitle());
        mPst.setString(2, post.getContent());
        mPst.setTimestamp(3, post.getUpdate_at());
        mPst.setInt(4, post.getStatus());
        mPst.setLong(5, post.getPostid());
        result = mPst.executeUpdate();
        closeAll();
        return result;
    }

    /**
     * Delete a post
     *
     * @param post
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public int daletePost(Post post) throws ClassNotFoundException, SQLException {
        openConnection();
        int result;
        String sql = "DELETE post "
                + "WHERE postid = ?";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, post.getPostid());
        result = mPst.executeUpdate();
        closeAll();
        return result;
    }

}
