package by.intexsoft.imolchan.jobsystem.dto;

import by.intexsoft.imolchan.jobsystem.views.CrudView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class JobTypeDTO {
    @JsonView({CrudView.UPDATE.class, CrudView.READ.class})
    @NotNull(groups = CrudView.UPDATE.class)
    Long id;

    @JsonView({CrudView.CREATE.class, CrudView.UPDATE.class, CrudView.READ.class})
    @NotBlank
    String name;

    @NotBlank
    @JsonView({CrudView.CREATE.class, CrudView.UPDATE.class, CrudView.READ.class})
    String handlerName;
}
