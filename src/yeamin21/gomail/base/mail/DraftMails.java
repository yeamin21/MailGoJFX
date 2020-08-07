package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DraftMails extends Mails implements DatabaseOperations{
    Connection connection= ConnectDB.connect();

    @Override
    public void setUser(String user) {

    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public void Create() {
        String SQL_INSERT="INSERT INTO DRAFT(subject,body,receiver_email,sender_email)VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,getSubject());
            preparedStatement.setString(2,getBody());
            preparedStatement.setString(3,getReceiver());
            preparedStatement.setString(4,getSender());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void Read()  {


    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }
}
