package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.complexModels.Message;
import hr.fer.opp.pizzeriasystem.models.enums.OrderStatus;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.EmployeeService;
import hr.fer.opp.pizzeriasystem.services.OrderService;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class EmployeeController {

    public static boolean ORDERS_OPEN = true;
    public static boolean STORE_OPEN = true;


    @Autowired
    private OrderService orderService;

    @Autowired
    private SecurityService securityService;


    @GetMapping("/pizzeriaStatus")
    public ResponseEntity<Message> getPizzeriaStatus() {
        if (ORDERS_OPEN && STORE_OPEN) {
            return new ResponseEntity<>(new Message("Naručivanje je otvoreno!"), HttpStatus.OK);
        }else if (!STORE_OPEN){
            return new ResponseEntity<>(new Message("Pizzeria je zatvorena!"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Message("Naručivanje je blokirano!"), HttpStatus.OK);

        }
    }

    //samo blokira narudzbu, moram jos poslati poruku korisniku kad narucuje da mu bude stavljeno da su narudzbe blokirane
    @PostMapping("/employee/block")
    public ResponseEntity<Message> blockOrdering(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ORDERS_OPEN = false;
        return new ResponseEntity<>(new Message("Blocked"), HttpStatus.OK);
    }

    //otvara narudzbe
    @PostMapping("/employee/open")
    public ResponseEntity<Message> openOrdering(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ORDERS_OPEN = true;

        return new ResponseEntity<>(new Message("Open"), HttpStatus.OK);
    }

    //prihvaca narudzbu, neka bude gumb uz narudzbu za prihvacanje
    @PostMapping("/employee/accept/{id}")
    public ResponseEntity<Message> acceptOrder(@RequestBody Token token, @PathVariable Integer id) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        Order oldOrder = orderService.findOne(id);

        oldOrder.setStatus(OrderStatus.ACCEPTED);
        orderService.save(oldOrder);

        return new ResponseEntity<>(new Message("Accepted"), HttpStatus.OK);
    }

    //kad se pizza napravi neka to bude jos tu, pa se klikne da je dostavljeno
    @PostMapping("/employee/deliver/{id}")
    public ResponseEntity<Message> deliverOrder(@RequestBody Token token, @PathVariable Integer id) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        Order oldOrder = orderService.findOne(id);

        oldOrder.setStatus(OrderStatus.DELIVERING);
        orderService.save(oldOrder);

        return new ResponseEntity<>(new Message("Delivered"), HttpStatus.OK);
    }

    //nije placeno
    @PostMapping("/employee/notPaid/{id}")
    public ResponseEntity<Message> notPaidOrder(@RequestBody Token token, @PathVariable Integer id) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        Order oldOrder = orderService.findOne(id);

        oldOrder.setStatus(OrderStatus.NOT_PAID);
        orderService.save(oldOrder);

        Message message = new Message("Not paid");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //placeno, isto su to gumbovi
    @PostMapping("/employee/paid/{id}")
    public ResponseEntity<Message> paidOrder(@RequestBody Token token, @PathVariable Integer id) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        Order oldOrder = orderService.findOne(id);

        oldOrder.setStatus(OrderStatus.PAID);
        orderService.save(oldOrder);

        Message message = new Message("Paid");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    //otvaranje pizzerie
    @Scheduled(cron = "0 0 16 * * ?")
    public void openStore() {
        STORE_OPEN = true;
    }

    //zatvaranje pizzerie
    @Scheduled(cron = "0 0 0 * * ?")
    public void closeStore() {
        STORE_OPEN = false;
    }
}
