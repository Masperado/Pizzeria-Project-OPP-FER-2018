package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.enums.OrderStatus;
import hr.fer.opp.pizzeriasystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public List<Order> findAllById(Integer id) {
        return orderRepository.findAllById(id);
    }

    @Transactional
    public List<Order> findPastUserOrders(User user) {
        List<Order> orders = orderRepository.findAllByUser(user);
        if (orders == null) orders = new ArrayList<>();

        List<Order> ordersToReturn = new ArrayList<>();
        for (Order o : orders) {
            OrderStatus os = o.getStatus();
            if (os.equals(OrderStatus.PAID)|| os.equals(OrderStatus.NOT_PAID))
                ordersToReturn.add(o);
        }
        return ordersToReturn;
    }

    @Transactional
    public List<Order> findPastEmployeeOrders(User employee) {
        List<Order> orders = orderRepository.findAllByEmployee(employee);
        if (orders == null) orders = new ArrayList<>();

        return orders.stream().filter(OrderStatus::isPast).collect(Collectors.toList());
    }

    @Transactional
    public List<Order> findCurrentEmployeeOrders(User employee) {
        List<Order> orders = orderRepository.findAllByEmployee(employee);
        if (orders == null) orders = new ArrayList<>();

        return orders.stream().filter(e -> !OrderStatus.isPast(e)).collect(Collectors.toList());
    }

    @Transactional
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Transactional
    public List<Order> findCurrentUserOrders(User user) {
        List<Order> orders = orderRepository.findAllByUser(user);
        if (orders == null) orders = new ArrayList<>();
        List<Order> ordersToReturn = new ArrayList<>();
        for (Order o : orders) {
            OrderStatus os = o.getStatus();
            if (os.equals(OrderStatus.ACCEPTED) || os.equals(OrderStatus.DELIVERING) || os.equals(OrderStatus.ORDERED))
                ordersToReturn.add(o);
        }
        return ordersToReturn;
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public Order findOne(Integer id) {
        return orderRepository.findOne(id);
    }

    @Transactional
    public List<Order> findAllByEmployee(User employee) {
        return orderRepository.findAllByEmployee(employee);
    }



    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public List<Order> findAllPastOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders == null) orders = new ArrayList<>();

        return orders.stream().filter(OrderStatus::isPast).collect(Collectors.toList());
    }

    @Transactional
    public List<Order> findAllCurrentOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders == null) orders = new ArrayList<>();

        return orders.stream().filter(e -> !OrderStatus.isPast(e)).collect(Collectors.toList());
    }


}
