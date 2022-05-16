package by.intexsoft.imolchan.jobsystem.controller;

import by.intexsoft.imolchan.jobsystem.dto.JobTypeDTO;
import by.intexsoft.imolchan.jobsystem.service.JobTypeService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/job/type")
@Slf4j
public class JobTypeController {
    private final JobTypeService jobTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobTypeDTO createJobType(
            @JsonView(CrudView.CREATE.class)
            @Valid
            @RequestBody JobTypeDTO jobTypeDTO
    ) {
        log.info("Creating job type: {}", jobTypeDTO);
        return jobTypeService.saveJobType(jobTypeDTO);
    }

    @PutMapping
    @JsonView(CrudView.READ.class)
    public JobTypeDTO updateJobType(
            @JsonView(CrudView.UPDATE.class)
            @Validated(CrudView.UPDATE.class)
            @RequestBody JobTypeDTO jobTypeDTO
    ) {
        log.info("Updating job type: {}", jobTypeDTO);
        return jobTypeService.saveJobType(jobTypeDTO);
    }

    @JsonView(CrudView.READ.class)
    @GetMapping
    public List<JobTypeDTO> getAllJobTypes() {
        log.info("Getting all job types");
        return jobTypeService.getAll();
    }

    @JsonView(CrudView.READ.class)
    @GetMapping("/{id}")
    public JobTypeDTO getJobTypeById(@PathVariable Long id) {
        log.info("Getting job type with id: {}", id);
        return jobTypeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJobTypeById(@PathVariable Long id) {
        log.info("Deleting job type with id: {}", id);
        jobTypeService.deleteById(id);
    }
}
