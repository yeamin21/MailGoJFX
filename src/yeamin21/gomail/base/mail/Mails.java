/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeamin21.gomail.base.mail;


import yeamin21.gomail.base.user.Users;

import java.util.Date;

/**
 *
 * @author yeami
 */
public class Mails {
   private  String sender;
   private String receiver;
   private  String subject;
   private String body;
   private Date date;
   private  int code;
   private MailCategory category;

    public Mails() {
    }

    public String getCategory() {
        return category.getCategoryName();
    }

    public void setCategory(MailCategory category) {
        this.category = category;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }




    
}
