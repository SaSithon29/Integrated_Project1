package com.example.int222_backend.services;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.*;
import com.example.int222_backend.dtos.PageDTO;
import com.example.int222_backend.dtosAdmin.*;
import com.example.int222_backend.entities.Announcement;
import com.example.int222_backend.entities.Category;
import com.example.int222_backend.repositories.*;
import com.example.int222_backend.utils.ListMapper;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;

    public List<AnnouncementsDto> getAllAnnouncements() {
        Sort sort = Sort.by("id").descending(); // เรียงsortใหม่

        List<Announcement> announcements = announcementRepository.findAll(sort);
        //แสดงหลายออพเจค
//        เอา announcement ใน list แต่ละตัว(stream เข้าถึงทีละตัว,announcement1 ออบเจคหนึ่ง)ไปแมพ กับรูปแบบ Dto(AnnouncementsDto)
        return announcements.stream().map(announcement1 -> modelMapper.map(announcement1, AnnouncementsDto.class)).collect(Collectors.toList());
    }

    public AnnouncementAdminDetailDto getOneAnnouncement(Integer id) {
//        get id match
        Announcement announcement = announcementRepository.findById(id).orElseThrow(
                //เก็บ announcement ที่เจอไอดี
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id" + id + "does not exist"));
//        แปลงจาก announcement ปกติด้วย modelMapper
        return modelMapper.map(announcement, AnnouncementAdminDetailDto.class);

    }

    //
    public AnnouncementAdminMapDetailDto createAnnouncement(AnnouncementAdminMapDto announcementMapDto) {
        Category category = categoryService.getCategoryById(announcementMapDto.getCategoryId());
        //ได้เป็นobjectที่มีอยู่แล้วของcategoryมา
        Announcement newannouncement = new Announcement(); //new object Announcement
        newannouncement.setAnnouncementTitle(announcementMapDto.getAnnouncementTitle());
        newannouncement.setAnnouncementDescription(announcementMapDto.getAnnouncementDescription());
        newannouncement.setPublishDate(announcementMapDto.getPublishDate());
        newannouncement.setCloseDate(announcementMapDto.getCloseDate());
        newannouncement.setAnnouncementDisplay(announcementMapDto.getAnnouncementDisplay());
        newannouncement.setCategory(category); //

        try {
            return modelMapper.map(announcementRepository.saveAndFlush(newannouncement), AnnouncementAdminMapDetailDto.class);

        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
//  announcementRepository.saveAndFlush(newannouncement);//save in Announcement entity,but we won't want show id

    }
    //ไม่ได้แมพมาในแบบปกติของอเนาเมน

    //
    public AnnouncementAdminMapDetailDto updateAnnouncement(Integer id, AnnouncementAdminMapDto announcement) {

        Announcement updateAnnouncement = announcementRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id" + id + "does not exist"));
        updateAnnouncement.setAnnouncementTitle(announcement.getAnnouncementTitle());
        updateAnnouncement.setAnnouncementDescription(announcement.getAnnouncementDescription());
        updateAnnouncement.setPublishDate(announcement.getPublishDate());
        updateAnnouncement.setCloseDate(announcement.getCloseDate());
        updateAnnouncement.setAnnouncementDisplay(announcement.getAnnouncementDisplay());
        updateAnnouncement.setCategory(categoryService.getCategoryById(announcement.getCategoryId()));

        return modelMapper.map(announcementRepository.saveAndFlush(updateAnnouncement), AnnouncementAdminMapDetailDto.class);

    }


    public void deleteAnnouncement(Integer id) {

        if (announcementRepository.existsById(id)) {
            announcementRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement id" + id + "does not exist");
        }
    }

    ////                                                            ////
    public List<?> getAnnouncementsByMode(String mode) {
        if ("active".equals(mode)) {
            return getActiveAnnouncements();
        } else if ("close".equals(mode)) {
            return getClosedAnnouncements();
        } else if ("admin".equals(mode)) {
            return getAllAnnouncements();
        } else {
//            return getAllAnnouncements();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Announcements Mode" + mode + "Not Found");
        }
        // Handle invalid mode or other cases
//        return Collections.emptyList();
    }

    private List<AnnouncementsDto> getActiveAnnouncements() {
        Sort sort = Sort.by("id").descending();
        ZonedDateTime currentDate = ZonedDateTime.now();
        List<Announcement> activeAnnouncements = announcementRepository.findActive(currentDate);

        return activeAnnouncements.stream().map(announcement1 -> modelMapper.map(announcement1, AnnouncementsDto.class)).collect(Collectors.toList());

    }

    private List<AnnouncementsDto> getClosedAnnouncements() {
        Sort sort = Sort.by("id").descending();
        ZonedDateTime currentDate = ZonedDateTime.now();
        List<Announcement> closedAnnouncements = announcementRepository.findClose(currentDate);

        return closedAnnouncements.stream().map(announcement1 -> modelMapper.map(announcement1, AnnouncementsDto.class)).collect(Collectors.toList());

    }


    public PageDTO<AnnouncementsDto> getPageAnnouncements(int page, int size, String sortBy, String mode) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        ZonedDateTime currentTime = ZonedDateTime.now();
        if ("active".equals(mode)) {
            return listMapper.toPageDTO(announcementRepository.findActive(currentTime, pageable), AnnouncementsDto.class, modelMapper);
        } else if ("close".equals(mode)) {
            return listMapper.toPageDTO(announcementRepository.findClose(currentTime, pageable), AnnouncementsDto.class, modelMapper);
        } else if ("admin".equals(mode)) {
            return listMapper.toPageDTO(announcementRepository.findAll(pageable), AnnouncementsDto.class, modelMapper);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcements Mode" + mode + "Not Found");
        }
    }



    public PageDTO<AnnouncementsDto> getPageAnnouncementsFilterCategory(Integer page, Integer size, String sortBy, Integer categoryId, String mode) {
//        Integer categoryIdValue = Integer.valueOf((categoryId));
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        ZonedDateTime currentTime = ZonedDateTime.now();
        if ("admin".equals(mode)) {
            if (categoryId == 0) {
                return listMapper.toPageDTO(announcementRepository.findAll(pageable), AnnouncementsDto.class, modelMapper);
            } else {
                return listMapper.toPageDTO(announcementRepository.findAllByCategoryCategoryId(categoryId, pageable), AnnouncementsDto.class, modelMapper);
            }
        } else if ("active".equals(mode)) {
            if (categoryId == 0) {
                return listMapper.toPageDTO(announcementRepository.findActive(currentTime, pageable), AnnouncementsDto.class, modelMapper);
            } else {
                return listMapper.toPageDTO(announcementRepository.findActive(currentTime, categoryId, pageable), AnnouncementsDto.class, modelMapper);
            }
        } else if ("close".equals(mode)) {
            if (categoryId == 0) {
                return listMapper.toPageDTO(announcementRepository.findClose(currentTime, pageable), AnnouncementsDto.class, modelMapper);
            } else {
                return listMapper.toPageDTO(announcementRepository.findClose(currentTime, categoryId, pageable), AnnouncementsDto.class, modelMapper);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcements Mode" + mode + "Not Found");
        }
    }
}
