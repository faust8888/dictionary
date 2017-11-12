package com.faust8888.project.dictionary.viewItems;


public class DictionaryInfoView {

    private String dictionaryName;
    private Long countOfWords;

    public DictionaryInfoView(final String dictionaryName, final Long countOfWords) {
        this.dictionaryName = dictionaryName;
        this.countOfWords = countOfWords;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public Long getCountOfWords() {
        return countOfWords;
    }

    public void setCountOfWords(Long countOfWords) {
        this.countOfWords = countOfWords;
    }
}
