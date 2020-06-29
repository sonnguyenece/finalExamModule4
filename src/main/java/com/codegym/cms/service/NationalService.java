package com.codegym.cms.service;

import com.codegym.cms.model.National;
import com.codegym.cms.model.Province;

public interface NationalService {

        Iterable<National> findAll();

        National findById(Long id);

        void save(National national);

        void remove(Long id);

        National findByName(String name);
}
