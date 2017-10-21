package com.faust8888.project.dictionary.viewItems;

public final class WordView {

    private String word;
    private String translate;
    private String context;
    private String meaning;

    public WordView() {}

    public WordView(String word, String translate, String context, String meaning) {
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
}
