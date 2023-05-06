package com.example.serverandroid.service;

import com.example.serverandroid.entity.Section;
import com.example.serverandroid.entity.User;
import com.example.serverandroid.repository.SectionRepository;
import com.example.serverandroid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    @Transactional
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    @Transactional
    public Section saveOrUpdateSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    @Transactional
    public Section getSectionById(int id) {
        return sectionRepository.findById(id).orElse(null);

    }

    @Override
    @Transactional
    public void deleteSectionById(int id) {
        sectionRepository.deleteById(id);

    }
}
