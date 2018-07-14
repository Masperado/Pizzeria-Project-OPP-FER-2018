package hr.fer.opp.pizzeriasystem.models;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Transient
    private String token;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getToken() {
        return token;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGrade() {
        return grade;
    }

    public User getUser() {
        return user;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", pizza=" + pizza +
                ", user=" + user +
                ", grade=" + grade +
                '}';
    }
}
