package by.intexsoft.imolchan.jobsystem.controller;

import by.intexsoft.imolchan.jobsystem.dto.JobTypeDTO;
import by.intexsoft.imolchan.jobsystem.service.JobTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/job-type")
public class JobTypeController {
    private final JobTypeService jobTypeService;

    @PostMapping
    public JobTypeDTO upsertJobType(@RequestBody JobTypeDTO jobTypeDTO) {
        return jobTypeService.saveJobType(jobTypeDTO);
    }

    @GetMapping
    public List<JobTypeDTO> getAllJobTypes() {
        return jobTypeService.getAll();
    }

    @GetMapping("/{id}")
    public JobTypeDTO getJobTypeById(@PathVariable Long id) {
        return jobTypeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteJobTypeById(@PathVariable Long id) {
        jobTypeService.deleteById(id);
    }
}
