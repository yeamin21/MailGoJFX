package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MailCategory implements DatabaseOperations {
    Connection con= ConnectDB.connect();
    private int categoryID;
    public ArrayList<MailCategory>mailCategories=new ArrayList<>();
    private String categoryName,loggedInUser;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public void setUser(String user) {
        this.loggedInUser=user;
    }

    @Override
    public String getUser() {
        return loggedInUser;
    }

    @Override
    public void Create() {
        String SQL_INSERT="Insert into category (category_name,belongs_to) values(?,?)";
        try {
            PreparedStatement statement=con.prepareStatement(SQL_INSERT);
            statement.setString(1,getCategoryName());
            statement.setString(2,loggedInUser);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void Read() {
        String SQL_READ="Select * from category where belongs_to='"+loggedInUser+"'";
        try {
            PreparedStatement statement = con.prepareStatement(SQL_READ);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                MailCategory mailCategory=new MailCategory();
                mailCategory.setCategoryID(resultSet.getInt("category_id"));
                mailCategory.setCategoryName(resultSet.getString("category_name"));
                mailCategory.setUser(resultSet.getString("belongs_to"));
                mailCategories.add(mailCategory);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }
    @Override
    public String toString()
    {
        return this.getCategoryName();
    }
}
