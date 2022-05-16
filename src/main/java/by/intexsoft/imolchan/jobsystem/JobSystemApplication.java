package by.intexsoft.imolchan.jobsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSystemApplication.class, args);
	}

}
