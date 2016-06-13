package com.scmspain.learning.controller.request;

public class SaveFrameworkRequest {
    private Long id;
    private String name;
    private Long love;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLove() {
        return love;
    }

    public void setLove(Long love) {
        this.love = love;
    }
}
