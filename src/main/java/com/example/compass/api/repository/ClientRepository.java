package com.example.compass.api.repository;

import com.example.compass.api.model.City;
import com.example.compass.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Class cidade repository.
 * @author Damianos Sotirakis
 *
 */

public interface ClientRepository extends JpaRepository<Client, Long>{

  @Query(name = "findCityByName", value = "SELECT p FROM Client p WHERE p.nome LIKE "
      + "%:nome%")
  Client findClientByName(@Param("nome") String nome);
}
