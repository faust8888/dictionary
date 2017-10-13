package com.faust8888.project.dictionary.controllers;


import com.faust8888.project.dictionary.items.Word;
import com.faust8888.project.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class HomeController {

	@Autowired
	DictionaryService dictionaryService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/api/next", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Word> next() {
		return dictionaryService.getWords();
	}
}
