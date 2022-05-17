package by.intexsoft.imolchan.jobsystem.service;

import by.intexsoft.imolchan.jobsystem.config.MapperConfig;
import by.intexsoft.imolchan.jobsystem.dto.JobTypeDTO;
import by.intexsoft.imolchan.jobsystem.entity.JobType;
import by.intexsoft.imolchan.jobsystem.exception.EntityNotFoundException;
import by.intexsoft.imolchan.jobsystem.repository.JobTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {JobTypeService.class, MapperConfig.class})
@ExtendWith(SpringExtension.class)
class JobTypeServiceTest {
    @MockBean
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private JobTypeService jobTypeService;

    /**
     * Method under test: {@link JobTypeService#saveJobType(JobTypeDTO)}
     */
    @Test
    void testSaveJobType() {
        JobType jobType = createSampleEntity();

        when(jobTypeRepository.save((JobType) any())).thenReturn(jobType);

        JobTypeDTO actualSaveJobTypeResult = jobTypeService.saveJobType(new JobTypeDTO());

        assertEquals("Handler Name", actualSaveJobTypeResult.getHandlerName());
        assertEquals("Name", actualSaveJobTypeResult.getName());
        assertEquals(123L, actualSaveJobTypeResult.getId().longValue());
        verify(jobTypeRepository).save((JobType) any());
    }

    /**
     * Method under test: {@link JobTypeService#getById(Long)}
     */
    @Test
    void testGetById() {
        JobTypeDTO jobTypeDTO = createSampleDto();
        JobType jobType = createSampleEntity();

        Optional<JobType> ofResult = Optional.of(jobType);
        when(jobTypeRepository.findById((Long) any())).thenReturn(ofResult);

        assertEquals(jobTypeDTO, jobTypeService.getById(123L));
        verify(this.jobTypeRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link JobTypeService#getById(Long)}
     */
    @Test
    void testGetByIdEntityNotFound() {
        when(jobTypeRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> jobTypeService.getById(123L));
        verify(this.jobTypeRepository).findById((Long) any());
    }

    private JobTypeDTO createSampleDto() {
        JobTypeDTO jobTypeDTO = new JobTypeDTO();
        jobTypeDTO.setHandlerName("Handler Name");
        jobTypeDTO.setId(123L);
        jobTypeDTO.setName("Name");
        return jobTypeDTO;
    }

    private JobType createSampleEntity() {
        JobType jobType = new JobType();
        jobType.setHandlerName("Handler Name");
        jobType.setId(123L);
        jobType.setName("Name");
        return jobType;
    }
}

