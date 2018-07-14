package hr.fer.opp.pizzeriasystem.repository;

import hr.fer.opp.pizzeriasystem.models.Grade;
import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>{

    List<Grade> findAllByPizza(Pizza pizza);

    @Query(value = "SELECT AVG(grade) FROM grade WHERE pizza_id = ?1", nativeQuery = true)
    Double averageGrade(Integer id);

    Grade findAllByPizzaAndUser(Pizza pizza, User user);

    void deleteAllByUser(User user);

    List<Grade> findAllByOrder(Order order);
}
