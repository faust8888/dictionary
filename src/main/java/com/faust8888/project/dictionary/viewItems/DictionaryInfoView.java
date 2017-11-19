package com.faust8888.project.dictionary.viewItems;


public class DictionaryInfoView {

    private String dictionaryName;
    private Integer countOfWords;

    public DictionaryInfoView(final String dictionaryName, final Integer countOfWords) {
        this.dictionaryName = dictionaryName;
        this.countOfWords = countOfWords;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public Integer getCountOfWords() {
        return countOfWords;
    }

    public void setCountOfWords(Integer countOfWords) {
        this.countOfWords = countOfWords;
    }
}
