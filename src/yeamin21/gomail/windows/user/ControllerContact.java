package yeamin21.gomail.windows.user;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import yeamin21.gomail.base.user.UserContacts;
import yeamin21.gomail.base.user.Users;
import yeamin21.gomail.windows.mail.ControllerInbox;
import yeamin21.gomail.windows.signings.ControllerLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerContact implements Initializable {

    @FXML
    ListView aListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserContacts userContacts=new UserContacts();
        userContacts.setUser(ControllerLogin.userEmail);
        userContacts.Read();
        for(Users user:userContacts.contacts )
        {

        }
    }
}
