package hr.fer.opp.pizzeriasystem.models.complexModels;

import hr.fer.opp.pizzeriasystem.models.Pizza;

public class PizzaAndToken {

    private Pizza pizza;

    private String token;

    public String getToken() {
        return token;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
