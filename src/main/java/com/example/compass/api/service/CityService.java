package com.example.compass.api.service;

import com.example.compass.api.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.compass.api.repository.CityRepository;

/**
 * Class cidade resources.
 * @author Damianos Sotirakis
 *
 */

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public City atualizar(Long codigo, City city) {
		City citySalva = cityRepository.findOne(codigo);
		if (citySalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(city, citySalva, "codigo");
		return cityRepository.save(citySalva);
		
	}
}
