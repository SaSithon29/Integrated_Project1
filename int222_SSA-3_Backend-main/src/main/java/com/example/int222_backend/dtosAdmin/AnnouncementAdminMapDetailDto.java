package com.example.int222_backend.dtosAdmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.int222_backend.entities.Category;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//show data ที่รับเข้ามาแล้วถูกจัดฟอร์แมทตาม AnnouncementMapDto
public class AnnouncementAdminMapDetailDto {
    private Integer Id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;

    @JsonIgnore //รีเทิรนแบบเจสัน -กำหนดให้ไม่ให้แสดง -24 แสดง 26 30
    private Category category;

    public String getAnnouncementCategory() {
//        ให้ดึง CategoryId มารีเทิร์น
        return  category.getCategoryName();
    }
    public Integer getCategoryId() {
//        ให้ดึง CategoryId มารีเทิร์น
        return  category.getCategoryId();
    }
}
