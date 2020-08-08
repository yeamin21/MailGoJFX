package yeamin21.gomail.windows.signings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import yeamin21.gomail.base.user.UserDatabaseOperations;
import yeamin21.gomail.windows.SwitchPane;

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
       us.setEmail(txtEmail.getText().trim());
       us.setFirstName(txtFName.getText().trim());
       us.setLastName(txtLName.getText().trim());
       us.setPassword(passPassword.getText().trim());
       us.Create();

       new SwitchPane("Login.fxml",paneSignUp).doSwitch();

    }


}
