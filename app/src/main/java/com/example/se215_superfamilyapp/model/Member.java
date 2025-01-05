package com.example.se215_superfamilyapp.model;

public class Member {
    public void setAvatarResource(int avatarResource) {
        this.avatarResource = avatarResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnshownCount(int unshownCount) {
        this.unshownCount = unshownCount;
    }

    private int avatarResource;
    private String name;
    private int unshownCount;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    private int isSelected;

    public Member(int avatarResource, String name, int unshownCount) {
        this.avatarResource = avatarResource;
        this.name = name;
        this.unshownCount = unshownCount;
        isSelected=0;
    }

    public int getAvatarResource() {
        return avatarResource;
    }

    public String getName() {
        return name;
    }

    public int getUnshownCount() {
        return unshownCount;
    }
}
