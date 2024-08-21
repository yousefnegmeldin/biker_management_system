package com.brightskies.biker_system.generalmodels;

import com.brightskies.biker_system.order.model.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
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

    public FeedBack(Order order, LocalDate orderDate, LocalDate feedbackDate, int rating, String text) {
        this.order = order;
        this.orderDate = orderDate;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
        this.text = text;
    }
}
