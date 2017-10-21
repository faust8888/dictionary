package com.faust8888.project.dictionary.viewItems;


public class WordViewBuilder {

    private String word;
    private String translate;
    private String context;
    private String meaning;

    public WordViewBuilder setWord(String word) {
        this.word = word;
        return this;
    }

    public WordViewBuilder setTranslate(String translate) {
        this.translate = translate;
        return this;
    }

    public WordViewBuilder setContext(String context) {
        this.context = context;
        return this;
    }

    public WordViewBuilder setMeaning(String meaning) {
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
