/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.yeamin21.gomail.user;

import io.github.yeamin21.gomail.mail.DatabaseOperations;

/**
 *
 * @author yeami
 */
public class Contact implements DatabaseOperations {
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public void Create() {
        String sqlAddToContact="INSERT INTO CONTACTS(ID,)";
    }

    @Override
    public void Read() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }
}
