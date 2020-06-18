package yeamin21.gomail.base.mail;

public class MailCategory implements DatabaseOperations {
    private String categoryID,categoryName,loggedInUser;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
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
        String INSERT;
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
