package yeamin21.gomail.windows.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import yeamin21.gomail.base.mail.FetchMails;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.base.user.UserContacts;
import yeamin21.gomail.base.user.Users;
import yeamin21.gomail.windows.mail.ControllerInbox;
import yeamin21.gomail.windows.signings.ControllerLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerContact implements Initializable {

    @FXML
    TableColumn cEmail,cFName,cLName,cPhoneNo;
    @FXML
    TableView aTable;
    @FXML
    ObservableList<Users> data= FXCollections.observableArrayList();
    Users selectedUser;
    static String  loggedInUser=ControllerLogin.userEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserContacts userContacts=new UserContacts();
        userContacts.setUser(ControllerLogin.userEmail);
        userContacts.Read();
        for(Users user:userContacts.contacts )
        {
            data.add(user);
        }
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        aTable.setItems(data);
    }
    public void onTableClick() {
        if (aTable.getSelectionModel().getSelectedItem() != null) {
            selectedUser= (Users) aTable.getSelectionModel().getSelectedItem();
        }
    }


    }
