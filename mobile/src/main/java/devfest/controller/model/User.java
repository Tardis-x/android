package devfest.controller.model;

/**
 * Created by Brusd on 7/16/2016.
 */

public class User {

    public String userName;
    public String email;
    public String emailWithDots;
    public String imageURL;


    public User (){

    }
    public User(String userName, String imageURL){
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
    public String getEmailWithDots() {
        return emailWithDots;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setEmailWithoutDots(String email) {
        this.emailWithDots = email;
        String temp = email.replaceAll("\\.", ",");

        this.email = temp;
    }
}
