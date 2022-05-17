package by.intexsoft.imolchan.jobsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.intexsoft.imolchan.jobsystem.config.MapperConfig;
import by.intexsoft.imolchan.jobsystem.dto.JobDefinitionDTO;
import by.intexsoft.imolchan.jobsystem.dto.JobDefinitionDTO;
import by.intexsoft.imolchan.jobsystem.entity.JobDefinition;
import by.intexsoft.imolchan.jobsystem.entity.JobDefinition;
import by.intexsoft.imolchan.jobsystem.entity.JobType;
import by.intexsoft.imolchan.jobsystem.exception.EntityNotFoundException;
import by.intexsoft.imolchan.jobsystem.repository.JobDefinitionRepository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobDefinitionService.class, MapperConfig.class})
@ExtendWith(SpringExtension.class)
class JobDefinitionServiceTest {
    @MockBean
    private JobDefinitionRepository jobDefinitionRepository;

    @Autowired
    private JobDefinitionService jobDefinitionService;

    /**
     * Method under test: {@link JobDefinitionService#saveJobDefinition(JobDefinitionDTO)}
     */
    @Test
    void testSaveJobDefinition() {
        JobDefinition jobDefinition = createSampleEntity();
        JobDefinitionDTO jobDefinitionDTO = createSampleDto();

        when(jobDefinitionRepository.save((JobDefinition) any())).thenReturn(jobDefinition);

        JobDefinitionDTO actualSaveJobDefinitionResult = jobDefinitionService.saveJobDefinition(jobDefinitionDTO);

        assertEquals(jobDefinitionDTO, actualSaveJobDefinitionResult);
        verify(jobDefinitionRepository).save((JobDefinition) any());
    }

    /**
     * Method under test: {@link JobDefinitionService#getById(Long)}
     */
    @Test
    void testGetById() {
        JobDefinitionDTO jobDefinitionDTO = createSampleDto();
        JobDefinition jobDefinition = createSampleEntity();

        Optional<JobDefinition> ofResult = Optional.of(jobDefinition);
        when(jobDefinitionRepository.findById((Long) any())).thenReturn(ofResult);

        assertEquals(jobDefinitionDTO, jobDefinitionService.getById(123L));
        verify(this.jobDefinitionRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link JobDefinitionService#getById(Long)}
     */
    @Test
    void testGetByIdEntityNotFound() {
        when(jobDefinitionRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> jobDefinitionService.getById(123L));
        verify(this.jobDefinitionRepository).findById((Long) any());
    }

    private JobDefinitionDTO createSampleDto() {
        JobDefinitionDTO jobDefinitionDTO = new JobDefinitionDTO();
        jobDefinitionDTO.setId(123L);
        jobDefinitionDTO.setJobTypeId(123L);
        jobDefinitionDTO.setName("Name");
        jobDefinitionDTO.setPayload("Payload");
        jobDefinitionDTO.setCronExpression("* * * * * *");
        jobDefinitionDTO.setNextRun(LocalDateTime.of(1, 1, 1, 1, 1));
        return jobDefinitionDTO;
    }

    private JobDefinition createSampleEntity() {
        JobDefinition jobDefinition = new JobDefinition();
        jobDefinition.setName("Name");
        jobDefinition.setId(123L);
        jobDefinition.setCronExpression("* * * * * *");
        jobDefinition.setPayload("Payload");
        jobDefinition.setNextRun(LocalDateTime.of(1, 1, 1, 1, 1));

        JobType jobType = new JobType();
        jobType.setId(123L);

        jobDefinition.setJobType(jobType);

        return jobDefinition;
    }
}

