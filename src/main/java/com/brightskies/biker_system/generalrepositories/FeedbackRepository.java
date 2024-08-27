package com.brightskies.biker_system.generalrepositories;

import com.brightskies.biker_system.generalmodels.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack,Long>
{
    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.order.biker.id = :biker")
    long countFeedbackByBiker(@Param("bikerId") Long biker);
}
