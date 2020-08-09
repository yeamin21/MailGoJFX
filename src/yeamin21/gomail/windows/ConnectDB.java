package yeamin21.gomail.windows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection connect(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            Connection con=DriverManager.getConnection(
             "jdbc:mysql://localhost:3306/MailGO","root","root");
            System.out.println("Connected");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
