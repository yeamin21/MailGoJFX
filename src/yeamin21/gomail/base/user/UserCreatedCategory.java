package yeamin21.gomail.base.user;

import yeamin21.gomail.base.mail.DatabaseOperations;
import yeamin21.gomail.windows.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserCreatedCategory implements DatabaseOperations {
    private int categoryID;
    private String categoryName;
    private String cateGoryBelongsTo;
    ArrayList<UserCreatedCategory>userCreatedCategoryArrayListl=new ArrayList<>();

    Connection con=ConnectDB.connect();
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

    public String  getCateGoryBelongsTo() {
        return cateGoryBelongsTo;
    }

    public void setCateGoryBelongsTo(String  cateGoryBelongsTo) {
        this.cateGoryBelongsTo = cateGoryBelongsTo;
    }


    @Override
    public void setUser(String user) {

    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public void Create() {
        String CREATE="INSERT INTO CATEGORY(CATEGORY_NAME,BELONGS_TO)VALUES(?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(CREATE);
            statement.setString(1,getCategoryName());
            statement.setString(2,getCateGoryBelongsTo());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void Read() {
        String READ="SELECT * FROM CATEGORY WHERE BELONGS_TO="+getCateGoryBelongsTo();
        try{PreparedStatement statement=con.prepareStatement(READ);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                UserCreatedCategory userCreatedCategory=new UserCreatedCategory();
                userCreatedCategory.setCategoryID(resultSet.getInt(1));
                userCreatedCategory.setCategoryName(resultSet.getString(2));
                userCreatedCategory.setCateGoryBelongsTo(resultSet.getString(3));
                userCreatedCategoryArrayListl.add(userCreatedCategory);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }
}
