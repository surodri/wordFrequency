package com.frequency.frequency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class FrequencyApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext program = SpringApplication.run(FrequencyApplication.class, args);
		FrequencyService service = new FrequencyService();
		String text = service.readFile("text.txt");
		service.getWordsFrequencies(text);

		program.close();
	}

}
