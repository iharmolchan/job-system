package by.intexsoft.imolchan.jobsystem.repository;

import by.intexsoft.imolchan.jobsystem.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
