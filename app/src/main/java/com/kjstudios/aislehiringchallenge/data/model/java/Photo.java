package com.kjstudios.aislehiringchallenge.data.model.java;

public class Photo {

    String photo, status;
    int photo_id;
    Boolean selected;

    public Photo(String photo, String status, int photo_id, Boolean selected) {
        this.photo = photo;
        this.status = status;
        this.photo_id = photo_id;
        this.selected = selected;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
