package com.faust8888.project.dictionary.viewItems;

public final class WordView {

    private String word;
    private String translate;
    private String context;
    private String meaning;

    public WordView() {}

    public WordView(final String word, final String translate, final String context, final String meaning) {
        this.word = word;
        this.translate = translate;
        this.context = context;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
