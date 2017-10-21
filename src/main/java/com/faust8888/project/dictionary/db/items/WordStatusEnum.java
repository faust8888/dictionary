package com.faust8888.project.dictionary.db.items;


public enum WordStatusEnum {

    BAD(1L),
    NORMAL(2L),
    GOOD(3L);

    WordStatusEnum(Long statusId) {
        this.statusId = statusId;
    }

    private Long statusId;
    private String name;

    public Long getStatusId() {
        return statusId;
    }
}
