/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.UserHRM;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DateUtil;

/**
 * Class UserModel1 Date 23/04/2014 15:52:37
 *
 * @author
 */
public class UserModel1 extends DBUtility implements Serializable {

    ////////////////////////////////////////////////////////
    /**
     * getAll() UserHRM Table
     *
     * @return List - UserHRM
     * @author
     */
    ////////////////////////////////////////////////////////
    public List<UserHRM> getAll() throws Exception {
        List<UserHRM> returnValue = new ArrayList<UserHRM>();
        try {
            openConnection();
            String strSQL = "SELECT * FROM USER_TELSOFT";
            mPst = mConn.prepareStatement(strSQL);
            mRs = mPst.executeQuery();
            while (mRs.next()) {
                UserHRM user = new UserHRM();
                user.setUserId(mRs.getInt("USER_ID"));
                user.setUserName(mRs.getString("USER_NAME"));
                user.setFullName(mRs.getString("FULL_NAME"));
                user.setBirthDay(mRs.getTimestamp("BIRTHDAY"));
                user.setEmail(mRs.getString("EMAIL"));
                user.setSalary(mRs.getFloat("SALARY"));
                user.setStatus(mRs.getInt("STATUS"));
                user.setRoleId(mRs.getInt("ROLE_ID"));
                user.setPassword(mRs.getString("PASSWORD"));
                returnValue.add(user);
            }
        } finally {
            closeAll();
        }
        return returnValue;
    }

