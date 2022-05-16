package by.intexsoft.imolchan.jobsystem.repository;

import by.intexsoft.imolchan.jobsystem.entity.JobDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobDefinitionRepository extends JpaRepository<JobDefinition, Long> {
    @Query(
        "SELECT jd FROM JobDefinition AS jd " +
        "INNER JOIN jd.jobType AS jt " +
        "WHERE jd.nextRun IS NOT NULL and jd.nextRun <= current_timestamp"
    )
    List<JobDefinition> getScheduledJobDefinitionsForRun();
}
