package com.example.int222_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private List<T> content;
    private boolean first;
    private boolean last;
    private  Integer totalPages;
    private  Integer totalElements;
    private Integer size;
    @JsonIgnore
    private Integer number;

    public Integer getPage(){
        return number;
    }

}