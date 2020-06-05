package yeamin21.gomail.windows.user;

import yeamin21.gomail.base.user.Contact;
import yeamin21.gomail.windows.signings.ControllerLogin;

public class ControllerContact {

    void addContact()
    {
        Contact contact=new Contact();
        contact.setUser(ControllerLogin.userEmail);
    }
}
