package com.cinema.validator;

import com.cinema.model.dto.RoomDto;
import com.cinema.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

public class AddRoomValidator extends ModelValidator<RoomDto> {

    public AddRoomValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator nameValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong name");
        StringValidator nameEnglishValidator = new StringValidator(resourceBundle.getString("regexStringNumberEnglish"), "Wrong english name");
        validators.put(nameValidator, RoomDto::getName);
        validators.put(nameEnglishValidator, RoomDto::getNameEnglish);
    }
}
