package hr.fer.opp.pizzeriasystem.models.enums;

import hr.fer.opp.pizzeriasystem.models.Pizza;

import java.io.Serializable;

public enum PizzaStatus implements Serializable {
    IN_OFFER("IN_OFFER"),
    NOT_OFFERED("NOT_OFFERED");

    String pizzaStatus;

    PizzaStatus(String pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public String getPizzaStatus() {
        return pizzaStatus;
    }

    public static boolean isOffered(Pizza pizza) {
        PizzaStatus pizzaStatus = pizza.getPizzaStatus();
        if (pizzaStatus.equals(IN_OFFER)) {
            return true;
        }
        return false;
    }
}