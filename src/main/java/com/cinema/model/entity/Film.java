package com.cinema.model.entity;


import java.util.Date;

public class Film {

    private int id;
    private String name;
    private String nameEnglish;
    private Date releaseDate;
    private String description;
    private String descriptionEnglish;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEnglish() {
        return descriptionEnglish;
    }

    public void setDescriptionEnglish(String descriptionEnglish) {
        this.descriptionEnglish = descriptionEnglish;
    }
}
