package com.example.int222_backend.dtosAdmin;

import jakarta.annotation.Nullable;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Category;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import com.example.int222_backend.utils.AnnouncementDisplay;
import com.example.int222_backend.validator.CategoryIdExists;
import com.example.int222_backend.validator.ValidDate;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ValidDate
//ดึง format  อาจารย์มาเช็ค
//ตัวที่ map เข้ามาในแบค
@Validated
public class AnnouncementAdminMapDto {
    //    private Integer Id;

    @NotBlank
    @NotNull(message = "must not be null")
    @Size(min = 1, max = 200, message = "size must be between 1 and 200")
    private String announcementTitle;
    //-----------------------------------------------------------------------------------------------------------------//
    @NotBlank
    @NotNull(message = "must not be null")
    @Size(min = 1, max = 10000, message = "size must be between 1 and 10000")
    private String announcementDescription;
    //-----------------------------------------------------------------------------------------------------------------//
    @FutureOrPresent(message = " must be a date in the present or in the future")
    private ZonedDateTime publishDate;
    //-----------------------------------------------------------------------------------------------------------------//
    @Future(message = "must be a future date")
    private ZonedDateTime closeDate;

    //-----------------------------------------------------------------------------------------------------------------//
    @Nullable
    private AnnouncementDisplay announcementDisplay;
    //-----------------------------------------------------------------------------------------------------------------//
    @NotNull(message = "must not be null")
    @CategoryIdExists
    private Integer categoryId;




}
