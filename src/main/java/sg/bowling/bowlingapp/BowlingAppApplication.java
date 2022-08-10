package sg.bowling.bowlingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import sg.bowling.bowlingapp.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BowlingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BowlingAppApplication.class, args);
	}

}
