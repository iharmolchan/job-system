package by.intexsoft.imolchan.jobsystem.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component("default_handler")
@RequiredArgsConstructor
@Slf4j
public class DefaultHandler implements JobHandler{
    private final ExecutorService executor;

    private final List<String> shouldBeStopped = new ArrayList<>();


    public String handle() {
        String uuid = UUID.randomUUID().toString();

        Runnable runnable = () -> {
            while (!shouldBeStopped.contains(uuid)) {
                log.info("UUID: " + uuid);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            shouldBeStopped.remove(uuid);
        };
        executor.submit(runnable);
        return uuid;
    }

    @Override
    public void cancelJob(String jobId) {
        shouldBeStopped.add(jobId);
    }
}
