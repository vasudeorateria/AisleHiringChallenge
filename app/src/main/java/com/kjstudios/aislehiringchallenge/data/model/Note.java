package com.kjstudios.aislehiringchallenge.data.model;


public class Note {

    Invites invites;
    Likes likes;

    public Note(Invites invites, Likes likes) {
        this.invites = invites;
        this.likes = likes;
    }

    public Invites getInvites() {
        return invites;
    }

    public void setInvites(Invites invites) {
        this.invites = invites;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }
}
