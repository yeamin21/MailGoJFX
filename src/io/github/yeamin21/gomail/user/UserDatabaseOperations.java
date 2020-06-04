package io.github.yeamin21.gomail.user;

import io.github.yeamin21.gomail.mail.DatabaseOperations;
import io.github.yeamin21.gomail.ui.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseOperations extends Users implements DatabaseOperations {
    Connection connection= ConnectDB.connect();
    @Override
    public void Create() {
        String INSERTION="INSERT INTO `USER`(`email`,`first_name`,`last_name`,`password`) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(INSERTION);
            preparedStatement.setString(1,getEmail());
            preparedStatement.setString(2,getFirstName());
            preparedStatement.setString(3,getLastName());
            preparedStatement.setString(4,getPassword());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            System.out.println(throwables);
        }

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

    public String userLogin()
    {
        String LOGIN="SELECT `email` FROM `USER` where `email`='"+getEmail()+"' and `password`='"+getPassword()+"'";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(LOGIN);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getString("email");
            }

        } catch (SQLException throwables) {
            System.out.println(throwables);
        }
        return null;
    }
}
