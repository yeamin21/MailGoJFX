/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yeami
 */
public class SendMail extends Mails implements DatabaseOperations {
   private String receiverEmail,senderEmail;
    Connection con=ConnectDB.connect();
    private String loggedInUser;


    @Override
    public void setUser(String user) {
        this.loggedInUser=user;
    }

    @Override
    public String getUser() {
        return loggedInUser;
    }

    @Override
    public void Create() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try{
            String sqlInsertMail = "INSERT INTO MAIL(SENDER_EMAIL,RECEIVER_EMAIL,SENDING_DATE_TIME) values(?,?,curdate())";
            preparedStatement=con.prepareStatement(sqlInsertMail,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, getSender());
            preparedStatement.setString(2, getReceiver());
            preparedStatement.executeUpdate();
            resultSet=preparedStatement.getGeneratedKeys();
            if(resultSet.next())
            {
                try{
                 int mailId=resultSet.getInt(1);                 // returns mail_id
                 String sqlInsertContents="insert into mail_contents(mail_id,`subject`,body) values(?,?,?)";
                 preparedStatement=con.prepareStatement(sqlInsertContents);
                 preparedStatement.setInt(1,mailId);
                 preparedStatement.setString(2, getSubject());
                 preparedStatement.setString(3, getBody());
                 preparedStatement.execute();
                }catch(Exception e)
               {
                   System.out.println(e);
               }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
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
