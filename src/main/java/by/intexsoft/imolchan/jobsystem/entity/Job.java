package by.intexsoft.imolchan.jobsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Job extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "job_definition_id")
    private JobDefinition jobDefinition;
}
