package hr.fer.opp.pizzeriasystem.services;


import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;

public interface SecurityService {
//    String findLoggedInUsername();
//
//    void autologin(String username, String password);

    public void login(User user, Token token);

    public User loggedIn(String token);

    public void logout(String token);

    void refreshToken(Token token);
}
