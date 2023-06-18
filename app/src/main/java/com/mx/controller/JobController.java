package com.mx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.model.Job;
import com.mx.model.Proposal;
import com.mx.model.User;
import com.mx.schema.PostJobSchema;
import com.mx.schema.SuccessSchema;
import com.mx.service.JobService;
import com.mx.service.ProposalService;
import com.mx.service.UserService;
import com.mx.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final UserService userService;
    private final ProposalService proposalService;
    private final JwtUtil jwtUtil;

    private User getUser(String bearerKey){
        String username = jwtUtil.getUsernameFromAuth(bearerKey);

        Optional<User> optUser = userService.findUser(username);
        if (!optUser.isPresent()){
            log.info("invalid user : {}", username);
            return null;
        }

        User user = optUser.get();
        if (user.getRole().getRoleId() != 1){
            log.info("invalid role");
            return null;
        }

        return user;
    }

    public JobController(JobService jobService, UserService userService, ProposalService proposalService, JwtUtil jwtUtil){
        this.jobService = jobService;
        this.userService = userService;
        this.proposalService = proposalService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping({"", "/"})
    public List<Job> getAllJobs(@RequestHeader("Authorization") String bearerKey){
        User user = getUser(bearerKey);
        if (user == null) return new ArrayList<Job>();

        if (user.getRole().getRoleId() == 1)
            return this.jobService.getAllMyJobs(user.getUserId());
        else return this.jobService.getAllPublishedJobs();
    }

    @GetMapping("/{jobId}")
    public Optional<Job> getSpecificJob(@PathVariable Long jobId){
        return this.jobService.getSpecificJob(jobId);
    }

    @PostMapping("")
    public SuccessSchema createNewJob(
        @RequestHeader("Authorization") String bearerKey,
        @RequestBody PostJobSchema postJob
    ){
        User user = getUser(bearerKey);
        if (user == null) return new SuccessSchema(0);

        Integer publishedStatus = 0;
        if (postJob.isPublished() == true) publishedStatus = 1;

        Job newJob = new Job();
        newJob.setJobTitle(postJob.getTitle());
        newJob.setJobDesc(postJob.getDesc());
        newJob.setIsPublished(publishedStatus);
        newJob.setUser(user);
        jobService.createJob(newJob);

        return new SuccessSchema(1);
    }

    @GetMapping("/{jobId}/proposals")
    public List<Proposal> getProposals(
        @PathVariable Long jobId
    ) {
        return this.proposalService.getJobsProposalList(jobId);
    }
}
