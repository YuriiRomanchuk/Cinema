package com.cinema.validator;

import com.cinema.model.dto.FilmDto;
import com.cinema.validator.typeValidator.NumberValidator;
import com.cinema.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class AddFilmValidator extends ModelValidator<FilmDto> {

    public AddFilmValidator() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator descriptionValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong description");
        StringValidator descriptionEnglishValidator = new StringValidator(resourceBundle.getString("regexStringEnglish"), "Wrong english description");
        StringValidator nameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong name ");
        StringValidator nameEnglishValidator = new StringValidator(resourceBundle.getString("regexStringEnglish"), "Wrong english name");
        StringValidator releaseDateValidator = new StringValidator(resourceBundle.getString("regexNumber"), "Wrong release date");
        NumberValidator runningTimeValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong running time");

        validators.put(descriptionValidator, FilmDto::getDescription);
        validators.put(descriptionEnglishValidator, FilmDto::getDescriptionEnglish);
        validators.put(nameValidator, FilmDto::getName);
        validators.put(nameEnglishValidator, FilmDto::getNameEnglish);
        validators.put(releaseDateValidator, FilmDto::getReleaseDate);
        validators.put(runningTimeValidator, FilmDto::getRunningTime);

    }
}
