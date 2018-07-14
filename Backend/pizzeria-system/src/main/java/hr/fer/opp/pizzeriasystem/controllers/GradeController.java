package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.Grade;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.complexModels.GradeAndPizza;
import hr.fer.opp.pizzeriasystem.models.complexModels.GradeModel;
import hr.fer.opp.pizzeriasystem.models.complexModels.Message;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/user/gradePizza")
    public ResponseEntity<Message> gradePizza(@RequestBody GradeModel gradeModel) {

        User user = securityService.loggedIn(gradeModel.getToken());

        if (user==null  || user.getRole()!= Role.USER){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (gradeModel.getOrderId() == null || gradeModel.getGrades() == null) {
            throw new IllegalArgumentException("All parameters in grade must not be null!");
        }
        List<GradeAndPizza> grades = gradeModel.getGrades();
        for (GradeAndPizza grade : grades) {
            Integer mark = grade.getGrade();

            if (mark < 1 || mark > 5) {
                throw new IllegalArgumentException("Illegal grade!");
            }
        }

        for (GradeAndPizza g : grades) {
            Grade grade = new Grade();
            grade.setUser(user);
            grade.setPizza(pizzaService.findOne(g.getPizzaId()));
            grade.setGrade(g.getGrade());
            grade.setOrder(orderService.findOne(gradeModel.getOrderId()));

            Grade oldGrade = gradeService.findAllByPizzaAndUser(grade.getPizza(), grade.getUser());

            if (oldGrade != null && oldGrade.getPizza().equals(grade.getPizza()) && oldGrade.getUser().equals(grade.getUser())
                    && oldGrade.getOrder().equals(grade.getOrder())) {
                oldGrade.setGrade(grade.getGrade());
                gradeService.save(oldGrade);
            }else {
                gradeService.save(grade);
            }

        }

        return new ResponseEntity<>(new Message("Ocjene su uspješno dodane!"), HttpStatus.OK);
    }
}
