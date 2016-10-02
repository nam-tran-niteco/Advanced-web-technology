/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.RoleHRM;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MRSON
 */
public class RoleModel extends DBUtility implements Serializable {

    public List<RoleHRM> getAllRole() throws ClassNotFoundException, SQLException {
        List<RoleHRM> list = new ArrayList<>();
        openConnection();
        mSt = mConn.createStatement();
        String sql = "SELECT a.role_id, a.role_name, a.note \n"
                + "  FROM role_telsoft a";
        mRs = mSt.executeQuery(sql);
        while (mRs.next()) {
            int roleID = mRs.getInt("role_id");
            String roleName = mRs.getString("role_Name");
            RoleHRM role = new RoleHRM(roleID, roleName);
            list.add(role);
        }
        closeAll();
        return list;
    }
    /*
        Method: add
     */
    public int addRole(RoleHRM role) throws SQLException, ClassNotFoundException {
        int iResult = -1;
		long id = getNextSequence("seq_role_telsoft");
        openConnection();       
        String strSQL = "Insert into role_telsoft(role_id,role_name,note) values(?,?,?)";
        mPst = mConn.prepareStatement(strSQL);
        mPst.setLong(1, id);
        mPst.setString(2, role.getRoleName());
        mPst.setString(3, "Note");
        iResult = mPst.executeUpdate();
        closeAll();
        return iResult;
    }

    public int updateRole(RoleHRM role) throws SQLException, ClassNotFoundException {
        int iResult = -1;
        openConnection();
        String strSQL = "Update role_telsoft Set role_name=?,note=? Where role_id=?";
        mPst = mConn.prepareStatement(strSQL);
        mPst.setString(1, role.getRoleName());
        mPst.setString(2, "Note");
        mPst.setInt(3, role.getRoleID());
        iResult = mPst.executeUpdate();
        closeAll();
        return iResult;
    }

    public int deleteRole(RoleHRM role) throws SQLException, ClassNotFoundException {
        int iResult = -1;
        openConnection();
        String strSQL = "Delete role_telsoft Where role_id=?";
        mPst = mConn.prepareStatement(strSQL);
        mPst.setInt(1, role.getRoleID());
        iResult = mPst.executeUpdate();
        closeAll();
        return iResult;
    }
}
