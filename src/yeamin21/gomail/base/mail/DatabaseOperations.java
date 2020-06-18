/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeamin21.gomail.base.mail;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import yeamin21.gomail.windows.mail.ControllerInbox;

/**
 *
 * @author yeami
 */
public interface DatabaseOperations {
    public void setUser(String user);
    public String getUser();
    public void Create();
    public void Read();
    public void Update();
    public void Delete();
}
