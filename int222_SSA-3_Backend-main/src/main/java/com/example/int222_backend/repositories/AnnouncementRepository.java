package com.example.int222_backend.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.int222_backend.entities.Announcement;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Repository //1.ลืมไม

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    Page<Announcement> findAllByCategoryCategoryId(Integer categoryId, Pageable pageable);
                        //2.ชื่อผิด find all announcement ที่มี field categoryId of category ตรงกับที่รับมา

    @Query("SELECT a FROM Announcement a WHERE "
            + "a.announcementDisplay = 'Y' AND ("
            + "(a.publishDate IS NULL AND a.closeDate IS NULL) "
            + "OR (a.publishDate IS NULL AND a.closeDate >= :now) "
            + "OR (a.publishDate <= :now AND a.closeDate IS NULL) "
            + "OR (a.publishDate <= :now AND a.closeDate >= :now)) "
            + "ORDER BY a.id DESC ")
    List<Announcement> findActive(ZonedDateTime now);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = 'Y' AND (a.closeDate <= :now) ORDER BY a.id DESC ")
    List<Announcement> findClose(ZonedDateTime now);


    @Query("SELECT a FROM Announcement a WHERE "
            + "a.announcementDisplay = 'Y' AND ("
            + "(a.publishDate IS NULL AND a.closeDate IS NULL) "
            + "OR (a.publishDate IS NULL AND a.closeDate >= :now) "
            + "OR (a.publishDate <= :now AND a.closeDate IS NULL) "
            + "OR (a.publishDate <= :now AND a.closeDate >= :now)) "
            + "ORDER BY a.id DESC ")
    Page<Announcement> findActive(ZonedDateTime now, Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.announcementDisplay = 'Y' AND (a.closeDate <= :now) ORDER BY a.id DESC ")
    Page<Announcement> findClose(ZonedDateTime now, Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.category.categoryId = :categoryId AND "
            + "a.announcementDisplay = 'Y' AND ("
            + "(a.publishDate IS NULL AND a.closeDate IS NULL) "
            + "OR (a.publishDate IS NULL AND a.closeDate >= :now) "
            + "OR (a.publishDate <= :now AND a.closeDate IS NULL) "
            + "OR (a.publishDate <= :now AND a.closeDate >= :now)) "
            + "ORDER BY a.id DESC ")
    Page<Announcement> findActive(ZonedDateTime now, Integer categoryId, Pageable pageable);

    @Query("SELECT a FROM Announcement a WHERE a.category.categoryId=:categoryId AND "
            + "a.announcementDisplay = 'Y' AND (a.closeDate <= :now) ORDER BY a.id DESC ")
    Page<Announcement> findClose(ZonedDateTime now, Integer categoryId, Pageable pageable);

}

