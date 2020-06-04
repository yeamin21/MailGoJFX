package io.github.yeamin21.gomail.ui.mail;


import io.github.yeamin21.gomail.mail.FetchMails;
import io.github.yeamin21.gomail.mail.Mails;
import io.github.yeamin21.gomail.ui.signings.ControllerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerInbox implements Initializable {


    @FXML
    TableColumn cSender,cDate,cSubject,cBody;
    @FXML
    TableView aTable;
    ObservableList<Mails> data= FXCollections.observableArrayList();;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
       FetchMails mail=new FetchMails(ControllerLogin.userEmail);

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
}
