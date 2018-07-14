package hr.fer.opp.pizzeriasystem.repository;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllById(Integer id);
    List<Order> findAllByUser(User user);
    List<Order> findAllByEmployee(User employee);

    @Query(value = "SELECT sum(o.price) FROM Order o WHERE o.dateCreatedAt BETWEEN ?1 AND ?2")
    Double getTotalMoneyEarned(Date beginDate, Date endDate);

    @Query(value = "SELECT COUNT(o.id) FROM Order o WHERE o.dateCreatedAt BETWEEN ?1 AND ?2")
    Integer getNumberOfOrders(Date beginDate, Date endDate);

    @Query(value = "SELECT DISTINCT(o.user) FROM Order o WHERE o.dateCreatedAt BETWEEN ?1 AND ?2")
    List<User> getDistinctUsersOrdered(Date beginDate, Date endDate);

    @Query(value = "SELECT * FROM orders WHERE date_created_at BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Order> getOrdersByDateSpan(Date beginDate, Date endDate);

    @Query(value = "SELECT max(o.price) FROM Order o WHERE o.dateCreatedAt BETWEEN ?1 AND ?2")
    Double getMaxOrderPrice(Date beginDate, Date endDate);

    @Query(value = "SELECT min(o.price) FROM Order o WHERE o.dateCreatedAt BETWEEN ?1 AND ?2")
    Double getMinOrderPrice(Date beginDate, Date endDate);
}
