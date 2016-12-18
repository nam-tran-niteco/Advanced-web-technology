/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MRSON
 */
public class DBUtility {

    protected Connection mConn;
    protected PreparedStatement mPst;
    protected Statement mSt;
    protected ResultSet mRs;

    //Open connection
    public void openConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("oracle.jdbc.OracleDriver");
//        String url = "jdbc:oracle:thin:@localhost:1521:THN";
//        mConn = DriverManager.getConnection(url, "telsoft01", "telsoft");
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/keepmemo";
        mConn = DriverManager.getConnection(url, "root", "");
//        String url = "jdbc:mysql://localhost/test";
//        Class.forName ("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection (url, "root", "");
    }

    //Close Connection
    public void closeConnnection() throws SQLException {
        if (mConn != null) {
            mConn.close();
            mConn = null;
        }
    }

    //Close Connection
    public void closeAll() throws SQLException {

        if (mPst != null) {
            mPst.close();
            mPst = null;
        }
        if (mSt != null) {
            mSt.close();
            mSt = null;
        }
        if (mRs != null) {
            mRs.close();
            mRs = null;
        }
        if (mConn != null) {
            mConn.close();
            mConn = null;
        }
    }

    //Get Sequence
    public long getNextSequence(String seqName) throws SQLException, ClassNotFoundException {
        openConnection();
        String strSqlIdentifier = "select " + seqName + ".NEXTVAL from dual";
        PreparedStatement pst = mConn.prepareStatement(strSqlIdentifier);
        long lMyId = -1;
        synchronized (this) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lMyId = rs.getLong(1);
            }
        }
        closeConnnection();
        return lMyId;
    }

    //Getter and Setter
    public Connection getmConn() {
        return mConn;
    }

    public void setmConn(Connection mConn) {
        this.mConn = mConn;
    }

    public PreparedStatement getmPst() {
        return mPst;
    }

    public void setmPst(PreparedStatement mPst) {
        this.mPst = mPst;
    }

    public Statement getmSt() {
        return mSt;
    }

    public void setmSt(Statement mSt) {
        this.mSt = mSt;
    }

    public ResultSet getmRs() {
        return mRs;
    }

    public void setmRs(ResultSet mRs) {
        this.mRs = mRs;
    }

}
