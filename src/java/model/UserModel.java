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
import java.util.Collections;
import java.util.Comparator;
import util.Constant;

/**
 *
 * @author Tran
 */
public class UserModel extends DBUtility implements Serializable {

    ////////////////////////////////////////////////////////
    /**
     * getUserByID() User
     *
     * @param username
     * @param password
     * @return returnUser User
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public User checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        User returnUser = null;
        try {
            openConnection();
            String strSQL = "SELECT * FROM " + Constant.USER_TABLE
                    + " WHERE username = ? AND password = md5(?)";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setString(1, username);
            mPst.setString(2, password);
            mRs = mPst.executeQuery();
            if (mRs.next()) {
                returnUser = new User(mRs.getLong("userid"), mRs.getString("username"));
            }
        } finally {
            closeAll();
        }
        return returnUser;
    }

    ////////////////////////////////////////////////////////
    /**
     * getUserByID() User
     *
     * @param user
     * @return returnUser User
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public User getUserByID(User user) throws ClassNotFoundException, SQLException {
        User returnUser = null;
        try {
            openConnection();
            String strSQL = "SELECT * FROM " + Constant.USER_TABLE
                    + " WHERE userid=?";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mRs = mPst.executeQuery();
            if (mRs.next()) {
                returnUser = new User();
                returnUser.setUserID(mRs.getLong("userid"));
                returnUser.setUsername(mRs.getString("username"));
                returnUser.setFullname(mRs.getString("fullname"));
                returnUser.setEmail(mRs.getString("email"));
                returnUser.setPhone(mRs.getString("phone"));
                returnUser.setHobby(mRs.getString("hobby"));
                if (!mRs.getString("avatar").equals("")) {
                    returnUser.setAvatar(mRs.getString("avatar"));
                }
            }
        } finally {
            closeAll();
        }
        return returnUser;
    }

    ////////////////////////////////////////////////////////
    /**
     * getAllUserByUserId() ListUser
     *
     * @param user
     * @return mlistUser ArrayList
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public ArrayList<User> getAllUserByUserId(User user) throws ClassNotFoundException, SQLException {
        ArrayList<User> mlistUser = new ArrayList<>();
        try {
            openConnection();

            /**
             * Get all user are not current user and current user's friends
             */
            String strSQL = "SELECT user.userid, username,fullname, email, phone, avatar FROM " + Constant.USER_TABLE
                    + " WHERE user.userid <> ? AND user.userid NOT IN (SELECT friendid FROM friend where friend.userid = ?)";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mPst.setLong(2, user.getUserID());
            mRs = mPst.executeQuery();
            while (mRs.next()) {
                User muser = new User();
                muser.setUserID(mRs.getLong("userid"));
                muser.setUsername(mRs.getString("username"));
                muser.setFullname(mRs.getString("fullname"));
                muser.setEmail(mRs.getString("email"));
                muser.setPhone(mRs.getString("phone"));
                if (!mRs.getString("avatar").equals("")) {
                    muser.setAvatar(mRs.getString("avatar"));
                }
                muser.setFriendStatus(0);
                mlistUser.add(muser);
            }

            /**
             * Get all user are current user's friends
             */
            strSQL = "SELECT user.userid, username,fullname, email, phone, status, avatar FROM " + Constant.USER_TABLE + ", " + Constant.FRIEND_TABLE
                    + " WHERE friend.userid = ? AND user.userid = friend.friendid";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            mRs = mPst.executeQuery();
            while (mRs.next()) {
                User muser = new User();
                muser.setUserID(mRs.getLong("userid"));
                muser.setUsername(mRs.getString("username"));
                muser.setFullname(mRs.getString("fullname"));
                muser.setEmail(mRs.getString("email"));
                muser.setPhone(mRs.getString("phone"));
                if (!mRs.getString("avatar").equals("")) {
                    muser.setAvatar(mRs.getString("avatar"));
                }
                muser.setFriendStatus(mRs.getInt("status"));
                mlistUser.add(muser);
            }
        } finally {
            closeAll();
        }
        if ( !mlistUser.isEmpty() ) Collections.sort(mlistUser, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getUsername().compareToIgnoreCase(u2.getUsername());
            }
        });
        return mlistUser;
    }

    ////////////////////////////////////////////////////////
    /**
     * Add User
     *
     * @param user User
     * @return long - sequence
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public int addUser(User user) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            String strSQL = "INSERT INTO " + Constant.USER_TABLE + " " + "(username, password, fullname, email, phone, hobby, avatar)"
                    + "VALUES (?, md5(?),?,?,?,?,?)";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setString(1, user.getUsername());
            mPst.setString(2, user.getPassword());
            mPst.setString(3, user.getFullname());
            mPst.setString(4, user.getEmail());
            mPst.setString(5, user.getPhone());
            mPst.setString(6, user.getHobby());
            mPst.setString(7, user.getAvatar());
            mPst.executeUpdate();
//            return iID;
            return 1;
        } finally {
            closeAll();
        }
    }

    ////////////////////////////////////////////////////////
    /**
     * Edit USER
     *
     * @param user User
     * @return int
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    ////////////////////////////////////////////////////////
    public int updateUser(User user) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            String strSQL = "UPDATE " + Constant.USER_TABLE
                    + " SET username=?,fullname=?,email=?,phone=?,hobby=?,avatar=?"
                    + "      Where userid=? ";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setString(1, user.getUsername());
            mPst.setString(2, user.getFullname());
            mPst.setString(3, user.getEmail());
            mPst.setString(4, user.getPhone());
            mPst.setString(5, user.getHobby());
            mPst.setString(6, user.getAvatar());
            mPst.setLong(7, user.getUserID());
            int r = mPst.executeUpdate();
            return r;
        } finally {
            closeAll();
        }
    }  ////////////////////////////////////////////////////////

    /**
     * Delete USER
     *
     * @param user User
     * @return int
     * @author
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public int deleteUser(User user) throws Exception {
        try {
            openConnection();
            String strSQL = "Delete " + Constant.USER_TABLE
                    + "      Where userid = ? ";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setLong(1, user.getUserID());
            int r = mPst.executeUpdate();
            return r;
        } finally {
            closeAll();
        }
    }  ////////////////////////////////////////////////////////
}
