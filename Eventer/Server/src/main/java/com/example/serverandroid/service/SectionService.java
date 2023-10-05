package com.example.serverandroid.service;

import com.example.serverandroid.entity.Section;

import java.util.List;

public interface SectionService {
    List<Section> getAllSections();

    Section saveOrUpdateSection(Section section);

    Section getSectionById(int id);

    void deleteSectionById(int id);
}
