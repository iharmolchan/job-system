package by.intexsoft.imolchan.jobsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobTypeDTO {
    Long id;
    String name;
    String handlerClass;
}
