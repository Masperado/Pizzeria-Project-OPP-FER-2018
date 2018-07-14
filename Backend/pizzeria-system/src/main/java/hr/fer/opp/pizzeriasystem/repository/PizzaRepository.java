package hr.fer.opp.pizzeriasystem.repository;

import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.models.enums.PizzaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

    List<Pizza> findAllByName(String name);
    void deleteById(Integer id);
    List<Pizza> findAllByPizzaStatus(PizzaStatus pizzaStatus);
}
