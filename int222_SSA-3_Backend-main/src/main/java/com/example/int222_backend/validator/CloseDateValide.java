package com.example.int222_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.int222_backend.dtosAdmin.AnnouncementAdminMapDto;

public class CloseDateValide implements ConstraintValidator<ValidDate, AnnouncementAdminMapDto> {
    @Override
    public boolean isValid(AnnouncementAdminMapDto AdminMapDto, ConstraintValidatorContext context) {
        if (AdminMapDto.getCloseDate() == null || AdminMapDto.getPublishDate() == null) {
            return true;
        }
//        else return AdminMapDto.getCloseDate().isAfter(AdminMapDto.getPublishDate());
        boolean isValid = AdminMapDto.getCloseDate().isAfter(AdminMapDto.getPublishDate());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("closeDate").addConstraintViolation();
        }
        return isValid;

    }
}
