package com.example.int222_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generaId
    @Column(name = "categoryid")
    private Integer categoryId;
    @Column(name = "categoryname")
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Announcement> announcements;
}
