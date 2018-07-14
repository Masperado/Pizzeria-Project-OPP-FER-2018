package hr.fer.opp.pizzeriasystem.models;

import javax.persistence.*;

@Entity
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_order_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "number_of_pizzas", nullable = false)
    private Integer numberOfPizzas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfPizzas() {
        return numberOfPizzas;
    }

    public Order getOrder() {
        return order;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setNumberOfPizzas(Integer numberOfPizzas) {
        this.numberOfPizzas = numberOfPizzas;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "PizzaOrder{" +
                "id=" + id +
                ", pizza=" + pizza +
                ", order=" + order +
                ", numberOfPizzas=" + numberOfPizzas +
                '}';
    }
}
