package com.example.int222_backend.dtosAdmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.example.int222_backend.entities.Category;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementsDto {
    private Integer id;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
    @JsonIgnore //รีเทิรนแบบเจสัน -กำหนดให้ไม่ให้แสดง -19
    private Category category;

    public String getAnnouncementCategory() {
//        ให้ดึง CategoryName มารีเทิร์น
        return  category.getCategoryName();
    }
}
