package com.mx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.model.Proposal;
import com.mx.repository.ProposalRepository;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
    }

    public List<Proposal> getJobsProposalList(Long jobId){
        return this.proposalRepository.getJobsProposal(jobId);
    }

}
