package com.brightskies.biker_system.feedback.repository;

import com.brightskies.biker_system.feedback.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack,Long>
{
    @Query("SELECT COUNT(f) FROM FeedBack f WHERE f.order.biker.id = :biker")
    long countFeedbackByBiker(@Param("biker") Long biker);

    @Query("SELECT f.rating, f.text FROM FeedBack f WHERE f.order.biker.id = :biker")
    List<Object[]> findRatingAndTextByBikerId(@Param("biker") Long biker);
}
