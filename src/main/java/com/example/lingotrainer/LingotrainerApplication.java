package com.example.lingotrainer;

import com.example.lingotrainer.filterwords.FilterWordsProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class LingotrainerApplication {

	public static FilterWordsProcessor filterWords = new FilterWordsProcessor();

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(LingotrainerApplication.class, args);
		filterWords.getWords();
	}
}



