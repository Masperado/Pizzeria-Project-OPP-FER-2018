package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Grade;
import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Transactional
    public List<Grade> findAllByPizza(Pizza pizza) {
        return gradeRepository.findAllByPizza(pizza);
    }

    @Transactional
    public Double getAverageGrade(Pizza pizza) {
        return gradeRepository.averageGrade(pizza.getId());
    }


    @Transactional
    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    @Transactional
    public Grade findAllByPizzaAndUser(Pizza pizza, User user) {
        return gradeRepository.findAllByPizzaAndUser(pizza, user);
    }

    @Transactional
    public void deleteAllByUser(User user) {
        gradeRepository.deleteAllByUser(user);
    }


    @Transactional
    public List<Grade> findAllByOrder(Order o) {
        return gradeRepository.findAllByOrder(o);
    }
}

