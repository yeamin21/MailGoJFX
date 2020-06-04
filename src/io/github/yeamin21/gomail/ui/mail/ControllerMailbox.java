package io.github.yeamin21.gomail.ui.mail;

import io.github.yeamin21.gomail.ui.SwitchPane;
import io.github.yeamin21.gomail.ui.signings.ControllerLogin;
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
}
