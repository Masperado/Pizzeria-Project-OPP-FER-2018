package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.PizzaOrder;
import hr.fer.opp.pizzeriasystem.models.Report;
import hr.fer.opp.pizzeriasystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PizzaOrderRepository pizzaOrderRepository;

    @Transactional
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Transactional
    public Report findOne(Integer id) {
        return reportRepository.findOne(id);
    }

    @Transactional
    public void save(Report report) {
        reportRepository.save(report);
    }

    @Transactional
    public Integer getNumberOfPizzasSold(Date beginDate, Date endDate) {
        List<Order> orders = orderRepository.getOrdersByDateSpan(beginDate, endDate);
        Integer sum = 0;

        for (int i = 0, size = orders.size(); i < size; i++) {
            List<PizzaOrder> pizzas = pizzaOrderRepository.findByOrderId(orders.get(i).getId());
            for (PizzaOrder po : pizzas) {
                sum += po.getNumberOfPizzas();
            }
        }

        return sum;
    }

    @Transactional
    public Double getMoneyEarned(Date beginDate, Date endDate) {
        Double moneyEarned = orderRepository.getTotalMoneyEarned(beginDate, endDate);

        if (moneyEarned == null) {
            moneyEarned = 0.0;
        }

        return moneyEarned;
    }

    @Transactional
    public Integer getNumberOfOrders(Date beginDate, Date endDate) {
        Integer numberOfOrders = orderRepository.getNumberOfOrders(beginDate, endDate);
        if (numberOfOrders == null) {
            numberOfOrders = 0;
        }

        return numberOfOrders;
    }

    @Transactional
    public Integer getDistinctUsersOrdered(Date beginDate, Date endDate) {
        List<User> distinctUsersOrdered = orderRepository.getDistinctUsersOrdered(beginDate, endDate);
        if (distinctUsersOrdered == null) {
            return 0;
        }

        return distinctUsersOrdered.size();
    }

    @Transactional
    public Double getMaxOrder(Date beginDate, Date endDate) {
        return orderRepository.getMaxOrderPrice(beginDate, endDate);
    }

    @Transactional
    public Double getMinOrder(Date beginDate, Date endDate) {
        return orderRepository.getMinOrderPrice(beginDate, endDate);
    }
}