package com.brightskies.biker_system.generalmodels;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.order.model.Orderr;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity
public class FeedBack
{
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="order_id")
    private Orderr order;

    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private LocalDate feedbackDate;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    private String text;
    public FeedBack(){}

    public FeedBack(Orderr order, LocalDate orderDate, LocalDate feedbackDate, int rating, String text) {
        this.order = order;
        this.orderDate = orderDate;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
        this.text = text;
    }

    public Orderr getOrder() {
        return order;
    }

    public void setOrder(Orderr order) {
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
