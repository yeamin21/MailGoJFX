package yeamin21.gomail.windows.mail;

import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.windows.signings.ControllerLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ControllerMailbox {
    @FXML
    Pane panelMailboxBody;
    @FXML
    Button buttonComposeMail,buttonInbox;
    @FXML
    Label labelUser;


    @FXML
    public void initialize(){
        labelUser.setText(ControllerLogin.userEmail);
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

