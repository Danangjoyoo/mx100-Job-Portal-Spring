package com.mx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("SELECT p FROM Proposal p where p.job.jobId = :targetId")
    public List<Proposal> getJobsProposal(@Param("targetId") Long jobId);
}
