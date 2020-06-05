/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeamin21.gomail.base.user;

import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author yeami
 */
public class Contact implements DatabaseOperations {
    private String user,contact;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public void Create() {
        String sqlAddToContact="INSERT INTO CONTACTS(user_email,contact_email) values(?,?)";
        Connection con= ConnectDB.connect();
        try {
          PreparedStatement stm= con.prepareStatement(sqlAddToContact);
          stm.setString(1,this.getUser());
          stm.setString(2,this.getContact());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void Read() {


    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }
}
