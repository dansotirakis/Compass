package com.example.compass.api.repository;

import com.example.compass.api.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Class cidade repository.
 * @author Damianos Sotirakis
 *
 */

public interface CityRepository extends JpaRepository<City, Long> {

  @Query(name = "findCityByName", value = "SELECT p FROM City p WHERE p.nome LIKE "
      + "%:nome%")
  City findByCityByName(@Param("nome") String nome);

//  @Query(name = "findCityByName", value = "SELECT p FROM City p WHERE p.estado LIKE"
//      + "%:estado%")
//  List<City> findCityByEstado(@Param("estado") Estado estado);

}
