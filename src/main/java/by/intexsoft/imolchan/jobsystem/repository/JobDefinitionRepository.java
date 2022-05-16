package by.intexsoft.imolchan.jobsystem.repository;

import by.intexsoft.imolchan.jobsystem.entity.JobDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDefinitionRepository extends JpaRepository<JobDefinition, Long> {
}
