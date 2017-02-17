package com.example.mrjab.hermes;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by yatharth on 14/02/17.
 */

public class User {
    private String userName;
    private BigInteger userID;
    private String email;
    private String password;
    private String name;
    private boolean status;
    private Date created;
    private Date modified;

    public User(BigInteger userID){
        // Establish connection with the database
        // if user exists
        // update the variables
        this.userID=userID;

    }

    //modify_user
    //new user
    //delete_user
    //load user details
    //search for user(by username)
    public void createNewUser(BigInteger userID, String name,String... more){
        // create a new user in it doesn't exist and update the db
    }

    public void deleteUser(){
        //delete the user details from database

    }

    public User searchUser(){
        // search the database and if exists, return user object
        return null;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigInteger getUserID() {
        return userID;
    }

    public void setUserID(BigInteger userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
