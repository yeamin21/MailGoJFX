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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yeami
 */
public class FetchMails implements DatabaseOperations {
    public final ArrayList<Mails>mails=new ArrayList<>();
    Mails mail;
    private String loggedInUser;
    public FetchMails(Mails mail)
    {
        this.mail=mail;
    }

    public FetchMails() {

    }


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

    }

    @Override
    public void Read() {

        try {
            Connection con= ConnectDB.connect();
            String SQL_GETMAILS="SELECT mail.mail_id, mail.sender_email,mail.receiver_email, mail.sending_date_time,mail_contents.subject, mail_contents.body\n" +
                    "FROM mail\n" +
                    "inner join mail_contents on mail.mail_id=mail_contents.mail_id\n" +
                    "where receiver_email='"+getUser()+"'";
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

    public void Read(MailCategory category) {

        Connection con= ConnectDB.connect();
        try {
            String SQL_GETMAILS="SELECT mail.mail_id, mail.sender_email,mail.receiver_email, mail.sending_date_time,mail_contents.subject, mail_contents.body," +
                    " category.category_id, category_name FROM mail\n" +
                    "inner join mail_contents on mail.mail_id=mail_contents.mail_id\n" +
                    "left join mail_category on mail.mail_id=mail_category.mail_id\n" +
                    "left join category on category.category_id=mail_category.category_id where receiver_email='"+getUser()+"' and category.category_id="+category.getCategoryID();
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
          ex.printStackTrace();
        }

    }

    @Override
    public void Update() {
        Connection con= ConnectDB.connect();
        String SQL_UPDATE="UPDATE mail set status_read=true where mail_id="+this.mail.getCode();
        try {
            PreparedStatement preparedStatement=con.prepareStatement(SQL_UPDATE);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void Delete() {
        Connection con= ConnectDB.connect();
        String SQL_DELETE="Delete from mail where mail_id="+this.mail.getCode();
        try {
            PreparedStatement preparedStatement=con.prepareStatement(SQL_DELETE);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void addCategory(Mails selectedMail, MailCategory category)
    {
        Connection con= ConnectDB.connect();
        String SQL_ADDCATEGORY="INSERT INTO mail_category(category_id,mail_id) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(SQL_ADDCATEGORY);
            preparedStatement.setInt(1,category.getCategoryID());
            preparedStatement.setInt(2,selectedMail.getCode());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
