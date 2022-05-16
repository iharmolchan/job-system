package by.intexsoft.imolchan.jobsystem.dto;

import by.intexsoft.imolchan.jobsystem.entity.JobStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobDTO {
    private JobStatus jobStatus;
    private LocalDateTime start;
    private LocalDateTime end;
    private Object payload;
    private String jobDefinitionName;
    private String jobTypeName;
}
