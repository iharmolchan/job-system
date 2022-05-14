package by.intexsoft.imolchan.jobsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_types")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobType extends BaseEntity{
    String name;
    String handlerClass;
}
