package yeamin21.gomail.windows.mail;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    VBox vbox;



    @FXML
    public void initialize(){
        labelUser.setText(ControllerLogin.userEmail);
        new SwitchPane("mail\\Inbox.fxml",panelMailboxBody).doSwitch();


        for(int i=0;i<5;i++)
        {

            Button l=new Button("ABBIIIFS");
            AnchorPane anchorPane=new AnchorPane();
            anchorPane.getChildren().addAll(l);
            vbox.getChildren().add(anchorPane);
            System.out.println(vbox.getChildren());
        }
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

