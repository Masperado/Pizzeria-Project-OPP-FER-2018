package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.PizzaOrder;
import hr.fer.opp.pizzeriasystem.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PizzaOrderService {

    @Autowired
    private PizzaOrderRepository pizzaOrderRepository;

    @Transactional
    public void save(PizzaOrder pizzaOrder) {
        pizzaOrderRepository.save(pizzaOrder);
    }

    public List<PizzaOrder> findByOrderId(Order order) {
        return pizzaOrderRepository.findByOrderId(order.getId());
    }
}
