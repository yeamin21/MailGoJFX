package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import java.sql.*;
import java.util.ArrayList;

public class ArchivedMails extends Mails implements DatabaseOperations {
    Connection con= ConnectDB.connect();
    public String loggedInUser;
    public ArrayList<Mails> archivedMails=new ArrayList<>();
    Mails mail;
    public ArchivedMails(){};
    public ArchivedMails(Mails mail)
    {
        this.mail=mail;
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
        try{
            String SQL_ADDTOCONTACT="INSERT INTO ARCHIVE(`user_email`,`mail_id`) VALUES(?,?)";
            PreparedStatement stm = con.prepareStatement(SQL_ADDTOCONTACT);
            stm.setString(1,getUser());
            stm.setInt(2,this.mail.getCode());
            stm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void Read() {



        try{
            String SQL_GETARCHIVE="select archive.mail_id,sender_email,receiver_email, sending_date_time, status_read,`subject`,body from archive \n" +
                    "inner join mail on archive.mail_id=mail.mail_id\n" +
                    "inner join mail_contents on mail.mail_id=mail_contents.mail_id where receiver_email='"+getUser()+"'";
            PreparedStatement stm = con.prepareStatement(SQL_GETARCHIVE);
            ResultSet resultSet=stm.executeQuery();
            while (resultSet.next())
            {
                ArchivedMails archivedMail = new ArchivedMails();
                archivedMail.setCode(resultSet.getInt("mail_id"));
                archivedMail.setSender(resultSet.getString("sender_email"));
                archivedMail.setBody(resultSet.getString("body"));
                archivedMail.setReceiver(resultSet.getString("receiver_email"));
                archivedMail.setSubject(resultSet.getString("subject"));
                archivedMail.setDate(resultSet.getDate("sending_date_time"));
                 archivedMails.add(archivedMail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {
        String SQL_DELETE="Delete from archive where mail_id="+this.mail.getCode();
        try {
            PreparedStatement preparedStatement=con.prepareStatement(SQL_DELETE);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
