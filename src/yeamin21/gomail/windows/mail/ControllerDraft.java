package yeamin21.gomail.windows.mail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import yeamin21.gomail.base.mail.*;
import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.windows.signings.ControllerLogin;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDraft implements Initializable {
    @FXML
    AnchorPane anchorPaneDraft;
    @FXML
    TextArea mailBody;
    @FXML
    TableColumn cSender,cDate,cSubject,cBody,cButton;
    @FXML
    TableView aTable;
    @FXML
    Button buttonCont;
    @FXML
    final
    ObservableList<DraftMails> data= FXCollections.observableArrayList();
    @FXML
    DraftMails selectedMail;
    static final String  loggedInUser= ControllerLogin.userEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMailsToTable();


    }
    @FXML
    void addMailsToTable()
    {
       DraftMails mail=new DraftMails();
        mail.setUser(loggedInUser);
        mail.Read();
        data.addAll(mail.mails);
        cSender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        cBody.setCellValueFactory(new PropertyValueFactory<>("body"));
        aTable.setItems(data);
    }
    @FXML
    void sendMail()
    {
        selectedMail= (DraftMails) aTable.getSelectionModel().getSelectedItem();
        SwitchPane switchPane=new SwitchPane("ComposePanel.fxml",anchorPaneDraft);
        switchPane.doSwitch();
        ControllerComposePanel controllerComposePanel=switchPane.getFxmlLoader().getController();
        controllerComposePanel.setMail(selectedMail);
    }
}
