package by.intexsoft.imolchan.jobsystem.service;

import by.intexsoft.imolchan.jobsystem.dto.JobDefinitionDTO;
import by.intexsoft.imolchan.jobsystem.entity.JobDefinition;
import by.intexsoft.imolchan.jobsystem.exception.EntityNotFoundException;
import by.intexsoft.imolchan.jobsystem.repository.JobDefinitionRepository;
import by.intexsoft.imolchan.jobsystem.util.CronUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobDefinitionService {
    private final JobDefinitionRepository jobDefinitionRepository;
    private final ModelMapper modelMapper;

    public JobDefinitionDTO saveJobDefinition(JobDefinitionDTO jobDefinitionDTO) {
        JobDefinition jobDefinition = modelMapper.map(jobDefinitionDTO, JobDefinition.class);

        LocalDateTime jobNextRun = CronUtils.getNextRunFromCronExpression(jobDefinitionDTO.getCronExpression());
        jobDefinition.setNextRun(jobNextRun);

        return modelMapper.map(jobDefinitionRepository.save(jobDefinition), JobDefinitionDTO.class);
    }

    public JobDefinitionDTO getById(Long id) {
        JobDefinition jobDefinition = jobDefinitionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find job definition with id: " + id));
        return modelMapper.map(jobDefinition, JobDefinitionDTO.class);
    }

    public List<JobDefinitionDTO> getAll() {
        return jobDefinitionRepository.findAll().stream()
                .map(jobDefinition -> modelMapper.map(jobDefinition, JobDefinitionDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        jobDefinitionRepository.deleteById(id);
    }

    @PostConstruct
    private void addJobDefinitionMappings() {
        modelMapper.createTypeMap(JobDefinition.class, JobDefinitionDTO.class).addMappings(
                mapper -> mapper.map(
                        jobDefinition -> jobDefinition.getJobType().getId(),
                        JobDefinitionDTO::setJobTypeId
                )
        );

        modelMapper.createTypeMap(JobDefinitionDTO.class, JobDefinition.class).addMappings(
                mapper -> mapper.map(
                        JobDefinitionDTO::getJobTypeId,
                        (JobDefinition jobDefinition, Long id) -> jobDefinition.getJobType().setId(id)
                )
        );
    }
}
