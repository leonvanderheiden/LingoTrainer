package com.example.lingotrainer;

import com.example.lingotrainer.filterwords.application.FilterWordsProcessor;
import com.example.lingotrainer.filterwords.data.FileWordReader;
import com.example.lingotrainer.filterwords.data.FileWordWriter;
import com.example.lingotrainer.filterwords.domain.LingoWordFilter;
import com.example.lingotrainer.filterwords.domain.WordFilter;
import com.example.lingotrainer.filterwords.domain.WordReader;
import com.example.lingotrainer.filterwords.domain.WordWriter;
import com.example.lingotrainer.filterwords.presentation.ProcessWordsCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
public class LingotrainerApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(LingotrainerApplication.class, args);

		ProcessWordsCommand pwc = new ProcessWordsCommand();
		pwc.process();

	}
}



