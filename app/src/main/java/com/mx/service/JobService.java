package com.mx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mx.model.Job;
import com.mx.repository.JobRepository;


@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllMyJobs(Long userId){
        return jobRepository.findAllMyJobs(userId);
    }

    public List<Job> getAllPublishedJobs(){
        return jobRepository.findAllPublishedJobs();
    }

    public Optional<Job> getSpecificJob(Long jobId){
        return jobRepository.findById(jobId);
    }

    public Job createJob(Job job){
        return jobRepository.save(job);
    }
}
