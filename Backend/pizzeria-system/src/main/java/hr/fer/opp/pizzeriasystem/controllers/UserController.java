package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.LoyaltyProgram;
import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.complexModels.Message;
import hr.fer.opp.pizzeriasystem.models.complexModels.UserAndToken;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.*;
import hr.fer.opp.pizzeriasystem.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @Autowired
    private TokenService tokenService;

    //registracija
    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        userService.save(userForm);

        loyaltyProgramService.createLoyaltyProgram(userForm);

        return new ResponseEntity<>(userForm, HttpStatus.OK);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<Token> login(@RequestBody User userForm){
        if (userForm.getUsername()==null || userForm.getPassword()==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String userName = userForm.getUsername();

        List<User> users = userService.findAll();

        User user=userService.findByUsername(userName);
        if (user==null || !(user.getPassword().equals(userForm.getPassword()))){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Token token = tokenService.findByUser(user);
        if (token != null) {
            securityService.refreshToken(token);
            return new ResponseEntity<Token>(token, HttpStatus.OK);
        }

        token = new Token();
        securityService.login(user, token);


        return new ResponseEntity<>(token,HttpStatus.OK);
    }

    //userov profil
    @PostMapping("/user/profile")
    public ResponseEntity<User> editProfile(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/edit")
    public ResponseEntity<User> updateUser(@RequestBody UserAndToken userAndToken) {
        User user = securityService.loggedIn(userAndToken.getToken());

        if (user==null  || user.getRole()!= Role.USER){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User userNew = userAndToken.getUser();

        User oldUser = userService.findOne(user.getId());
        oldUser.setFirstName(userNew.getFirstName());
        oldUser.setLastName(userNew.getLastName());
        oldUser.setEmail(userNew.getEmail());
        oldUser.setPassword(userNew.getPassword());
        oldUser.setAddress(userNew.getAddress());
        oldUser.setPhoneNumber(userNew.getPhoneNumber());

        userService.save(oldUser);

        return new ResponseEntity<>(oldUser, HttpStatus.OK);
    }

    //userov loyalty
    @PostMapping("/user/loyaltyProgram")
    public ResponseEntity<LoyaltyProgram> getLoyaltyProgram(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.findByUser(user);
        return new ResponseEntity<>(loyaltyProgram,HttpStatus.OK);
    }

    @PostMapping("/userlogout")
    public ResponseEntity<Message> logout(@RequestBody Token token) {

        User user = securityService.loggedIn(token.getToken());

        if (user == null) {
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        securityService.logout(token.getToken());

        Message message = new Message();
        message.setMessage("Odlogirani!");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
