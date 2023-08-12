package com.example.int222_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.example.int222_backend.entities.Category;
import com.example.int222_backend.utils.AnnounceFormatDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDetailDto {
    private Integer Id;
    private String announcementTitle;
    private String announcementDescription;
    private String publishDate;
    private String closeDate;
    private String announcementDisplay;

    @JsonIgnore //รีเทิรนแบบเจสัน -กำหนดให้ไม่ให้แสดง -19
    private Category category;

    public String getAnnouncementCategory() {
//        ให้ดึง CategoryName มารีเทิร์น
        return  category.getCategoryName();
    }

    //// set format T-----Z
    public String getPublishDate(){
        return AnnounceFormatDate.sqlToJson(publishDate);
    }

    public String getCloseDate(){
        return AnnounceFormatDate.sqlToJson(closeDate);
    }
}
