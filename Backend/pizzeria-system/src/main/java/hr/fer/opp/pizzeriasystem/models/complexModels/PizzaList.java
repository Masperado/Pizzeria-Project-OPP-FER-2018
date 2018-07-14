package hr.fer.opp.pizzeriasystem.models.complexModels;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.models.enums.OrderStatus;
import hr.fer.opp.pizzeriasystem.models.enums.PizzaStatus;

import java.util.Date;
import java.util.List;

public class PizzaList {

    private List<PizzaIdQuantity> pizzas;

    private String token;

    private Integer orderId;

    private Date orderDate;

    private OrderStatus orderStatus;

    private Double price;

    private Boolean hasGrade;

    private String userName;

    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHasGrade(Boolean hasGrade) {
        this.hasGrade = hasGrade;
    }

    public Boolean getHasGrade() {
        return hasGrade;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PizzaIdQuantity> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaIdQuantity> pizzas) {
        this.pizzas = pizzas;
    }

    public Double getPrice() {
        return price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
