package io.github.yeamin21.gomail.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection connect(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {

            }
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/MailGO","root","root");
            System.out.println("Connected");
            return con;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

}
