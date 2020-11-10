package com.example.rickandmorty.models;

import com.google.gson.annotations.SerializedName;

public class Character {

    @SerializedName("name")
    public String name;
    @SerializedName("status")
    public String status;
    //@SerializedName("location")
    //public String location;
    @SerializedName("gender")
    public String gender;
    @SerializedName("species")
    public String species;
    @SerializedName("image")
    public String image;
}
