package com.brightskies.biker_system.generalrepositories;

import com.brightskies.biker_system.generalmodels.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack,String>
{

}
