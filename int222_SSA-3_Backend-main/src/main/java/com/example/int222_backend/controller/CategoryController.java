package com.example.int222_backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.int222_backend.dtosAdmin.CategoryAdminMapDetailDto;
import com.example.int222_backend.dtosAdmin.CategoryAdminMapDto;
import com.example.int222_backend.entities.Category;
import com.example.int222_backend.services.CategoryService;

import java.util.List;

@RestController //เอาไว้คุยกับ front
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("")
    public List<Category> getCategories(){
        return service.getCategories();
    }

    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return service.getCategoryById(id);
    }


    @PostMapping("")
    public CategoryAdminMapDetailDto createCategory(@RequestBody CategoryAdminMapDto categoryAdminMapDto){
        return service.createCategory(categoryAdminMapDto);
    }

}
