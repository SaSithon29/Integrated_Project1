package com.example.int222_backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.int222_backend.dtosAdmin.CategoryAdminMapDetailDto;
import com.example.int222_backend.dtosAdmin.CategoryAdminMapDto;
import com.example.int222_backend.entities.Category;
import com.example.int222_backend.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id" + id + "does not exist"));
    }

    public CategoryAdminMapDetailDto createCategory(CategoryAdminMapDto categoryAdminMapDto) {
        Category newcategory = new Category(); //new object Category
        newcategory.setCategoryName(categoryAdminMapDto.getCategoryName());

        return modelMapper.map(categoryRepository.saveAndFlush(newcategory), CategoryAdminMapDetailDto.class);
    }

}
