package by.intexsoft.imolchan.jobsystem.handler;

import by.intexsoft.imolchan.jobsystem.entity.Job;

public interface JobHandler {
    void run(Job job);

    void cancel(Job job);
}
