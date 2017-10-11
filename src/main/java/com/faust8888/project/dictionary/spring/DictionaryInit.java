package com.faust8888.project.dictionary.spring;

import com.faust8888.project.dictionary.items.Word;
import com.faust8888.project.dictionary.service.DictionaryFileReader;
import com.faust8888.project.dictionary.service.DictionaryService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;


public class DictionaryInit implements BeanPostProcessor {

    @Autowired
    private DictionaryFileReader dictionaryFileReader;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DictionaryService) {
            DictionaryService dictionaryService = (DictionaryService)bean;
            try {
                List<Word> words = dictionaryFileReader.readDictionary("Words.xlsx");
                dictionaryService.setWords(words);
            } catch (Exception e) {
                System.out.println("DictionaryInit Exception. Couldn't load dictionary: " + e.getMessage());
            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
