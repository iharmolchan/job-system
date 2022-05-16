package by.intexsoft.imolchan.jobsystem.service;

import by.intexsoft.imolchan.jobsystem.dto.JobTypeDTO;
import by.intexsoft.imolchan.jobsystem.entity.JobType;
import by.intexsoft.imolchan.jobsystem.exception.EntityNotFoundException;
import by.intexsoft.imolchan.jobsystem.repository.JobTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.intexsoft.imolchan.jobsystem.util.DtoConverter.convert;

@Service
@RequiredArgsConstructor
public class JobTypeService {
    private final JobTypeRepository jobTypeRepository;

    public JobTypeDTO saveJobType(JobTypeDTO jobTypeDTO) {
        JobType jobType = convert(jobTypeDTO, JobType.class);
        return convert(jobTypeRepository.save(jobType), JobTypeDTO.class);
    }

    public JobTypeDTO getById(Long id) {
        JobType jobType = jobTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find job type with id: " + id));
        return convert(jobType, JobTypeDTO.class);
    }

    public List<JobTypeDTO> getAll() {
        return jobTypeRepository.findAll().stream()
                .map(jobType -> convert(jobType, JobTypeDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        jobTypeRepository.deleteById(id);
    }
}
