/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import util.Constant;

/**
 *
 * @author Tran
 */
public class FriendModel extends DBUtility implements Serializable{
    
    ////////////////////////////////////////////////////////
    /**
     * isFriend() User
     * 
     * @param user User
     * @param friend User
     * @author
     * @return isFriend
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public boolean isFriend(User user, User friend) throws ClassNotFoundException, SQLException {
        boolean isFriend = false;
        try {
            openConnection();
            String strSQL = "SELECT listid FROM " + Constant.FRIEND_TABLE
                    + " WHERE userid = ? AND friendid = ?";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mPst.setLong(2, friend.getUserID());
            mRs = mPst.executeQuery();
            if (mRs.next()) isFriend = true;
        } finally {
            closeAll();
        }
        return isFriend;
    }
    
    ////////////////////////////////////////////////////////
    /**
     * addFriend() User
     * 
     * @param user User
     * @param friend User
     * @author
     * @return isSuccess
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public boolean addFriend(User user, User friend) throws ClassNotFoundException, SQLException {
        boolean isSuccess = false;
        try {
            openConnection();
            String strSQL = "INSERT INTO " + Constant.FRIEND_TABLE + " (userid, friendid, status)"
                    + " VALUES (?,?,?)";
            
            /**
             * Insert 2 times because of friendship
             */
            // first time: userid = userid, friendid = friendid
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mPst.setLong(2, friend.getUserID());
            mPst.setInt(3, Constant.PENDING_STATUS);
            mPst.executeUpdate();
            
            // second time: userid = friendid, friendid = userid
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, friend.getUserID());
            mPst.setLong(2, user.getUserID());
            mPst.setInt(3, Constant.REQUEST_STATUS);
            mPst.executeUpdate();
            
            isSuccess = true;
        } finally {
            closeAll();
        }
        return isSuccess;
    }
    
    ////////////////////////////////////////////////////////
    /**
     * updateFriendStatus() boolean
     * 
     * @param user User
     * @param friend User
     * @param status
     * @author
     * @return isSuccess
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public boolean updateFriendStatus(User user, User friend, int status) throws ClassNotFoundException, SQLException {
        boolean isSuccess = false;
        try {
            openConnection();
            String strSQL = "UPDATE " + Constant.FRIEND_TABLE 
                    + " SET STATUS = ?" 
                    + " WHERE (userid = ? AND friendid = ?) OR (userid = ? AND friendid = ?)";
            
            mPst = mConn.prepareStatement(strSQL);
            mPst.setInt(1, status);
            mPst.setLong(2, friend.getUserID());
            mPst.setLong(3, user.getUserID());
            mPst.setLong(4, user.getUserID());
            mPst.setLong(5, friend.getUserID());
            mPst.executeUpdate();
            
            isSuccess = true;
        } finally {
            closeAll();
        }
        return isSuccess;
    }
    
    ////////////////////////////////////////////////////////
    /**
     * deleteFriends() boolean
     * 
     * @param user User
     * @param friend User
     * @author
     * @return isSuccess
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public boolean deleteFriend(User user, User friend) throws ClassNotFoundException, SQLException {
        boolean isSuccess = false;
        try {
            openConnection();
            String strSQL = "DELETE " + Constant.FRIEND_TABLE 
                    + " WHERE (userid = ? AND friendid = ?) OR (userid = ? AND friendid = ?)";
            
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, friend.getUserID());
            mPst.setLong(2, user.getUserID());
            mPst.setLong(3, user.getUserID());
            mPst.setLong(4, friend.getUserID());
            mPst.executeUpdate();
            
            isSuccess = true;
        } finally {
            closeAll();
        }
        return isSuccess;
    }
    
    ////////////////////////////////////////////////////////
    /**
     * getFriendList() User
     * 
     * @param user User
     * @author
     * @return isSuccess
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public Map<String, Long> getFriendList(User user) throws ClassNotFoundException, SQLException {
        Map<String, Long> friendList = new HashMap<>();
        try {
            openConnection();
            String strSQL = "SELECT user.userid, user.username "
                    + "FROM user, friend "
                    + "WHERE user.userid = friend.friendid AND friend.userid = ?";
            
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mRs = mPst.executeQuery();
            while ( mRs.next() ) {
                friendList.put( mRs.getString("username"), mRs.getLong("userid") );
            }
        } finally {
            closeAll();
        }
        return friendList;
    }
    
    ////////////////////////////////////////////////////////
    /**
     * getFriendRequest() User
     * 
     * @param user User
     * @author
     * @return isSuccess
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public ArrayList<User> getFriendRequest(User user) throws ClassNotFoundException, SQLException {
        ArrayList<User> friendList = new ArrayList<>();
        try {
            openConnection();
            String strSQL = "SELECT * " + Constant.FRIEND_TABLE + ", " + Constant.USER_TABLE
                    + " WHERE friend.userid = ? AND friend.userid = user.userid AND status = ?";
            
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mPst.setInt(2, Constant.REQUEST_STATUS);
            mRs = mPst.executeQuery();
            
            while( mRs.next() ) {
                friendList.add(new User(
                        mRs.getLong("userid"),
                        mRs.getString("username"),
                        mRs.getString("fullname"),
                        mRs.getString("email"),
                        mRs.getString("phone"),
                        mRs.getString("hobby"),
                        mRs.getInt("status")
                ));
            }
            
        } finally {
            closeAll();
        }
        return friendList;
    }
    
}
