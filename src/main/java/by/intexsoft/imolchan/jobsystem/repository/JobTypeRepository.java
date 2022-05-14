package by.intexsoft.imolchan.jobsystem.repository;

import by.intexsoft.imolchan.jobsystem.entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface JobTypeRepository extends JpaRepository<JobType, Long> {
}
