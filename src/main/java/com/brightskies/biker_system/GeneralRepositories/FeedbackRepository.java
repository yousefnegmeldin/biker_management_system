package com.brightskies.biker_system.GeneralRepositories;

import com.brightskies.biker_system.GeneralModels.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,String>
{

}
