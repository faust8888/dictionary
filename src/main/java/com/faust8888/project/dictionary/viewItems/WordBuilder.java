package com.faust8888.project.dictionary.viewItems;


public class WordBuilder {

    private String word;
    private String translate;
    private String context;
    private String meaning;

    public WordBuilder setWord(String word) {
        this.word = word;
        return this;
    }

    public WordBuilder setTranslate(String translate) {
        this.translate = translate;
        return this;
    }

    public WordBuilder setContext(String context) {
        this.context = context;
        return this;
    }

    public WordBuilder setMeaning(String meaning) {
        this.meaning = meaning;
        return this;
    }

    public WordView buildWord() {
        return new WordView(word, translate, context, meaning);
    }

    public boolean isWordReady() {
        if (word != null && translate != null && context != null && meaning != null) {
            return true;
        } else {
            return false;
        }
    }

}
