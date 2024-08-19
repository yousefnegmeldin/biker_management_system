package com.brightskies.biker_system.GeneralModels;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity
public class Feedback
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String orderId;
    @Column(nullable = false)
    String bikerId;
    @Column(nullable = false)
    Date orderDate;
    @Column(nullable = false)
    Date feedbackDate;
    @Column(nullable = false)
    int rating;
    @Column(nullable = false)
    String text;

    public Feedback(){}

    public Feedback(String orderId, String bikerId, Date orderDate, Date feedbackDate, int rating, String text) {
        this.orderId = orderId;
        this.bikerId = bikerId;
        this.orderDate = orderDate;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
        this.text = text;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBikerId() {
        return bikerId;
    }

    public void setBikerId(String bikerId) {
        this.bikerId = bikerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
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
