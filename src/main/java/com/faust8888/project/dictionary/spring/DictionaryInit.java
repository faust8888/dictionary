package com.faust8888.project.dictionary.spring;

import com.faust8888.project.dictionary.service.DictionaryFileReader;
import com.faust8888.project.dictionary.viewItems.DictionaryView;
import com.faust8888.project.dictionary.service.DictionaryService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class DictionaryInit implements BeanPostProcessor {

    @Autowired
    private DictionaryFileReader dictionaryFileReader;

    @Override
    public Object postProcessBeforeInitialization(final Object bean,final String beanName) throws BeansException {
        if (bean instanceof DictionaryService) {
            DictionaryService dictionaryService = (DictionaryService)bean;
            try {
                DictionaryView dictionaryView = dictionaryFileReader.readDictionary("Words.xlsx");
                dictionaryService.installDictionary(dictionaryView);
            } catch (Exception e) {
                System.out.println("DictionaryInit Exception. Couldn't load dictionary: " + e.getMessage());
            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean,final String beanName) throws BeansException {
        return bean;
    }
}
