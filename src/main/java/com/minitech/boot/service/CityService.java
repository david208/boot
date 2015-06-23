package com.minitech.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minitech.boot.entity.City;
import com.minitech.boot.repository.CityRepository;

@Service
@Transactional
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public void addCity(int i) {
		City city = new City();
		city.setName("上海");
		cityRepository.save(city);
	}
	
	public  void findCity(int i) {
		City city = new City();
		city.setName("上海");
		cityRepository.save(city);
	}
	
}
