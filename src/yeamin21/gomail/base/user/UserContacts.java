package yeamin21.gomail.base.user;

import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.windows.ConnectDB;
import yeamin21.gomail.windows.mail.ControllerComposePanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserContacts implements DatabaseOperations {
    private Mails mail;
    private Contact contact;
    Connection con= ConnectDB.connect();
    public String loggedInUser;
    ArrayList<Contact>contacts=new ArrayList<>();
    public UserContacts(Mails mail)
    {
       this.mail=mail;
    }
    public UserContacts(Contact contact){
        this.contact=contact;
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
        String SQL_GETCONTACTS="SELECT contact_email,first_name,last_name "+
                "from  contacts right join `user` " +
                "on contacts.contact_email=`user`.email where user_email='"+this.loggedInUser+"'";

        try{
            PreparedStatement stm = con.prepareStatement(SQL_GETCONTACTS);
            ResultSet rs=stm.executeQuery();
            while (rs.next())
            {
                Contact contact=new Contact();
                contact.setFirstName("first_name");
                contact.setEmail("contact_email");
                contact.setLastName("last_name");
                contacts.add(contact);
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
        String SQL_DELETECONTACT="DELETE FROM CONTACTS WHERE `user_email`='"+this.loggedInUser+
                "' and `contact_email`='"+this.contact.getEmail()+"'";

        try{
            PreparedStatement stm = con.prepareStatement(SQL_DELETECONTACT);
            stm.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
