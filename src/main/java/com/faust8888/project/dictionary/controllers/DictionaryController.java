package com.faust8888.project.dictionary.controllers;

import com.faust8888.project.dictionary.db.items.User;
import com.faust8888.project.dictionary.service.UserService;
import com.faust8888.project.dictionary.viewItems.DictionaryInfoView;
import com.faust8888.project.dictionary.viewItems.WordView;
import com.faust8888.project.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/dictionary")
	public String index() {
		User user = userService.getUserByLogin("faust8888");
		dictionaryService.installDictionaryUser(user);
		return "index";
	}

	@RequestMapping(value = "/api/next", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<WordView> next() {
		return dictionaryService.getWords(10);
	}

	@RequestMapping(value = "/api/saveWord", method = RequestMethod.POST)
	public @ResponseBody DictionaryInfoView saveWord(@RequestBody WordView word) {
		dictionaryService.saveWord(word);
		return dictionaryInfo();
	}

	@RequestMapping(value = "/api/dictionaryInfo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DictionaryInfoView dictionaryInfo() {
		return dictionaryService.getDictionaryInfo();
	}
}
