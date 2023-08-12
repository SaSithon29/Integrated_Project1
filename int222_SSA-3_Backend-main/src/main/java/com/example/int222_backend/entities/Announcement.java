package com.example.int222_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;
import com.example.int222_backend.utils.AnnouncementDisplay;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generaId
    @Column(name = "announcementid")
    private Integer id;
    //-----------------------------------------------------------------------------------------------------------------//
    @Column(name = "announcementtitle", nullable = false)
    private String announcementTitle;
    //-----------------------------------------------------------------------------------------------------------------//
    @Column(name = "announcementdescription", nullable = false)
    private String announcementDescription;
    //-----------------------------------------------------------------------------------------------------------------//
    @Column(name = "publishdate")
    private ZonedDateTime publishDate;
    //-----------------------------------------------------------------------------------------------------------------//
    @Column(name = "closedate")
    private ZonedDateTime closeDate;

    //-----------------------------------------------------------------------------------------------------------------//
    @Column(name = "announcementdisplay")
    @Enumerated(EnumType.STRING)
    private AnnouncementDisplay announcementDisplay;
    //-----------------------------------------------------------------------------------------------------------------//
    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;

}
