package yeamin21.gomail.base.mail;

import yeamin21.gomail.windows.ConnectDB;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class UserCreatedCategory implements DatabaseOperations{
    private String categoryName,loggedInUser;
    String userEmail;
    Connection con= ConnectDB.connect();
    ArrayList<UserCreatedCategory>userCreatedCategory=new ArrayList<>();

    public UserCreatedCategory() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    UserCreatedCategory(String userEmail){
        this.userEmail=userEmail;
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
        String SQL_ADDCATEGORY="Insert into category(category_name,belongs_to) values(?,?)";
        try  {
            PreparedStatement preparedStatement = con.prepareStatement(SQL_ADDCATEGORY);
            preparedStatement.setString(1,getCategoryName());
            preparedStatement.setString(2,getUser());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void Read() {
        String SQL_GETCATEGORY="SELECT * FROM CATEGORY WHERE BELONGS_TO=`"+userEmail+"`";
        try  {
            PreparedStatement preparedStatement=con.prepareStatement(SQL_GETCATEGORY);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next())
            {
                UserCreatedCategory aUserCreatedCategory=new UserCreatedCategory();
                aUserCreatedCategory.setCategoryName(rs.getString("category_name"));
                userCreatedCategory.add(aUserCreatedCategory);
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
}
