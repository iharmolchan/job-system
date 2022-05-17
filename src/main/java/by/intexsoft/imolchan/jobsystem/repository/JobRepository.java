package by.intexsoft.imolchan.jobsystem.repository;

import by.intexsoft.imolchan.jobsystem.entity.Job;
import by.intexsoft.imolchan.jobsystem.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(
            "SELECT j From Job as j " +
            "INNER JOIN j.jobDefinition as jd " +
            "INNER JOIN jd.jobType as jt " +
            "WHERE j.status in (:jobStatuses)"
    )
    List<Job> getJobsByStatuses(List<JobStatus> jobStatuses);
}
