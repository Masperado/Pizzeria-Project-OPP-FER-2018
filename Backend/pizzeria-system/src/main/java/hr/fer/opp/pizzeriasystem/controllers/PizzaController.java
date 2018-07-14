package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.complexModels.Message;
import hr.fer.opp.pizzeriasystem.models.complexModels.PizzaAndToken;
import hr.fer.opp.pizzeriasystem.models.enums.PizzaStatus;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.GradeService;
import hr.fer.opp.pizzeriasystem.services.PizzaService;
import hr.fer.opp.pizzeriasystem.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/offer/pizza")
    public ResponseEntity<List<Pizza>> getOfferedPizzas() {
        List<Pizza> pizzas = pizzaService.findAllByPizzaStatus(PizzaStatus.IN_OFFER);
        assignPizzaGrades(pizzas);
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    @PostMapping("/admin/pizzas")
    public ResponseEntity<List<Pizza>> getPizzas(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        List<Pizza> pizzas = pizzaService.findAll();
        assignPizzaGrades(pizzas);
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    //pizza po id-u
    @GetMapping("/pizza/{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable Integer id) {
        Pizza pizza = pizzaService.findOne(id);
        pizza.setAverageGrade(gradeService.getAverageGrade(pizza));
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }


    @PostMapping(value = "/admin/pizza")
    public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizzaForm) {
        User user = securityService.loggedIn(pizzaForm.getToken());

        if (user == null || user.getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        pizzaService.save(pizzaForm);
        return new ResponseEntity<>(pizzaForm, HttpStatus.OK);
    }


    //izbrise pizzu
    @PostMapping("/admin/pizza/delete/{id}")
    public ResponseEntity<Integer> removeFromOffers(@RequestBody Token token, @PathVariable Integer id) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        if (id == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }

        Pizza pizza = pizzaService.findOne(id);

        pizza.setPizzaStatus(PizzaStatus.NOT_OFFERED);
        pizzaService.save(pizza);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/admin/pizza/addToOffers/{id}")
    public ResponseEntity<Integer> addToOffers(@RequestBody Token pizzaAndToken, @PathVariable Integer id) {
        User user = securityService.loggedIn(pizzaAndToken.getToken());

        if (user == null || user.getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        Pizza newPizza = pizzaService.findOne(id);
        newPizza.setPizzaStatus(PizzaStatus.IN_OFFER);
        pizzaService.save(newPizza);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //TODO
    @PostMapping("/admin/pizza/edit/{id}")
    public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizzaT, @PathVariable Integer id) {
        User user = securityService.loggedIn(pizzaT.getToken());

        if (user == null || user.getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        Pizza pizza = pizzaT;
        Pizza newPizza = pizzaService.findOne(id);

        newPizza.setId(id);
        newPizza.setPizzaStatus(pizza.getPizzaStatus());
        newPizza.setDescription(pizza.getDescription());
        newPizza.setName(pizza.getName());
        newPizza.setPictureUrl(pizza.getPictureUrl());
        newPizza.setPrice(pizza.getPrice());

        pizzaService.save(newPizza);

        return new ResponseEntity<Pizza>(newPizza, HttpStatus.OK);
    }


    private void assignPizzaGrades(List<Pizza> pizzas) {
        for (int i = 0, size = pizzas.size(); i < size; i++) {
            Pizza pizza = pizzas.get(i);
            pizza.setAverageGrade(gradeService.getAverageGrade(pizza));
        }
    }

}
