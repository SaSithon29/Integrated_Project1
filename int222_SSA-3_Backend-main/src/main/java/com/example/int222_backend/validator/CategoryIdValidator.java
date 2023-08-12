package com.example.int222_backend.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.int222_backend.repositories.CategoryRepository;

public class CategoryIdValidator implements ConstraintValidator<CategoryIdExists,Integer> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(Integer categoryId, ConstraintValidatorContext context){
        if(categoryId==null){
            return true;
        }
        return categoryRepository.existsById(categoryId);
    }
}
