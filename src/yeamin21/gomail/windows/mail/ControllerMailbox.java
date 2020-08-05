package yeamin21.gomail.windows.mail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ListView listCategories;
    ObservableList<MailCategory>categories= FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        labelUser.setText(ControllerLogin.userEmail);
        new SwitchPane("resources\\Inbox.fxml",panelMailboxBody).doSwitch();
        MailCategory readMailCategory=new MailCategory();
        readMailCategory.setUser(ControllerLogin.userEmail);
        readMailCategory.Read();
        for (MailCategory mailcategory:readMailCategory.mailCategories) {
            categories.add(mailcategory);
        }
        listCategories.getItems().addAll(categories);

        listCategories.setOnMouseClicked(event ->
                getCategorizedMails());

    }
    @FXML
    void getCategorizedMails()
    {
        MailCategory mailCategory=(MailCategory)listCategories.getSelectionModel().getSelectedItem();
        SwitchPane switchPane=new SwitchPane("resources\\Inbox.fxml",panelMailboxBody);
        switchPane.doSwitch();
        ControllerInbox controllerInbox=switchPane.getFxmlLoader().getController();
        controllerInbox.search(mailCategory);

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
        new SwitchPane("resources\\ComposePanel.fxml",panelMailboxBody).doSwitch();
    }
    @FXML
    void actionInbox()
    {
        new SwitchPane("resources\\Inbox.fxml",panelMailboxBody).doSwitch();
    }
    @FXML
    void actionArchive(){new SwitchPane("resources\\Archive.fxml",panelMailboxBody).doSwitch();}
    @FXML
    void actionContact(){new SwitchPane("resources\\Contact.fxml",panelMailboxBody).doSwitch();}


}

