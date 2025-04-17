package com.notetaking.note_taking.model.core;

public enum StatusEnum {
    TO_DO("Хийх"),
    IN_PROGRESS("Хийгдэж буй"),
    DONE("Хийгдсэн");

    private final String name;

    private StatusEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return toString();
    }

}
