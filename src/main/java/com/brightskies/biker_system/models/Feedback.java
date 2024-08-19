package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@Entity
public class Feedback
{
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId")
    private Order order;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="bikerId",nullable = false)
    private Biker biker;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private LocalDate feedbackDate;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String text;

    public Feedback(){}

    public Feedback(Order order, Biker biker, LocalDate orderDate, LocalDate feedbackDate, int rating, String text) {
        this.order = order;
        this.biker = biker;
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

    public Biker getBiker() {
        return biker;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
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
