package by.intexsoft.imolchan.jobsystem.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
       return new ModelMapper();
    }
}
