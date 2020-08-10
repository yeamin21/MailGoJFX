package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DraftMails extends Mails implements DatabaseOperations{
    final Connection connection= ConnectDB.connect();
    public ArrayList<DraftMails>mails=new ArrayList<>();
    String user;
    @Override
    public void setUser(String user) {
    this.user=user;
    }

    @Override
    public String getUser() {
        return this.user;
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
    String SQL_READ="SELECT * FROM mailgo.draft where sender_email='"+getUser()+"'";
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(SQL_READ);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                DraftMails draftMails=new DraftMails();
                draftMails.setBody(resultSet.getString("body"));
                draftMails.setReceiver(resultSet.getString("receiver_email"));
                draftMails.setSubject(resultSet.getString("subject"));
                draftMails.setSender(resultSet.getString("sender_email"));
                mails.add(draftMails);
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

    }
}
