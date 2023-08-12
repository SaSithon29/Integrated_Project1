package com.example.int222_backend;

import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import com.example.int222_backend.utils.ListMapper;


@org.springframework.context.annotation.Configuration //บอกหน้าที่
public class Configuration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ListMapper listMapper(){
        return ListMapper.getInstance();
    }
}
