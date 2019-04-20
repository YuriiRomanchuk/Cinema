package com.cinema.validator;

import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.validator.typeValidator.NumberValidator;

import java.util.ResourceBundle;

public class AddRoomPlaceValidator extends ModelValidator<RoomPlaceDto> {

    public AddRoomPlaceValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        NumberValidator placeValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong place");
        NumberValidator rowValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong row");
        validators.put(placeValidator, RoomPlaceDto::getPlace);
        validators.put(rowValidator, RoomPlaceDto::getRow);
    }
}
