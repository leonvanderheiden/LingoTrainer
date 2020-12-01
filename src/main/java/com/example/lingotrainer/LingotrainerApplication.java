package com.example.lingotrainer;

import com.example.lingotrainer.filterwords.presentation.ProcessWordsCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class LingotrainerApplication {

	public static ProcessWordsCommand processWordsCommand;

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(LingotrainerApplication.class, args);
	}
}



