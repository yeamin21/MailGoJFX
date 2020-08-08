package yeamin21.gomail.windows.signings;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import yeamin21.gomail.base.user.UserDatabaseOperations;
import yeamin21.gomail.windows.SwitchPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {

    UserDatabaseOperations us=new UserDatabaseOperations();
    public static String userEmail;
    @FXML
    Button buttonLogin;
    @FXML
    Pane panelLogin;
    @FXML
    TextField textfieldEmail;
    @FXML
    PasswordField passwordfieldPassword;
    @FXML
    Label labelCreateAccount;
    @FXML
      AnchorPane panelLoginRegistration;


    @FXML
    void Login()
    {
        us.setEmail(textfieldEmail.getText().toString().trim());
        us.setPassword(passwordfieldPassword.getText().toString().trim());
        us.userLogin();
        userEmail=us.getEmail();
        new SwitchPane("MailBox.fxml",panelLoginRegistration).doSwitch();
    }

    @FXML
    void toSignUpPanel()
    {
      new SwitchPane("Registration.fxml",panelLoginRegistration).doSwitch();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textfieldEmail.setPromptText("enter email here");
        passwordfieldPassword.setPromptText("enter password here ");
    }
}
