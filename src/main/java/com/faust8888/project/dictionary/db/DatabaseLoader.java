/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.faust8888.project.dictionary.db;

import com.faust8888.project.dictionary.items.Word;
import com.faust8888.project.dictionary.service.DictionaryFileReader;
import com.faust8888.project.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class DatabaseLoader implements CommandLineRunner {

	//@Autowired
	//private DictionaryRepository repository;

	//@Autowired
	private DictionaryFileReader dictionaryFileReader;

	//@Autowired
	private DictionaryService dictionaryService;


	//@Override
	public void run(String... strings) throws Exception {
		//List<Word> words = dictionaryService.getWords();
		//this.repository.save(words.get(1));
	}
}