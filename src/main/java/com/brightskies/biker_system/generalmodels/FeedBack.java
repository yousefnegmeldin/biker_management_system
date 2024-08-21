package com.brightskies.biker_system.generalmodels;

import com.brightskies.biker_system.order.model.Order;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class FeedBack
{
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="order_id")
    private Order order;

    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private LocalDate feedbackDate;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String text;
    public FeedBack(){}

    public FeedBack(Order order, LocalDate orderDate, LocalDate feedbackDate, int rating, String text) {
        this.order = order;
        this.orderDate = orderDate;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
        this.text = text;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
