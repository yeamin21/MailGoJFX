package yeamin21.gomail.windows.mail;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import yeamin21.gomail.base.mail.*;
import yeamin21.gomail.base.user.UserContacts;
import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.windows.signings.ControllerLogin;

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
    final
    ObservableList<Mails> data= FXCollections.observableArrayList();
    @FXML
    final
    ObservableList<MailCategory>categories=FXCollections.observableArrayList();
    @FXML
    ComboBox comboCategory;
    @FXML
    Button btnAddToCategory;
    Mails selectedMail;
    static final String  loggedInUser=ControllerLogin.userEmail;

   @Override
    public void initialize(URL location, ResourceBundle resources) {

       addMailsToTable();
       addCategoriesToCombobox();



    }

    @FXML
    public void search(MailCategory mailCategory)
    {
        data.clear();
        FetchMails mail=new FetchMails();
        mail.setUser(loggedInUser);
        mail.Read(mailCategory);
        data.addAll(mail.mails);
        cSender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        cSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        cBody.setCellValueFactory(new PropertyValueFactory<>("body"));
        aTable.setItems(data);

    }
    @FXML
    void getCat()
    {
                MailCategory ca= (MailCategory) comboCategory.getSelectionModel().getSelectedItem();
                FetchMails categorizeMail=new FetchMails();
                categorizeMail.addCategory(selectedMail,ca);
    }
    void addCategoriesToCombobox()
    {
        MailCategory readMailCategory=new MailCategory();
        readMailCategory.setUser(ControllerLogin.userEmail);
        readMailCategory.Read();
        comboCategory.setPromptText("Add to Category");
        categories.addAll(readMailCategory.mailCategories);
        comboCategory.setItems(categories);
    }
    void addMailsToTable()
    {
        FetchMails mail=new FetchMails();
        mail.setUser(loggedInUser);
        mail.Read();
        data.addAll(mail.mails);

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
    @FXML
    void AddToArchive()
    {
        DatabaseOperations archivedMails=new ArchivedMails(selectedMail);
        archivedMails.setUser(ControllerLogin.userEmail);
        archivedMails.Create();

    }

    @FXML
    void replyToMail(){

       SwitchPane switchPane=new SwitchPane("ComposePanel.fxml",anchorPaneInbox);
       switchPane.doSwitch();
       ControllerComposePanel controllerComposePanel=switchPane.getFxmlLoader().getController();
       controllerComposePanel.setMail(selectedMail);
    }




}
