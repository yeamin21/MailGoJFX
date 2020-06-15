package yeamin21.gomail.base.user;

import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserContacts implements DatabaseOperations {
    private Mails mail;
    Connection con= ConnectDB.connect();
    public String loggedInUser;
    public UserContacts(Mails mail)
    {
       this.mail=mail;
    }

    @Override
    public void Create() {
        String SQL_ADDTOCONTACT="INSERT INTO CONTACTS(USER_EMAIL,CONTACT_EMAIL) VALUES(?,?)";

        try{
            PreparedStatement stm = con.prepareStatement(SQL_ADDTOCONTACT);
            stm.setString(1,this.loggedInUser);
            stm.setString(2,mail.getSender());
            stm.execute();
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
