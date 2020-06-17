package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArchivedMails extends Mails implements DatabaseOperations {
    Connection con= ConnectDB.connect();
    String loggedInUser;

    @Override
    public void Create() {
        String SQL_ADDTOCONTACT="INSERT INTO ARCHIVE(`email`,`mail_id`) VALUES(?,?)";

        try{
            PreparedStatement stm = con.prepareStatement(SQL_ADDTOCONTACT);
            stm.setString(1,this.loggedInUser);

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
