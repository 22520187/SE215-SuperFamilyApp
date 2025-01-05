package com.example.se215_superfamilyapp.model;

public class MemberTag {
    private String name;
    private boolean isAddButton;

    public MemberTag(String name, boolean isAddButton) {
        this.name = name;
        this.isAddButton = isAddButton;
    }

    public String getName() {
        return name;
    }

    public boolean isAddButton() {
        return isAddButton;
    }
}
