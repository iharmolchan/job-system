package by.intexsoft.imolchan.jobsystem.controller;

import by.intexsoft.imolchan.jobsystem.dto.JobDefinitionDTO;
import by.intexsoft.imolchan.jobsystem.service.JobDefinitionService;
import by.intexsoft.imolchan.jobsystem.views.CrudView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/job/definition")
@RequiredArgsConstructor
@Slf4j
public class JobDefinitionController {
    private final JobDefinitionService jobDefinitionService;

    @PostMapping
    @JsonView(CrudView.READ.class)
    @ResponseStatus(HttpStatus.CREATED)
    public JobDefinitionDTO createJobDefinition(
            @JsonView(CrudView.CREATE.class)
            @Valid
            @RequestBody JobDefinitionDTO jobDefinitionDTO
    ) {
        log.info("Creating job definition: {}", jobDefinitionDTO);
        return jobDefinitionService.saveJobDefinition(jobDefinitionDTO);
    }

    @PutMapping
    public JobDefinitionDTO updateJobDefinition(
            @JsonView(CrudView.UPDATE.class)
            @Validated(CrudView.UPDATE.class)
            @RequestBody JobDefinitionDTO jobDefinitionDTO
    ) {
        log.info("Updating job definition: {}", jobDefinitionDTO);
        return jobDefinitionService.saveJobDefinition(jobDefinitionDTO);
    }

    @JsonView(CrudView.READ.class)
    @GetMapping
    public List<JobDefinitionDTO> getAllJobDefinitions() {
        log.info("Getting all job types");
        return jobDefinitionService.getAll();
    }

    @JsonView(CrudView.READ.class)
    @GetMapping("/{id}")
    public JobDefinitionDTO getJobDefinitionById(@PathVariable Long id) {
        log.info("Getting job definition with id: {}", id);
        return jobDefinitionService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJobDefinitionById(@PathVariable Long id) {
        log.info("Deleting job definition with id: {}", id);
        jobDefinitionService.deleteById(id);
    }
}
