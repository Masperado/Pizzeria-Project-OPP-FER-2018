package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.models.complexModels.UserAndToken;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.*;
import hr.fer.opp.pizzeriasystem.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import hr.fer.opp.pizzeriasystem.models.*;


import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SecurityService securityService;


    //----------------------PIZZA CONTROLS------------------------



    //---------------------EMPLOYEE CONTROLS----------------------
    //ucitavanje stranice sa svim zaposlenicima
    @PostMapping("/admin/employees")
    public ResponseEntity<List<User>> getEmployees(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        return new ResponseEntity<List<User>>(employeeService.findAll(), HttpStatus.OK);
    }

//    //ucitavanje stranice s jednim zaposlenikom na kojeg se klikne iz prethodne metode
//    @PostMapping("/admin/employee/{id}")
//    public ResponseEntity<User> getEmployee(@PathVariable("id") Integer id, @RequestBody Token token) {
//        if (id == null) {
//            throw new IllegalArgumentException("Id not found!");
//        }
//
//        User user = securityService.loggedIn(token.getToken());
//
//        if (user==null || user.getRole()!= Role.ADMIN){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//
//        }
//
//        User wantedUser = employeeService.findOne(id);
//
//        if (wantedUser == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(wantedUser, HttpStatus.OK);
//    }

    //brisanje, moglo bi se u istoj stranici napraviti ko u prosloj metodi, samo gumb da se stavi, s pitanjem zelite li stvarno to napraviti
    @DeleteMapping("/admin/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id, @RequestBody Token token) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        User user = securityService.loggedIn(token.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return;

        }

        User employee = employeeService.findOne(id);

        if (employee == null) return;

        gradeService.deleteAllByUser(employee);
        loyaltyProgramService.deleteByUser(employee);
        employeeService.setOrdersOfEmployeeToNull(employee);
        employeeService.removeEmployee(employee);
    }

    //dodavanje zaposlenika, da bude neka forma na stranici
    @PostMapping("/admin/employee")
    public ResponseEntity<User> addEmployee(@RequestBody UserAndToken userAndToken, BindingResult bindingResult) {
        if (userAndToken.getToken() == null || userAndToken.getUser() == null) {
            throw new IllegalArgumentException("User or token can not be null!");
        }
        User user = securityService.loggedIn(userAndToken.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User userForm = userAndToken.getUser();

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        employeeService.save(userForm);
        loyaltyProgramService.createLoyaltyProgram(userForm);

        return new ResponseEntity<>(userForm, HttpStatus.OK);
    }

    //na stranici za jednog zaposlenika, izmjenjivi text fieldovi i te gluposti
    @PostMapping("/admin/employee/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable Integer id, @RequestBody UserAndToken userAndToken) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null value");
        }
        if (userAndToken == null || userAndToken.getToken() == null || userAndToken.getUser() == null) {
            throw new IllegalArgumentException("User or token can not be null!");
        }

        User loggedUser = securityService.loggedIn(userAndToken.getToken());

        if (loggedUser==null || loggedUser.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = userAndToken.getUser();

        User oldUser = employeeService.findOne(id);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setRole(user.getRole() == null ? Role.EMPLOYEE : user.getRole());
        oldUser.setGender(user.getGender());
        oldUser.setAddress(user.getAddress());

        final String password = user.getPassword();

        if (password != null) {
            oldUser.setPassword(passwordEncoder.encode(password));
        }

        employeeService.updateEmployee(oldUser);
        oldUser.setPassword(passwordEncoder.encode(oldUser.getPassword()));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    //----------------------- USER CONTROLS -------------------------

    //isto ko za employee samo za usera
    @PostMapping("/admin/users")
    public ResponseEntity<List<User>> getUsers(@RequestBody Token token) {

        User user = securityService.loggedIn(token.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }

//    @PostMapping("/admin/user/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") Integer id, @RequestBody Token token) {
//        if (id == null) {
//            throw new IllegalArgumentException("Id not found!");
//        }
//
//        User user = securityService.loggedIn(token.getToken());
//
//        if (user==null || user.getRole()!= Role.ADMIN){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        User wantedUser = userService.findOne(id);
//
//        if (wantedUser == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(wantedUser, HttpStatus.OK);
//    }

    @DeleteMapping("/admin/user/{id}")
    public void deleteUser(@PathVariable("id") Integer id, @RequestBody Token token) {
        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        User user = securityService.loggedIn(token.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return;
        }

        User customer = userService.findOne(id);

        if (customer == null) return;

        gradeService.deleteAllByUser(customer);
        loyaltyProgramService.deleteByUser(customer);
        userService.setOrdersOfUserToNull(customer);
        userService.removeUser(id);
    }

    @PostMapping("/admin/user")
    public ResponseEntity<User> addUser(@RequestBody UserAndToken userAndToken, BindingResult bindingResult) {
        if (userAndToken == null || userAndToken.getToken() == null || userAndToken.getUser() == null) {
            throw new IllegalArgumentException("User or token can not be null!");
        }

        User user = securityService.loggedIn(userAndToken.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        User userForm = userAndToken.getUser();
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        userService.save(userForm);
        loyaltyProgramService.createLoyaltyProgram(userForm);

        return new ResponseEntity<>(userForm, HttpStatus.OK);
    }

    @PostMapping("/admin/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserAndToken userAndToken, @PathVariable Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null value");
        }

        if (userAndToken == null || userAndToken.getUser() == null || userAndToken.getToken() == null) {
            throw new IllegalArgumentException("User or token can not be null!");
        }

        User loggedUser = securityService.loggedIn(userAndToken.getToken());

        if (loggedUser == null || loggedUser.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User user = userAndToken.getUser();

        User oldUser = userService.findOne(id);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setRole(user.getRole() == null ? Role.USER : user.getRole());
        oldUser.setGender(user.getGender());
        oldUser.setAddress(user.getAddress());


        userService.updateUser(oldUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
