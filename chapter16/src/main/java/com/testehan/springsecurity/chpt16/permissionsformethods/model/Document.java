package com.testehan.springsecurity.chpt16.permissionsformethods.model;

public class Document {
    private String owner;

    public Document(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
