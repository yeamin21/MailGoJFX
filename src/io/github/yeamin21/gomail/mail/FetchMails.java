/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yeamin21.gomail.mail;

import io.github.yeamin21.gomail.ui.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yeami
 */
public class FetchMails {
    Connection con=ConnectDB.connect();
    public ArrayList<Mails>mails=new ArrayList<>();


    public FetchMails(String userEmail) {
        
        
        try {
            String SQL_GETMAILS="SELECT * FROM MAIL WHERE RECEIVER_EMAIL= '"+userEmail+"'";
            PreparedStatement statement=con.prepareStatement(SQL_GETMAILS);
            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next())
            {
                Mails aMail=new Mails();
                aMail.setCode(resultSet.getInt("mail_id"));
                aMail.setSender(resultSet.getString("sender_email"));
                mails.add(aMail);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
    
    
}
