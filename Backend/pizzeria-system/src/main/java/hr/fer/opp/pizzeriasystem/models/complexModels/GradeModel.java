package hr.fer.opp.pizzeriasystem.models.complexModels;

import java.util.List;

public class GradeModel {

    private Integer orderId;

    private String token;

    private List<GradeAndPizza> grades;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public List<GradeAndPizza> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeAndPizza> grades) {
        this.grades = grades;
    }
}
