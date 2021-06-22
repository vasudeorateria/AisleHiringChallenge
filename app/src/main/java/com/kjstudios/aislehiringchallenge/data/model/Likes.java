package com.kjstudios.aislehiringchallenge.data.model;

import java.util.List;

public class Likes {

    boolean can_see_profile;
    int likes_received_count;
    List<Profile> profiles;

    public Likes(boolean can_see_profile, int likes_received_count, List<Profile> profiles) {
        this.can_see_profile = can_see_profile;
        this.likes_received_count = likes_received_count;
        this.profiles = profiles;
    }

    public boolean isCan_see_profile() {
        return can_see_profile;
    }

    public void setCan_see_profile(boolean can_see_profile) {
        this.can_see_profile = can_see_profile;
    }

    public int getLikes_received_count() {
        return likes_received_count;
    }

    public void setLikes_received_count(int likes_received_count) {
        this.likes_received_count = likes_received_count;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
