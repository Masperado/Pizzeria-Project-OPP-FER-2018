package hr.fer.opp.pizzeriasystem.models;

import hr.fer.opp.pizzeriasystem.models.enums.ReportTimeSpan;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "report_begin_date")
    private Date reportBeginDate;

    @Column(name = "report_end_date")
    private Date reportEndDate;

    @Column(name = "pizzas_sold")
    private Integer numberOfPizzasSold;

    @Column(name = "money_earned")
    private Double moneyEarned;

    @Column(name = "number_of_orders")
    private Integer numberOfOrders;

    @Column(name = "distinct_users_ordered")
    private Integer distinctUsersOrdered;

    @Column(name = "max_order")
    private Double maxOrder;

    @Column(name = "min_order")
    private Double minOrder;

    @Column(name = "time_span", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportTimeSpan reportTimeSpan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReportBeginDate() {
        return reportBeginDate;
    }

    public void setReportBeginDate(Date reportBeginDate) {
        this.reportBeginDate = reportBeginDate;
    }

    public Date getReportEndDate() {
        return reportEndDate;
    }

    public void setReportEndDate(Date reportEndDate) {
        this.reportEndDate = reportEndDate;
    }

    public Integer getNumberOfPizzasSold() {
        return numberOfPizzasSold;
    }

    public void setNumberOfPizzasSold(Integer numberOfPizzasSold) {
        this.numberOfPizzasSold = numberOfPizzasSold;
    }

    public Double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(Double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public Integer getDistinctUsersOrdered() {
        return distinctUsersOrdered;
    }

    public void setDistinctUsersOrdered(Integer distinctUsersOrdered) {
        this.distinctUsersOrdered = distinctUsersOrdered;
    }

    public Double getMaxOrder() {
        return maxOrder;
    }

    public Double getMinOrder() {
        return minOrder;
    }

    public void setMaxOrder(Double maxOrder) {
        this.maxOrder = maxOrder;
    }

    public void setMinOrder(Double minOrder) {
        this.minOrder = minOrder;
    }

    public ReportTimeSpan getReportTimeSpan() {
        return reportTimeSpan;
    }

    public void setReportTimeSpan(ReportTimeSpan reportTimeSpan) {
        this.reportTimeSpan = reportTimeSpan;
    }
}
