package com.kjstudios.aislehiringchallenge.data.model;

public class Profile {

    String avatar, first_name;

    public Profile(String avatar, String first_name) {
        this.avatar = avatar;
        this.first_name = first_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
