package com.minitech.boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.minitech.boot.eneity.City;

public interface CityRepository extends Repository<City, Long> {

	Page<City> findAll(Pageable pageable);
}
