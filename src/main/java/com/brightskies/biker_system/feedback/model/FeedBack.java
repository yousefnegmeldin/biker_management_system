package com.brightskies.biker_system.feedback.model;

import com.brightskies.biker_system.order.model.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer rating;
    @Column(nullable = false)
    private String text;

    public FeedBack(Order order, int rating, String text) {
        this.order = order;
        this.rating = rating;
        this.text = text;
    }
}
