package by.intexsoft.imolchan.jobsystem.dto;

import by.intexsoft.imolchan.jobsystem.validation.ValidCronExpression;
import by.intexsoft.imolchan.jobsystem.views.CrudView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class JobDefinitionDTO {
    @JsonView({CrudView.UPDATE.class, CrudView.READ.class})
    @NotNull(groups = CrudView.UPDATE.class)
    private Long id;

    @JsonView({CrudView.CREATE.class, CrudView.UPDATE.class, CrudView.READ.class})
    @NotBlank
    private String name;

    @JsonView({CrudView.CREATE.class, CrudView.UPDATE.class, CrudView.READ.class})
    @ValidCronExpression
    private String cronExpression;

    @JsonView({CrudView.READ.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime nextRun;

    @JsonView({CrudView.CREATE.class, CrudView.UPDATE.class, CrudView.READ.class})
    @NotNull
    private Long jobTypeId;

    @JsonView({CrudView.CREATE.class, CrudView.UPDATE.class, CrudView.READ.class})
    @NotNull
    private Object payload;
}
