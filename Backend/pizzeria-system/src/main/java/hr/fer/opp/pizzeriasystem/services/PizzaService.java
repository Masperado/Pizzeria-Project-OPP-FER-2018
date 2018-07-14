package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.models.enums.PizzaStatus;
import hr.fer.opp.pizzeriasystem.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional
    public void save(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @Transactional
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Transactional
    public Pizza findOne(Integer id) {
        return pizzaRepository.findOne(id);
    }

    @Transactional
    public List<Pizza> findAllByName(String name) {
        return pizzaRepository.findAllByName(name);
    }

    @Transactional
    public void removeById(Integer id) {
        pizzaRepository.deleteById(id);
    }

    @Transactional
    public List<Pizza> findAllByPizzaStatus(PizzaStatus pizzaStatus) {
        return pizzaRepository.findAllByPizzaStatus(pizzaStatus);
    }

}
