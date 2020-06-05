/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yeami
 */
public class FetchMails implements DatabaseOperations {
    Connection con=ConnectDB.connect();
    public ArrayList<Mails>mails=new ArrayList<>();
    String userEmail;

    public FetchMails(String userEmail) {

        this.userEmail=userEmail;
    }


    @Override
    public void Create() {

    }

    @Override
    public void Read() {

        try {
            String SQL_GETMAILS="SELECT * FROM mail inner join mail_contents on  mail.mail_id=mail_contents.mail_id WHERE RECEIVER_EMAIL= '"+userEmail+"'";
            PreparedStatement statement=con.prepareStatement(SQL_GETMAILS);
            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next())
            {
                Mails aMail=new Mails();
                aMail.setCode(resultSet.getInt("mail_id"));
                aMail.setSender(resultSet.getString("sender_email"));
                aMail.setBody(resultSet.getString("body"));
                aMail.setSubject(resultSet.getString("subject"));
                mails.add(aMail);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }
}
