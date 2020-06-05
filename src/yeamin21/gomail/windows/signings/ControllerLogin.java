package yeamin21.gomail.windows.signings;

import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.base.user.UserDatabaseOperations;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ControllerLogin {

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
        this.userEmail=us.getEmail();
        new SwitchPane("mail\\MailBox.fxml",panelLoginRegistration).doSwitch();
    }

    @FXML
    void toSignUpPanel()
    {
      new SwitchPane("signings\\Registration.fxml",panelLoginRegistration).doSwitch();
    }
}