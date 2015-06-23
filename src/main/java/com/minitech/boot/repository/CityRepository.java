package com.minitech.boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.minitech.boot.entity.City;


@Repository
@EnableJpaRepositories
public interface CityRepository extends JpaRepository<City, Long> {

	Page<City> findAll(Pageable pageable);
}
