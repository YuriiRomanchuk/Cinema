package com.cinema.model.dto;

import java.util.Date;

public class FilmDto {

    private String name;
    private String nameEnglish;
    private String releaseDate;
    private String description;

    public String getName() {
        return name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
