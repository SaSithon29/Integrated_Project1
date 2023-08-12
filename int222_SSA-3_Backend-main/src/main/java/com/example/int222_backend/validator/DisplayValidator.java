package com.example.int222_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.int222_backend.dtosAdmin.AnnouncementAdminMapDto;
import com.example.int222_backend.utils.AnnouncementDisplay;

public class DisplayValidator implements ConstraintValidator<YN, AnnouncementDisplay> {

    @Override
    public void initialize(YN annotation) {
    }

    @Override
    public boolean isValid(AnnouncementDisplay value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return "Y".equals(value) || "N".equals(value);
    }
}



