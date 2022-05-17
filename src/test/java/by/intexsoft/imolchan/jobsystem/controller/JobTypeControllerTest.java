package by.intexsoft.imolchan.jobsystem.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import by.intexsoft.imolchan.jobsystem.dto.JobTypeDTO;
import by.intexsoft.imolchan.jobsystem.service.JobTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {JobTypeController.class})
@ExtendWith(SpringExtension.class)
class JobTypeControllerTest {
    @Autowired
    private JobTypeController jobTypeController;

    @MockBean
    private JobTypeService jobTypeService;

    /**
     * Method under test: {@link JobTypeController#createJobType(JobTypeDTO)}
     */
    @Test
    void testCreateJobType() throws Exception {
        JobTypeDTO jobTypeDTO = getSampleDTO();
        when(jobTypeService.saveJobType((JobTypeDTO) any())).thenReturn(jobTypeDTO);

        String content = (new ObjectMapper()).writeValueAsString(jobTypeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/job/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(jobTypeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(getStringifiedSampleDto()));
    }

    /**
     * Method under test: {@link JobTypeController#deleteJobTypeById(Long)}
     */
    @Test
    void testDeleteJobTypeById() throws Exception {
        doNothing().when(jobTypeService).deleteById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/job/type/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.jobTypeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link JobTypeController#getAllJobTypes()}
     */
    @Test
    void testGetAllJobTypes() throws Exception {
        when(jobTypeService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/job/type");
        MockMvcBuilders.standaloneSetup(jobTypeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobTypeController#getJobTypeById(Long)}
     */
    @Test
    void testGetJobTypeById() throws Exception {
        JobTypeDTO jobTypeDTO = getSampleDTO();
        when(jobTypeService.getById((Long) any())).thenReturn(jobTypeDTO);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/job/type/{id}", 123L);

        MockMvcBuilders.standaloneSetup(this.jobTypeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(getStringifiedSampleDto()));
    }

    /**
     * Method under test: {@link JobTypeController#updateJobType(JobTypeDTO)}
     */
    @Test
    void testUpdateJobType() throws Exception {
        JobTypeDTO jobTypeDTO = getSampleDTO();
        when(this.jobTypeService.saveJobType((JobTypeDTO) any())).thenReturn(jobTypeDTO);

        String content = (new ObjectMapper()).writeValueAsString(jobTypeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/job/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(this.jobTypeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(getStringifiedSampleDto()));
    }

    private JobTypeDTO getSampleDTO() {
        JobTypeDTO jobTypeDTO = new JobTypeDTO();
        jobTypeDTO.setHandlerName("Handler Name");
        jobTypeDTO.setId(123L);
        jobTypeDTO.setName("Name");
        return jobTypeDTO;
    }

    private String getStringifiedSampleDto() {
        return "{\"id\":123,\"name\":\"Name\",\"handlerName\":\"Handler Name\"}";
    }
}

