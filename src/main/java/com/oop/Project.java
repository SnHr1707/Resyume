package com.oop;

public class Project {
    private String name;
    private String link;

    public Project(String name, String link) {
        this.name=name;
        this.link=link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String toString() {
        return name;
    }
}
