package by.intexsoft.imolchan.jobsystem.handler;

import by.intexsoft.imolchan.jobsystem.entity.Job;
import by.intexsoft.imolchan.jobsystem.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;

@Component(DefaultHandler.BEAN_NAME)
@Slf4j
public class DefaultHandler extends AbstractJobHandler {
    public static final String BEAN_NAME = "default_handler";

    private final Random random = new Random();

    protected DefaultHandler(ExecutorService executor, JobRepository jobRepository) {
        super(executor, jobRepository);
    }

    @Override
    public void handle(Job job) {
        log.info("Starting handling job with id: {}", job.getId());
        int randomInt = random.nextInt(10);
        log.info("Random int: {}", randomInt);

        for (int i = 0; i < randomInt; i++) {
            if (shouldBeStopped.contains(job.getId())) {
                return;
            }

            log.info("JOB ID: {}, JOB PAYLOAD: {}", job.getId(), job.getJobDefinition().getPayload());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
