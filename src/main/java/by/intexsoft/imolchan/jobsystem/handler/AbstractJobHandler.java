package by.intexsoft.imolchan.jobsystem.handler;

import by.intexsoft.imolchan.jobsystem.entity.JobStatus;
import by.intexsoft.imolchan.jobsystem.entity.Job;
import by.intexsoft.imolchan.jobsystem.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Slf4j
public abstract class AbstractJobHandler implements JobHandler {
    protected final List<Long> shouldBeStopped;
    private final ExecutorService executor;
    private final JobRepository jobRepository;


    protected AbstractJobHandler(ExecutorService executor, JobRepository jobRepository) {
        this.executor = executor;
        this.jobRepository = jobRepository;
        this.shouldBeStopped = new ArrayList<>();
    }

    @Override
    public void run(Job job) {
        log.info("Preparing and submitting job with id {} to executor service.", job.getId());
        Runnable runnable = () -> {
            try {
                setInProgressStatus(job);
                handle(job);
                if (job.getStatus() != JobStatus.CANCELED) {
                    setSuccessStatus(job);
                }
            } catch (Exception e) {
                setErrorStatus(job);
                throw e;
            } finally {
                shouldBeStopped.remove(job.getId());
            }
        };
        executor.submit(runnable);
    }

    @Override
    public void cancel(Job job) {
        setCancelledStatus(job);
        shouldBeStopped.add(job.getId());
    }

    public abstract void handle(Job job);

    private void setSuccessStatus(Job job) {
        job.setEnd(LocalDateTime.now());
        job.setStatus(JobStatus.SUCCESS);
        jobRepository.save(job);
    }

    private void setErrorStatus(Job job) {
        job.setEnd(LocalDateTime.now());
        job.setStatus(JobStatus.ERROR);
        jobRepository.save(job);
    }

    private void setInProgressStatus(Job job) {
        job.setStart(LocalDateTime.now());
        job.setStatus(JobStatus.IN_PROGRESS);
        jobRepository.save(job);
    }

    private void setCancelledStatus(Job job) {
        job.setEnd(LocalDateTime.now());
        job.setStatus(JobStatus.CANCELED);
        jobRepository.save(job);
    }
}
