package by.intexsoft.imolchan.jobsystem.entity;

import by.intexsoft.imolchan.jobsystem.validation.ValidCronExpression;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_definitions")
@NoArgsConstructor
@Getter
@Setter
@ToString
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class JobDefinition extends BaseEntity {
    private String name;

    @Type(type = "json")
    private Object payload;

    @ValidCronExpression
    private String cronExpression;

    private LocalDateTime nextRun;

    @ManyToOne
    @JoinColumn(
            name = "job_type_id",
            foreignKey = @ForeignKey(
                    name = "FK_JOB_TYPE",
                    foreignKeyDefinition = "FOREIGN KEY(job_type_id) REFERENCES job_types(id) ON DELETE SET NULL"
            )
    )
    private JobType jobType;
}
