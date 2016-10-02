package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Class: UserHRM Date: 23/04/2014 15:52:37 Verison: 1.0
 *
 * @author: Mr.Son
 */
public class UserHRM implements Serializable {

    private static final long serialVersionUID = 1L;

    //Fields
    private int userId;
    private String userName;
    private String fullName;
    private Date birthDay;
    private String email;
    private float salary;
    private int status;
    private int roleId;
    private String password;

    //Constructor
    public UserHRM() {
    }

    //Accessors
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
