/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeamin21.gomail.base.mail;

/**
 *
 * @author yeami
 */
public interface DatabaseOperations {
    void setUser(String user);
    String getUser();
    void Create();
    void Read();
    void Update();
    void Delete();
}
