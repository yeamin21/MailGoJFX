package yeamin21.gomail.windows.mail;


import javafx.scene.input.MouseEvent;
import yeamin21.gomail.base.mail.FetchMails;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.base.user.Contact;
import yeamin21.gomail.base.user.UserContacts;
import yeamin21.gomail.windows.signings.ControllerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerInbox implements Initializable {

    @FXML
    TextArea mailBody;
    @FXML
    TableColumn cSender,cDate,cSubject,cBody,cButton;
    @FXML
    TableView aTable;
    @FXML
    ObservableList<Mails> data= FXCollections.observableArrayList();
    Mails selectedMail;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
       addMailsToTable();
    }
    void addMailsToTable()
    {
        FetchMails mail=new FetchMails(ControllerLogin.userEmail);
        mail.Read();
        for(Mails aMail:mail.mails)
        {
            data.add(aMail);
        }

        cSender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        cBody.setCellValueFactory(new PropertyValueFactory<>("body"));
        aTable.setItems(data);
    }
    public void onTableClick() {
        if (aTable.getSelectionModel().getSelectedItem() != null) {
            selectedMail= (Mails) aTable.getSelectionModel().getSelectedItem();
            mailBody.setText("From: "+selectedMail.getSender()+"\n");
            mailBody.appendText("Subject: "+selectedMail.getSubject()+"\n");
            mailBody.appendText("\n"+selectedMail.getBody()+"\n");
            new FetchMails(selectedMail).Update();

        }
    }
    @FXML
    void DeleteSelectedMail()
    {
        new FetchMails(selectedMail).Delete();
    }
    @FXML
    void AddSenderToContact()
    {
        UserContacts userContacts=new UserContacts(selectedMail);
        userContacts.loggedInUser=ControllerLogin.userEmail;
        userContacts.Create();
    }


}
