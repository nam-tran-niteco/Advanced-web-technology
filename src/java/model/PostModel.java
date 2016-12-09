/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.RoleHRM;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author Tran
 */
public class PostModel extends DBUtility implements Serializable {
    
    /**
     * Get User in friend list
     * @param userid   int
     * @param friendid int
     * @return boolean isAFriend
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public boolean isFriend(int userid, int friendid) throws ClassNotFoundException, SQLException{
        openConnection();
        boolean isAFriend = false;
        String sql = "SELECT a.friendid, \n"
                + "  FROM friend a WHERE a.userid = ? AND a.friendid = ?";
        mPst = mConn.prepareStatement(sql);
        mPst.setInt(1, userid);
        mPst.setInt(2, friendid);
        mRs = mPst.executeQuery(sql);
        if (mRs.next()) isAFriend = true;
        closeAll();
        return isAFriend;
    }
    
    /**
     * 
     */
    
}
