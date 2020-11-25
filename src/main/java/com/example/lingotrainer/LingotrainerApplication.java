package com.example.lingotrainer;

import com.example.lingotrainer.filterwords.FilterWords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LingotrainerApplication {

	public static FilterWords filterWords = new FilterWords();

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(LingotrainerApplication.class, args);
		filterWords.getWords();
	}


}
