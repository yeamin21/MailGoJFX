package yeamin21.gomail.windows.mail;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.base.mail.DraftMails;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.base.mail.SendMail;
import yeamin21.gomail.base.user.Users;
import yeamin21.gomail.windows.signings.ControllerLogin;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerComposePanel implements Initializable {
    @FXML
    Button buttonSendMail;
    @FXML
    TextField textfieldSendTo,textfieldSubject;
    @FXML
    TextArea textareaMailBody;
    @FXML
    Mails mails;
    @FXML
    Pane ComposePanel;
    Stage s;


    @FXML
    void Clear()
    {
       textfieldSendTo.setText(null);
        textfieldSubject.setText(null);
        textareaMailBody.setText(null);
    }
    @FXML
    void sendMail()
    {
        SendMail sendMail=new SendMail();
        sendMail.setSender(ControllerLogin.userEmail.toLowerCase());
        sendMail.setReceiver(textfieldSendTo.getText().toLowerCase().trim());
        sendMail.setSubject(textfieldSubject.getText().trim());
        sendMail.setBody(textareaMailBody.getText().trim());
        sendMail.Create();
        Clear();
    }
    //TODO optimize this segment
    //need to make changes in base code for mail receiver
    @FXML
    public void setMail(Mails x) //from controllerMailbox
    {
        textfieldSubject.setText(x.getSubject());
        textfieldSendTo.setText(x.getReceiver());

    }
    @FXML
    public void setReceiver(Users x) //from controllerContact
    {
        textfieldSendTo.setText(x.getEmail());
    }
    @FXML
    public void draftMails()
    {


    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Draft mails
        Platform.runLater(()->{
            s=(Stage) ComposePanel.getScene().getWindow();
            s.setOnCloseRequest(event ->
            {
                if(!textfieldSendTo.getText().isEmpty() || !textareaMailBody.getText().isEmpty() || !textfieldSubject.getText().isEmpty())
                {
                    Mails draftMails=new DraftMails();
                    draftMails.setBody(textareaMailBody.getText());
                    draftMails.setReceiver(textfieldSendTo.getText());
                    draftMails.setSubject(textfieldSubject.getText());
                    draftMails.setSender(ControllerLogin.userEmail);
                    ((DatabaseOperations)draftMails).Create();
                }

            });

                }
        );

    }
}
