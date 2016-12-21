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
                + "FROM post WHERE userid = ? "
                + "ORDER BY update_at DESC";
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
            post.setCan_comment(mRs.getBoolean("can_comment"));
            posts.add(post);
        }
        closeAll();
        return posts;
    }

    /**
     * Get All posts by searched user id
     *
     * @param user
     * @param friend
     * @param isFriend
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ArrayList<Post> getPostsbySiteUserId(User user, User friend, boolean isFriend) throws ClassNotFoundException, SQLException {
        openConnection();
        ArrayList<Post> posts = new ArrayList<>();
        String sql;

        // get all public posts, protected 2 and protected 1 if logged user in the list that can see the post
        if (isFriend) {
            sql = "SELECT * "
                    + "FROM post "
                    + "WHERE userid = ? AND (STATUS = 3 OR STATUS = 2 OR (STATUS = 1 AND postid IN (SELECT postid FROM postmeta WHERE friendid = ?))) "
                    + "ORDER BY update_at DESC";
            mPst = mConn.prepareStatement(sql);
            mPst.setLong(1, user.getUserID());
            mPst.setLong(2, friend.getUserID());

        } // or just get all public post if they are not friend
        else {
            sql = "SELECT * "
                    + "FROM post "
                    + "WHERE userid = ? AND status = 3 "
                    + "ORDER BY update_at DESC";
            mPst = mConn.prepareStatement(sql);
            mPst.setLong(1, user.getUserID());
        }

        mRs = mPst.executeQuery();
        while (mRs.next()) {
            Post post = new Post();
            post.setPostid(mRs.getLong("postid"));
            post.setTitle(mRs.getString("title"));
            post.setContent(mRs.getString("content"));
            post.setPublish_at(mRs.getTimestamp("publish_at"));
            post.setUpdate_at(mRs.getTimestamp("update_at"));
            post.setStatus(mRs.getInt("status"));
            post.setUserid(mRs.getLong("userid"));
            post.setCan_comment(mRs.getBoolean("can_comment"));
            posts.add(post);
        }
        closeAll();
        return posts;
    }

    /**
     * A user edit his/her own post
     *
     * @param userid
     * @param postid
     * @return boolean
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public boolean canEditPost(long postid, long userid) throws ClassNotFoundException, SQLException {
        openConnection();
        boolean canEdit = false;
        String sql = "SELECT * "
                + " FROM post WHERE postid = ? AND userid = ? ";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, postid);
        mPst.setLong(2, userid);
        mRs = mPst.executeQuery();
        if( mRs.next() ) canEdit = true;
        closeAll();
        return canEdit;
    }
    
    /**
     * A user in list can see this post if post status = 1
     *
     * @param friendid
     * @param postid
     * @return boolean
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public boolean canSeePost(long postid, long friendid) throws ClassNotFoundException, SQLException {
        openConnection();
        boolean canSeePost = false;
        String sql = "SELECT * "
                + " FROM postmeta WHERE postid = ? AND friendid = ? ";
        mPst = mConn.prepareStatement(sql);
        mPst.setLong(1, postid);
        mPst.setLong(2, friendid);
        mRs = mPst.executeQuery();
        if( mRs.next() ) canSeePost = true;
        closeAll();
        return canSeePost;
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
        String sql = "SELECT * "
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
            post.setCan_comment(mRs.getBoolean("can_comment"));
        }
        closeAll();
        return post;
    }

    /**
     * Add a new post
     *
     * @param post
     * @param friendCanSeePost
     * @return ArrayList
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public int addPost(Post post, long[] friendCanSeePost) throws ClassNotFoundException, SQLException {
        openConnection();
        int result;
        String sql = "INSERT INTO post (title, content, publish_at, update_at, status, userid, can_comment) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        mPst = mConn.prepareStatement(sql);
        mPst.setString(1, post.getTitle());
        mPst.setString(2, post.getContent());
        mPst.setTimestamp(3, post.getPublish_at());
        mPst.setTimestamp(4, post.getUpdate_at());
        mPst.setInt(5, post.getStatus());
        mPst.setLong(6, post.getUserid());
        mPst.setBoolean(7, post.isCan_comment());
        result = mPst.executeUpdate();
        
        if (post.getStatus() == 1 || result == 1) {
            sql = "SELECT MAX(postid) "
                    + "FROM post "
                    + "WHERE userid = ?";
            mPst = mConn.prepareStatement(sql);
            mPst.setLong(1, post.getUserid());
            mRs = mPst.executeQuery();
            if (mRs.next()) {
                sql = "INSERT INTO postmeta (postid, friendid) "
                        + "VALUES (?, ?)";
                mPst = mConn.prepareStatement(sql);
                mPst.setLong(1, mRs.getLong(1));
                for (int i = 0; i < friendCanSeePost.length; i++) {
                    mPst.setLong(2, friendCanSeePost[i]);
                    result = mPst.executeUpdate();
                }
            }

        }

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
                + "SET title = ?, content = ?, update_at = ?, status = ?, can_comment = ? "
                + "WHERE postid = ?";
        mPst = mConn.prepareStatement(sql);
        mPst.setString(1, post.getTitle());
        mPst.setString(2, post.getContent());
        mPst.setTimestamp(3, post.getUpdate_at());
        mPst.setInt(4, post.getStatus());
        mPst.setBoolean(5, post.isCan_comment());
        mPst.setLong(6, post.getPostid());
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
