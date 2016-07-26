package devfest.controller.model;

/**
 * Created by Brusd on 7/16/2016.
 */

public class User {

    public String userName;
    public String email;
    public String imageURL;
    public String userLevel;


    public User() {

    }

    public User(String userName, String imageURL) {
        this.userName = userName;
        this.imageURL = imageURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", userLevel='" + userLevel + '\'' +
                '}';
    }
}
