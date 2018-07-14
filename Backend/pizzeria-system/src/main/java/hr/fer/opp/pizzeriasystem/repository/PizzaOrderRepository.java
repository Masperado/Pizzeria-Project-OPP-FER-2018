package hr.fer.opp.pizzeriasystem.repository;

import hr.fer.opp.pizzeriasystem.models.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Integer> {

    @Query(value = "SELECT * FROM pizza_order WHERE order_id = ?1", nativeQuery = true)
    List<PizzaOrder> findByOrderId(Integer id);
}
