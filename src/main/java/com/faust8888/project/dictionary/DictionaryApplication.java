package com.faust8888.project.dictionary;

import com.faust8888.project.dictionary.spring.DictionaryInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DictionaryApplication {

	@Bean
	public DictionaryInit dictionaryInit() {
		return new DictionaryInit();
	}

	public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}
}
