package io.github.yeamin21.gomail.ui.signings;
import io.github.yeamin21.gomail.mail.DatabaseOperations;
import io.github.yeamin21.gomail.ui.SwitchPane;
import io.github.yeamin21.gomail.user.UserDatabaseOperations;
import io.github.yeamin21.gomail.user.Users;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class RegistrationController {
    @FXML
    TextField txtEmail, txtFName, txtLName;
    @FXML
    PasswordField passPassword;
    @FXML
    Button btnSignUp;
    @FXML
    AnchorPane paneSignUp;

    @FXML
    void actionSignUp()
    {
       UserDatabaseOperations us=new UserDatabaseOperations();
       us.setEmail(txtEmail.getText().toString().trim());
       us.setFirstName(txtFName.getText().toString().trim());
       us.setLastName(txtLName.getText().toString().trim());
       us.setPassword(passPassword.getText().toString().trim());
       us.Create();

       new SwitchPane("signings\\Login.fxml",paneSignUp).doSwitch();

    }


}
