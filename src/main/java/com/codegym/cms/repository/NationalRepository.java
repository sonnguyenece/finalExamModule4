package com.codegym.cms.repository;

import com.codegym.cms.model.National;
import com.codegym.cms.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NationalRepository extends PagingAndSortingRepository<National, Long> {
    National findAllByName(String name);
}
