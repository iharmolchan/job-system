package by.intexsoft.imolchan.jobsystem.controller;


import by.intexsoft.imolchan.jobsystem.dto.JobDTO;
import by.intexsoft.imolchan.jobsystem.entity.JobStatus;
import by.intexsoft.imolchan.jobsystem.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
@Slf4j
public class JobController {
    private final JobService jobService;

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable Long id) {
        log.info("Getting job with id: {}.", id);
        return jobService.getJobById(id);
    }

    @GetMapping("/stats")
    public Map<JobStatus, List<JobDTO>> getCurrentJobs() {
        log.info("Getting curretly running and pending jobs.");
        return jobService.getCurrentlyRunningJobs();
    }

    @GetMapping("/execute")
    public void runJob(@RequestParam Long jobDefinitionId) {
        log.info("Creating and executing job for the definition with id: {}.", jobDefinitionId);
        jobService.runJob(jobDefinitionId);
    }

    @GetMapping("/cancel")
    public void cancelJob(@RequestParam Long jobId) {
        log.info("Cancelling job with id: {}.", jobId);
        jobService.cancelJob(jobId);
    }
}
