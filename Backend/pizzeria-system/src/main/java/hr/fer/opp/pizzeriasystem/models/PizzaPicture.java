package hr.fer.opp.pizzeriasystem.models;


import javax.persistence.*;

@Entity
@Table(name = "pizza_picture")
public class PizzaPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private  Pizza pizza;

    @Lob
    @Column(name = "picture", nullable = true)
    private String picture;


    public Integer getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }
}

