package yeamin21.gomail.windows.mail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import yeamin21.gomail.base.mail.ArchivedMails;
import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.base.mail.FetchMails;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.windows.signings.ControllerLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerArchive implements Initializable {
    @FXML
    TextArea mailBody;
    @FXML
    TableColumn cSender,cDate,cSubject,cBody,cButton;
    @FXML
    TableView aTable;
    @FXML
    ObservableList<Mails> data= FXCollections.observableArrayList();
    Mails selectedMail;
    static String loggedInUser= ControllerLogin.userEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMailsToTable();
    }
    void addMailsToTable()
    {
        ArchivedMails aMail=new ArchivedMails();
        aMail.setUser(loggedInUser);
        aMail.Read();
        for(Mails mail: aMail.archivedMails){
           data.add(mail);
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

        }
    }
    @FXML
    void DeleteSelectedMail()
    {
        new ArchivedMails(selectedMail).Delete();
    }



}
