package hr.fer.opp.pizzeriasystem.models.enums;


import hr.fer.opp.pizzeriasystem.models.Order;

import java.io.Serializable;

public enum OrderStatus implements Serializable{
    ORDERED("ORDERED"),
    ACCEPTED("ACCEPTED"),
    DELIVERING("DELIVERING"),
    PAID("PAID"),
    NOT_PAID("NOT_PAID");

    String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static boolean isPast(Order order) {
        OrderStatus orderStatus = order.getStatus();
        if (orderStatus.equals(OrderStatus.NOT_PAID) || orderStatus.equals(OrderStatus.PAID)) {
            return true;
        }
        return false;
    }
}