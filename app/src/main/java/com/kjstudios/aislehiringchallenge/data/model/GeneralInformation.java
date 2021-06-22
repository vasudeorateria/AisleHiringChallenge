package com.kjstudios.aislehiringchallenge.data.model;

public class GeneralInformation {
    String first_name;
    int age;

    public GeneralInformation(String first_name, int age) {
        this.first_name = first_name;
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
