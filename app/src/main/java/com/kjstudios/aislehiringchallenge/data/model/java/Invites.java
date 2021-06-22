package com.kjstudios.aislehiringchallenge.data.model.java;

public class Invites {
    int pending_invitations_count , totalPages;

    public Invites(int pending_invitations_count, int totalPages) {
        this.pending_invitations_count = pending_invitations_count;
        this.totalPages = totalPages;
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
}
