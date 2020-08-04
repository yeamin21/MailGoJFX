package yeamin21.gomail.windows.mail;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import yeamin21.gomail.base.mail.*;
import yeamin21.gomail.base.user.UserContacts;
import yeamin21.gomail.windows.SwitchPane;
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
    AnchorPane anchorPaneInbox;
    @FXML
    TextArea mailBody;
    @FXML
    TableColumn cSender,cDate,cSubject,cBody,cButton;
    @FXML
    TableView aTable;
    @FXML
    ObservableList<Mails> data= FXCollections.observableArrayList();
    @FXML
    ComboBox comboCategory;
    Mails selectedMail;
    static String  loggedInUser=ControllerLogin.userEmail;

   @Override
    public void initialize(URL location, ResourceBundle resources) {

       addMailsToTable();

       MailCategory readMailCategory=new MailCategory();
       readMailCategory.setUser(ControllerLogin.userEmail);
       readMailCategory.Read();
      comboCategory.setPromptText("Add to Category");
       for (MailCategory mailcategory:readMailCategory.mailCategories) {
           comboCategory.getItems().add(mailcategory.getCategoryName());
       }
    }
    void addMailsToTable()
    {
        DatabaseOperations mail=new FetchMails();
        mail.setUser(loggedInUser);
        mail.Read();
        for(Mails aMail: ((FetchMails) mail).mails)
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
        userContacts.loggedInUser=this.loggedInUser;
        userContacts.Create();
    }
    @FXML
    void AddToArchive()
    {
        DatabaseOperations archivedMails=new ArchivedMails(selectedMail);
        archivedMails.setUser(this.loggedInUser);
        archivedMails.Create();

    }

    @FXML
    void replyToMail(){

       SwitchPane switchPane=new SwitchPane("mail\\ComposePanel.fxml",anchorPaneInbox);
       switchPane.doSwitch();
       ControllerComposePanel controllerComposePanel=switchPane.getFxmlLoader().getController();
       controllerComposePanel.setMail(selectedMail);
    }


}
