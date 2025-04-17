package com.notetaking.note_taking.model.core;

public class CreateFolderRequest {
    private Long userId;
    private String name;
    private boolean isFavorite;


    public Long getUserId() {
        return userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
