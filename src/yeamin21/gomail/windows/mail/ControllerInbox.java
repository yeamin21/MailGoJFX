package yeamin21.gomail.windows.mail;


import yeamin21.gomail.base.mail.FetchMails;
import yeamin21.gomail.base.mail.Mails;
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
    TableColumn cSender,cDate,cSubject,cBody,cButton;
    @FXML
    TableView aTable;
    ObservableList<Mails> data= FXCollections.observableArrayList();;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    private void mew() {
        System.out.println("C");
    }
}
