package com.mx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.mx.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j where j.isPublished = 1")
    public List<Job> findAllPublishedJobs();

    @Query("SELECT j FROM Job j where j.user.userId = :myId")
    public List<Job> findAllMyJobs(@Param("myId") Long myId);
}
