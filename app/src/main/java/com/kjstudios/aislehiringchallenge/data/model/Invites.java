package com.kjstudios.aislehiringchallenge.data.model;

import java.util.List;

public class Invites {
    int pending_invitations_count, totalPages;
    List<InviteProfile> profiles;

    public Invites(int pending_invitations_count, int totalPages, List<InviteProfile> profiles) {
        this.pending_invitations_count = pending_invitations_count;
        this.totalPages = totalPages;
        this.profiles = profiles;
    }

    public int getPending_invitations_count() {
        return pending_invitations_count;
    }

    public void setPending_invitations_count(int pending_invitations_count) {
        this.pending_invitations_count = pending_invitations_count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<InviteProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<InviteProfile> profiles) {
        this.profiles = profiles;
    }
}
