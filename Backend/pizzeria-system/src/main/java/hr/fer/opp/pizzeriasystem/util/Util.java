package hr.fer.opp.pizzeriasystem.util;

import hr.fer.opp.pizzeriasystem.models.Pizza;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.complexModels.PizzaIdQuantity;

import java.util.Collections;
import java.util.List;

public class Util {

    public static Double calculateTotalPrice(List<Pizza> pizzaList, List<PizzaIdQuantity> pizzas) {
        double sum = 0;
        for (int i = 0, size = pizzas.size(); i < size; i++) {
            sum += pizzas.get(i).getQuantity() * pizzaList.get(i).getPrice();
        }

        return sum;
    }

    public static User selectRandomEmployee(List<User> all) {
        Collections.shuffle(all);
        return all.get(0);
    }
}
