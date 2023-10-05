package com.example.serverandroid.controller;

import com.example.serverandroid.entity.Section;
import com.example.serverandroid.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class SectionController {
    @Autowired
    private SectionService sectionService;


    @GetMapping("/sections")
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/section/{id}")
    public Section getSectionById(@PathVariable("id") int id) {
        return sectionService.getSectionById(id);
    }

    @PostMapping("/section")
    public Section addSection(@RequestBody Section section) {
        return sectionService.saveOrUpdateSection(section);
    }

    @PutMapping("/section")
    public void updateSection(@RequestBody Section section) {
        sectionService.saveOrUpdateSection(section);
    }

    @DeleteMapping("/section/{id}")
    public void deleteSection(@PathVariable int id) {
        sectionService.deleteSectionById(id);
    }


}