    ////////////////////////////////////////////////////////
    /**
     * checkUser(String email, String password)
     *
     * @param email
     * @param password
     * @return - UserHRM
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public UserHRM checkUser(String email, String password) throws ClassNotFoundException, SQLException  {
        UserHRM user = null;
        try {
            openConnection();
            String strSQL = "SELECT * FROM USER_TELSOFT WHERE EMAIL=? AND PASSWORD = ?";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setString(1, email);
            mPst.setString(2, password);
            mRs = mPst.executeQuery();
            if (mRs.next()) {
                user = new UserHRM();
                user.setUserId(mRs.getInt("USER_ID"));
                user.setUserName(mRs.getString("USER_NAME"));
                user.setFullName(mRs.getString("FULL_NAME"));
                user.setBirthDay(mRs.getTimestamp("BIRTHDAY"));
                user.setEmail(mRs.getString("EMAIL"));
                user.setSalary(mRs.getFloat("SALARY"));
                user.setStatus(mRs.getInt("STATUS"));
                user.setRoleId(mRs.getInt("ROLE_ID"));
                user.setPassword(mRs.getString("PASSWORD"));
            }
        }
        finally {
            closeAll();
        }
        return user;
    }

    ////////////////////////////////////////////////////////
    /**
     * getUserByRoleId(int roleId) Table
     *
     * @param roleId
     * @return List - UserHRM
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public List<UserHRM> getUserByRoleId(int roleId) throws ClassNotFoundException, SQLException {
        List<UserHRM> returnValue = new ArrayList<>();
        try {
            openConnection();
            String strSQL = "SELECT * FROM USER_TELSOFT WHERE ROLE_ID=?";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setInt(1, roleId);
            mRs = mPst.executeQuery();
            while (mRs.next()) {
                UserHRM user = new UserHRM();
                user.setUserId(mRs.getInt("USER_ID"));
                user.setUserName(mRs.getString("USER_NAME"));
                user.setFullName(mRs.getString("FULL_NAME"));
                user.setBirthDay(mRs.getTimestamp("BIRTHDAY"));
                user.setEmail(mRs.getString("EMAIL"));
                user.setSalary(mRs.getFloat("SALARY"));
                user.setStatus(mRs.getInt("STATUS"));
                user.setRoleId(mRs.getInt("ROLE_ID"));
                user.setPassword(mRs.getString("PASSWORD"));
                returnValue.add(user);
            }
        } finally {
            closeAll();
        }
        return returnValue;
    }

    ////////////////////////////////////////////////////////
    /**
     * getUserByRoleId(int userId) Table
     *
     * @param userId
     * @return List - UserHRM
     * @author
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public List<UserHRM> getUserByRUserId(int userId) throws ClassNotFoundException, SQLException {
        List<UserHRM> returnValue = new ArrayList<>();
        try {
            openConnection();
            String strSQL = "SELECT * FROM USER_TELSOFT WHERE ID=?";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setInt(1, userId);
            mRs = mPst.executeQuery();
            while (mRs.next()) {
                UserHRM user = new UserHRM();
                user.setUserId(mRs.getInt("USER_ID"));
                user.setUserName(mRs.getString("USER_NAME"));
                user.setFullName(mRs.getString("FULL_NAME"));
                user.setBirthDay(mRs.getTimestamp("BIRTHDAY"));
                user.setEmail(mRs.getString("EMAIL"));
                user.setSalary(mRs.getFloat("SALARY"));
                user.setStatus(mRs.getInt("STATUS"));
                user.setRoleId(mRs.getInt("ROLE_ID"));
                user.setPassword(mRs.getString("PASSWORD"));
                returnValue.add(user);
            }
        } finally {
            closeAll();
        }
        return returnValue;
    }
    
    ////////////////////////////////////////////////////////
    /**
     * Add UserHRM
     *
     * @param user
     * @return long - sequence
     * @author
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public long addUser(UserHRM user) throws Exception {
        try {
//            long iID = getNextSequence("SEQ_USER");
            openConnection();
            String strSQL = "INSERT INTO USER_TELSOFT VALUES (?,?,?,?,?,?,?,?,?)";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setInt(1, 0);
            mPst.setString(2, user.getUserName());
            mPst.setString(3, user.getFullName());
            mPst.setTimestamp(4, DateUtil.convertTimestamp(user.getBirthDay()));
            mPst.setString(5, user.getEmail());
            mPst.setFloat(6, user.getSalary());
            mPst.setInt(7, user.getStatus());
            mPst.setInt(8, user.getRoleId());
            mPst.setString(9, user.getPassword());
            mPst.executeUpdate();
//            return iID;
            return 0;

        } finally {
            closeAll();
        }
    }

    ////////////////////////////////////////////////////////
    /**
     * Edit USER_
     *
     * @param user
     * @return int
     * @author
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public long updateUser(UserHRM user) throws Exception {
        try {
            openConnection();
            String strSQL = "UPDATE USER_TELSOFT "
                    + " SET USER_NAME=?,FULL_NAME=?,BIRTHDAY=?,EMAIL=?,SALARY=?,STATUS=?,ROLE_ID=?, PASSWORD=? "
                    + "      Where USER_ID=? ";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setString(1, user.getUserName());
            mPst.setString(2, user.getFullName());
            mPst.setTimestamp(3, DateUtil.convertTimestamp(user.getBirthDay()));
            mPst.setString(4, user.getEmail());
            mPst.setFloat(5, user.getSalary());
            mPst.setInt(6, user.getStatus());
            mPst.setInt(7, user.getRoleId());
            mPst.setString(8, user.getPassword());
            mPst.setInt(9, user.getUserId());
            int r = mPst.executeUpdate();
            return r;
        } finally {
            closeAll();
        }
    }  ////////////////////////////////////////////////////////

    /**
     * Delete USER_
     *
     * @param user
     * @return int
     * @author
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public int deleteUser(UserHRM user) throws Exception {
        try {
            openConnection();
            String strSQL = "Delete USER_TELSOFT "
                    + "      Where USER_ID = ? ";
            mPst = mConn.prepareStatement(strSQL);
            mPst.setInt(1, user.getUserId());
            int r = mPst.executeUpdate();
            return r;
        } finally {
            closeAll();
        }
    }  ////////////////////////////////////////////////////////

    /**
     * Delete multiple USER_
     *
     * @param listUser
     * @return int
     * @author
     * @throws java.lang.Exception
     */
    ////////////////////////////////////////////////////////
    public int deleteUser(UserHRM[] listUser) throws Exception {
        int iResult = 0;
        try {
            openConnection();
            mConn.setAutoCommit(false);
            String strSQL = "Delete USER_TELSOFT "
                    + "      Where USER_ID=? ";
            mPst = mConn.prepareStatement(strSQL);
            int iCount = 0;
            for (UserHRM user : listUser) {
                mPst.setInt(1, user.getUserId());
                mPst.addBatch();
                iCount++;
                if (iCount % 500 == 0) {
                    mPst.executeBatch();
                    mPst.clearBatch();
                }
            }
            mPst.executeBatch();
            mPst.clearBatch();
            mConn.commit();
            iResult = 1;
        } finally {
            mConn.setAutoCommit(false);
            closeAll();
        }
        return iResult;
    }
}
