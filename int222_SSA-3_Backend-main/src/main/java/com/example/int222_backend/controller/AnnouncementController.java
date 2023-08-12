package com.example.int222_backend.controller;

import jakarta.validation.Valid;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.example.int222_backend.dtos.PageDTO;
import com.example.int222_backend.dtosAdmin.AnnouncementAdminDetailDto;
import com.example.int222_backend.dtosAdmin.AnnouncementAdminMapDetailDto;
import com.example.int222_backend.dtosAdmin.AnnouncementAdminMapDto;
import com.example.int222_backend.dtosAdmin.AnnouncementsDto;
import com.example.int222_backend.services.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin
public class AnnouncementController {
    @Autowired
    private AnnouncementService service;


//    @GetMapping("")
//    public List<AnnouncementsAdminDto> getAllAnnouncements() {
//        return service.getAllAnnouncements();
//    }

    @GetMapping("{id}")
    public AnnouncementAdminDetailDto getOneAnnouncements(@PathVariable Integer id) {
        return service.getOneAnnouncement(id);
    }

    @PostMapping("")
    public AnnouncementAdminMapDetailDto createAnnouncement(@Valid @RequestBody AnnouncementAdminMapDto announcementMapDto, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException((MethodParameter) null, bindingResult);
        }
        return service.createAnnouncement(announcementMapDto);
    }

    @PutMapping("/{id}")
    public AnnouncementAdminMapDetailDto updateAnnouncement(@PathVariable Integer id, @RequestBody AnnouncementAdminMapDto announcementMapDto) {
        return service.updateAnnouncement(id, announcementMapDto);
    }

    @DeleteMapping("{id}")
    public void deleteAnnouncement(@PathVariable Integer id) { //PathVariable รับค่าจาก Path มา
        service.deleteAnnouncement(id);
    }

//    @GetMapping("")
//    public

    @GetMapping("")
    public List<?> getAnnouncements(@RequestParam(value = "mode", defaultValue = "admin") String mode) {
        return service.getAnnouncementsByMode(mode);
    }

//    @GetMapping("/pages")
//    public PageDTO<AnnouncementsDto> getPageAnnouncements(@RequestParam(defaultValue = "0") Integer page,
//                                                          @RequestParam(defaultValue = "5") Integer size,
//                                                          @RequestParam(defaultValue = "id") String sortBy,
//                                                          @RequestParam(value = "mode", defaultValue = "admin") String mode) {
//        return service.getPageAnnouncements(page, size, sortBy, mode);
//    }

    @GetMapping("/pages")
    public PageDTO<AnnouncementsDto> getPageAnnouncementsFilterCategory(@RequestParam(defaultValue = "0") Integer page,
                                                                        @RequestParam(defaultValue = "5") Integer size,
                                                                        @RequestParam(defaultValue = "id") String sortBy,
                                                                        @RequestParam(defaultValue = "0") Integer category,
                                                                        @RequestParam(value = "mode", defaultValue = "admin") String mode) {
        return service.getPageAnnouncementsFilterCategory(page, size,sortBy, category, mode);
    }
}
   



