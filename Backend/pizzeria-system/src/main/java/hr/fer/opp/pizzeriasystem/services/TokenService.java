package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    public Token findByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    @Transactional
    public void save(Token token) {
        tokenRepository.save(token);
    }

    @Transactional
    public void removeByToken(String token) {
        tokenRepository.removeByToken(token);
    }

    @Transactional
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Transactional
    public List<Token> findAll() {
        return tokenRepository.findAll();
    }
}
