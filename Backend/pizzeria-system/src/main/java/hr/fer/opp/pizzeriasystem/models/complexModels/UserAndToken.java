package hr.fer.opp.pizzeriasystem.models.complexModels;

import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;

public class UserAndToken {

    private User user;

    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getFirstName(){
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public String getGender() {
        return user.getGender();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getEmail() {
        return user.getEmail();
    }

}
