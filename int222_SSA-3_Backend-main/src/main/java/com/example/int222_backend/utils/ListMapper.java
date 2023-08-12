package com.example.int222_backend.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import com.example.int222_backend.dtos.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {

        private static final ListMapper listMapper = new ListMapper();

        private ListMapper() {
        }

        public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
            return source.stream().map(entity -> modelMapper.map(entity, targetClass)).collect(Collectors.toList());
        }

        public static ListMapper getInstance() {
            return listMapper;
        }

    public <S,T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        PageDTO<T> pageDto = modelMapper.map(source, PageDTO.class);
        pageDto.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return pageDto;
    }
}


