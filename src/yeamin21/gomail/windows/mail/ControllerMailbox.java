package yeamin21.gomail.windows.mail;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.base.mail.MailCategory;
import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.windows.signings.ControllerLogin;

public class ControllerMailbox {
    @FXML
    Pane panelMailboxBody;
    @FXML
    Button buttonComposeMail,buttonInbox,btnAddCategory;
    @FXML
    Label labelUser;
    @FXML
    TextField txtFieldCategory;
    @FXML
    ListView scrollPane;

    String categories[]=new String[100];
    @FXML
    public void initialize(){
        labelUser.setText(ControllerLogin.userEmail);
        new SwitchPane("mail\\Inbox.fxml",panelMailboxBody).doSwitch();
        scrollPane.setOnMouseClicked(event ->
        System.out.println(scrollPane.getSelectionModel().getSelectedItem()));


        MailCategory readMailCategory=new MailCategory();
        readMailCategory.setUser(ControllerLogin.userEmail);
        readMailCategory.Read();
        for (MailCategory mailcategory:readMailCategory.mailCategories) {
            scrollPane.getItems().add(mailcategory.getCategoryName());
        }
    }


    @FXML
    void AddNewCategory()
    {
        DatabaseOperations mailCategory=new MailCategory();
        mailCategory.setUser(ControllerLogin.userEmail);
        ((MailCategory)mailCategory).setCategoryName(txtFieldCategory.getText());
        mailCategory.Create();
    }
    @FXML
    void AddNewProjectPanel() {
        new SwitchPane("mail\\ComposePanel.fxml",panelMailboxBody).doSwitch();
    }
    @FXML
    void actionInbox()
    {
        new SwitchPane("mail\\Inbox.fxml",panelMailboxBody).doSwitch();
    }
    @FXML
    void actionArchive(){new SwitchPane("mail\\Archive.fxml",panelMailboxBody).doSwitch();}
    @FXML
    void actionContact(){new SwitchPane("user\\Contact.fxml",panelMailboxBody).doSwitch();}


}

