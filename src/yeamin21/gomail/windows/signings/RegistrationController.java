package yeamin21.gomail.windows.signings;
import yeamin21.gomail.windows.SwitchPane;
import yeamin21.gomail.base.user.UserDatabaseOperations;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

       new SwitchPane("resources\\Login.fxml",paneSignUp).doSwitch();

    }


}
