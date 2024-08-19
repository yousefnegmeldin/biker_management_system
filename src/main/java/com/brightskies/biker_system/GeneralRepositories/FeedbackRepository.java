package com.brightskies.biker_system.GeneralRepositories;

import com.brightskies.biker_system.GeneralModels.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,String>
{

}
