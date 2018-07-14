package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.repository.TokenRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenService tokenService;

    @Scheduled(cron = "0 0 */1 ? * *")
    public void removeExpiringTokens() {
        Date now = new Date();
        List<Token> tokens = tokenService.findAll();
        for (Token token : tokens) {
            Date tokenDate = token.getExpirationDate();
            if (now.before(tokenDate)) {
                tokenService.removeByToken(token.getToken());
            }
        }
    }

    @Override
    public void login(User user, Token token) {

        Date dateAfterOneHour = new Date(System.currentTimeMillis() + 3600 * 1000);

        String tokenString = bCryptPasswordEncoder.encode(user.getUsername() + dateAfterOneHour.toString());
        token.setExpirationDate(dateAfterOneHour);
        token.setToken(tokenString);
        token.setUser(user);
        tokenService.save(token);
    }

    @Override
    public User loggedIn(String token) {
        Token tokenObject = tokenService.findByToken(token);

        if (tokenObject == null) {
            return null;
        }

        Date currentDate = new Date();

        if (currentDate.after(tokenObject.getExpirationDate())) {
            return null;
        }

        tokenObject.setExpirationDate(new Date(System.currentTimeMillis() + 3600 * 1000));
        tokenService.save(tokenObject);

        return tokenObject.getUser();
    }

    @Override
    public void logout(String token) {
        tokenService.removeByToken(token);
    }

    @Override
    public void refreshToken(Token token) {
        Date dateAfterOneHour = new Date(System.currentTimeMillis() + 3600 * 1000);
        token.setExpirationDate(dateAfterOneHour);
        tokenService.save(token);
    }


}
