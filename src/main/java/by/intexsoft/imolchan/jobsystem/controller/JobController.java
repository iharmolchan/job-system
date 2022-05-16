package by.intexsoft.imolchan.jobsystem.controller;


import by.intexsoft.imolchan.jobsystem.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/execute")
    public void runJob(@RequestParam Long jobDefinitionId) {
       jobService.runJob(jobDefinitionId);
    }

    @GetMapping("/cancel")
    public void cancelJob(@RequestParam Long jobId) {
        jobService.cancelJob(jobId);
    }
}
