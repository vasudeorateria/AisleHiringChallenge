package com.kjstudios.aislehiringchallenge.data.model.java;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InviteProfile {

    @SerializedName("general_information")
    GeneralInformation generalInformation;
    List<Photo> photos;

    public InviteProfile(GeneralInformation generalInformation, List<Photo> photos) {
        this.generalInformation = generalInformation;
        this.photos = photos;
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public List<Photo> getPhoto() {
        return photos;
    }

    public void setPhoto(List<Photo> photo) {
        this.photos = photo;
    }
}
