package hr.fer.opp.pizzeriasystem.models.complexModels;

import hr.fer.opp.pizzeriasystem.models.Order;

public class OrderAndMessage {

    private Order order;

    private String message;

    private Boolean orderOK;

    public Boolean getOrderOK() {
        return orderOK;
    }

    public void setOrderOK(Boolean orderOK) {
        this.orderOK = orderOK;
    }


    public Order getOrder() {
        return order;
    }

    public String getMessage() {
        return message;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderAndMessage{" +
                "order=" + order +
                ", message='" + message + '\'' +
                '}';
    }
}
