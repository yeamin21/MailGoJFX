package yeamin21.gomail.windows.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.base.user.UserContacts;
import yeamin21.gomail.base.user.Users;
import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.windows.mail.ControllerComposePanel;
import yeamin21.gomail.windows.signings.ControllerLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerContact implements Initializable {

    @FXML
    Pane paneContact;
    @FXML
    TableColumn cEmail,cFName,cLName,cPhoneNo;
    @FXML
    TableView aTable;
    @FXML
    final
    ObservableList<Users> data= FXCollections.observableArrayList();
    @FXML
    Button btnSendMail;
    Users selectedUser;
    static final String  loggedInUser=ControllerLogin.userEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserContacts userContacts=new UserContacts();
        userContacts.setUser(ControllerLogin.userEmail);
        userContacts.Read();
        data.addAll(userContacts.contacts);
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        aTable.setItems(data);


        aTable.setOnMousePressed(event ->
        selectedUser= (Users) aTable.getSelectionModel().getSelectedItem()
        );
    }

@FXML
    void sendMailToContact()
{

    SwitchPane switchPane=new SwitchPane("ComposePanel.fxml",paneContact);
    switchPane.doSwitch();
    ControllerComposePanel controllerComposePanel=switchPane.getFxmlLoader().getController();
    controllerComposePanel.setReceiver(selectedUser);
}
@FXML
    void onDeleteAction()
{
    UserContacts userContacts=new UserContacts();
    userContacts.setContact(selectedUser);
    userContacts.setUser(loggedInUser);
    userContacts.Delete();
}
    }
