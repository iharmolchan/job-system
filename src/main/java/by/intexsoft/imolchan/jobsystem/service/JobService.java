package by.intexsoft.imolchan.jobsystem.service;

import by.intexsoft.imolchan.jobsystem.entity.Job;
import by.intexsoft.imolchan.jobsystem.entity.JobDefinition;
import by.intexsoft.imolchan.jobsystem.entity.JobStatus;
import by.intexsoft.imolchan.jobsystem.exception.EntityNotFoundException;
import by.intexsoft.imolchan.jobsystem.handler.JobHandler;
import by.intexsoft.imolchan.jobsystem.repository.JobDefinitionRepository;
import by.intexsoft.imolchan.jobsystem.repository.JobRepository;
import by.intexsoft.imolchan.jobsystem.util.CronUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final ApplicationContext applicationContext;
    private final JobRepository jobRepository;
    private final JobDefinitionRepository jobDefinitionRepository;

    public void runJob(Long jobDefinitionId) {
        JobDefinition jobDefinition = getJobDefinitionById(jobDefinitionId);
        createAndRunJob(jobDefinition);
    }

    public void cancelJob(Long id) {
        Job job = getById(id);
        JobHandler jobHandler = getJobHandlerBean(job.getJobDefinition());
        jobHandler.cancel(job);
    }


    @Scheduled(cron = "0 * * ? * *")
    private void runScheduledJobs() {
        log.info("Running scheduled jobs.");
        List<JobDefinition> definitionsForRun = jobDefinitionRepository.getScheduledJobDefinitionsForRun();
        log.info("Number of jobs to run: {}", definitionsForRun.size());
        definitionsForRun.forEach(this::createAndRunJob);
    }

    private void createAndRunJob(JobDefinition jobDefinition) {
        Job job = Job.builder()
                .status(JobStatus.PENDING)
                .jobDefinition(jobDefinition)
                .build();
        jobRepository.save(job);

        updateJobNextRun(jobDefinition);

        JobHandler jobHandler = getJobHandlerBean(jobDefinition);
        jobHandler.run(job);
    }

    private void updateJobNextRun(JobDefinition jobDefinition) {
        LocalDateTime jobNextRun = CronUtils
                .getNextRunFromCronExpression(jobDefinition.getCronExpression(), jobDefinition.getNextRun());
        jobDefinition.setNextRun(jobNextRun);
        jobDefinitionRepository.save(jobDefinition);
    }

    private JobHandler getJobHandlerBean(JobDefinition jobDefinition) {
        String handlerName = jobDefinition.getJobType().getHandlerName();
        return applicationContext.getBean(handlerName, JobHandler.class);
    }

    private Job getById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find job with id: " + id));
    }

    private JobDefinition getJobDefinitionById(Long jobDefinitionId) {
        return jobDefinitionRepository.findById(jobDefinitionId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find job definition with id: " + jobDefinitionId));
    }
}
