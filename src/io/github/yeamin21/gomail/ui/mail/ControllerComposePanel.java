package io.github.yeamin21.gomail.ui.mail;

import io.github.yeamin21.gomail.mail.DatabaseOperations;
import io.github.yeamin21.gomail.mail.SendMail;
import io.github.yeamin21.gomail.ui.signings.ControllerLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ControllerComposePanel {
    @FXML
    Button buttonSendMail;
    @FXML
    TextField textfieldSendTo,textfieldSubject;
    @FXML
    TextArea textareaMailBody;

    @FXML
    void sendMail()
    {
        SendMail sendMail=new SendMail();
        sendMail.setSender(ControllerLogin.userEmail.toLowerCase());
        sendMail.setReceiver(textfieldSendTo.getText().toLowerCase().trim());
        sendMail.setSubject(textfieldSubject.getText().toString().trim());
        sendMail.setBody(textareaMailBody.getText().toString().trim());
        sendMail.Create();

    }
}
