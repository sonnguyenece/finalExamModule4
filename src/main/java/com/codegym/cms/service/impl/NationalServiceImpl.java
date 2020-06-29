package com.codegym.cms.service.impl;

import com.codegym.cms.model.National;
import com.codegym.cms.repository.NationalRepository;
import com.codegym.cms.repository.ProvinceRepository;
import com.codegym.cms.service.NationalService;
import org.springframework.beans.factory.annotation.Autowired;

public class NationalServiceImpl implements NationalService {

    @Autowired
    private NationalRepository nationalRepository;

    @Override
    public Iterable<National> findAll() {
        return nationalRepository.findAll();
    }

    @Override
    public National findById(Long id) {
        return nationalRepository.findOne(id);
    }

    @Override
    public void save(National national) {
        nationalRepository.save(national);
    }

    @Override
    public void remove(Long id) {
        nationalRepository.delete(id);
    }

    @Override
    public National findByName(String name) {
        return nationalRepository.findAllByName(name);
    }
}
