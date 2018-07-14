package hr.fer.opp.pizzeriasystem.models;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_program")
public class LoyaltyProgram {

    public static int TOTAL_PIZZAS_TO_GET_FREE = 5;

    @Transient
    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "remaining", nullable = false)
    private Integer pizzasRemainingUntilFree;

    @Column(name = "total", nullable = false)
    private Integer totalPizzasToGetFreeOne;

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPizzasRemainingUntilFree() {
        return pizzasRemainingUntilFree;
    }

    public void setPizzasRemainingUntilFree(Integer pizzasRemainingUntilFree) {
        this.pizzasRemainingUntilFree = pizzasRemainingUntilFree;
    }

    public Integer getTotalPizzasToGetFreeOne() {
        return totalPizzasToGetFreeOne;
    }

    public void setTotalPizzasToGetFreeOne(Integer totalPizzasToGetFreeOne) {
        this.totalPizzasToGetFreeOne = totalPizzasToGetFreeOne;
    }
}
