package yeamin21.gomail.windows.mail;

import javafx.fxml.Initializable;
import yeamin21.gomail.base.mail.Mails;
import yeamin21.gomail.base.mail.SendMail;
import yeamin21.gomail.windows.signings.ControllerLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    void sendMail()
    {
        SendMail sendMail=new SendMail();
        sendMail.setSender(ControllerLogin.userEmail.toLowerCase());
        sendMail.setReceiver(textfieldSendTo.getText().toLowerCase().trim());
        sendMail.setSubject(textfieldSubject.getText().toString().trim());
        sendMail.setBody(textareaMailBody.getText().toString().trim());
        sendMail.Create();
    }
    @FXML
    public void setMail(Mails x)
    {
        textfieldSubject.setText("Re: " +x.getSubject());
        textfieldSendTo.setText(x.getSender());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
