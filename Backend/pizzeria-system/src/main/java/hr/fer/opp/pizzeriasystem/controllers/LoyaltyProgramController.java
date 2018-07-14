package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.LoyaltyProgram;
import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.LoyaltyProgramService;
import hr.fer.opp.pizzeriasystem.services.SecurityService;
import hr.fer.opp.pizzeriasystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoyaltyProgramController {

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    //sve dobiva loyalty programe
    @PostMapping("/admin/loyalty")
    public ResponseEntity<List<LoyaltyProgram>> getLoyaltyPrograms(@RequestBody Token token) {

        User user = securityService.loggedIn(token.getToken());

        if (user==null  || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(loyaltyProgramService.findAll(),HttpStatus.OK);
    }

    //po id-u loyaltyja daje program
    @PostMapping("/admin/loyalty/{id}")
    public ResponseEntity<LoyaltyProgram> getLoyaltyProgram(@RequestBody Token token, @PathVariable Integer id) {
        User user = securityService.loggedIn(token.getToken());

        if (user==null  || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.findOne(id);
        if (loyaltyProgram == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(loyaltyProgram, HttpStatus.OK);
        }
    }

    //mijenja program vjernosti i stavlja novi broj pizza do besplatne jedne
    @PostMapping("/admin/loyaltyChange")
    public ResponseEntity<LoyaltyProgram> editLoyaltyProgram(@RequestBody LoyaltyProgram loyaltyProgram) {
        User user = securityService.loggedIn(loyaltyProgram.getToken());

        if (user==null  || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        int newTotal = loyaltyProgram.getTotalPizzasToGetFreeOne();
        LoyaltyProgram.TOTAL_PIZZAS_TO_GET_FREE = newTotal;
        List<User> users = userService.findAllNotFiltered();

        for (int i = 0, size = users.size(); i < size; i++) {
            LoyaltyProgram program = loyaltyProgramService.findByUser(users.get(i));
            if (program != null) {
                program.setTotalPizzasToGetFreeOne(newTotal);
                loyaltyProgramService.save(program);
            }
        }
        return new ResponseEntity<>(loyaltyProgram,HttpStatus.OK);
    }

    //daje program vjernosti po id-u usera
    @PostMapping("/admin/user/{id}/loyalty")
    public ResponseEntity<LoyaltyProgram> getLoyaltyProgramByUser(@RequestBody Token token, @PathVariable Integer id) {
        User admin = securityService.loggedIn(token.getToken());

        if (admin==null  || admin.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LoyaltyProgram loyaltyProgram = loyaltyProgramService.findByUser(user);

        if (loyaltyProgram == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(loyaltyProgram, HttpStatus.OK);
        }
    }
}