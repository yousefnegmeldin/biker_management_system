package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,String>
{

}
