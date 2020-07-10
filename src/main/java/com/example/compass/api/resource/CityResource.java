package com.example.compass.api.resource;

import java.util.List;

import com.example.compass.api.event.RecursoCriadoEvent;
import com.example.compass.api.model.City;
import com.example.compass.api.model.Estado;
import com.example.compass.api.repository.CityRepository;
import com.example.compass.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Class cidade resources.
 * @author Damianos Sotirakis
 *
 */

@RestController
@RequestMapping("/city")
public class CityResource {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  @Autowired
  private CityService cityService;

  @GetMapping
  public List<City> listar() {
    return cityRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<City> criar(@Valid @RequestBody City city, HttpServletResponse response) {
    City citySalva = cityRepository.save(city);
    publisher.publishEvent(new RecursoCriadoEvent(this, response, citySalva.getCodigo()));
    return ResponseEntity.status(HttpStatus.CREATED).body(citySalva);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<City> buscarPeloCodigo(@PathVariable Long codigo) {
    City city = cityRepository.findOne(codigo);
    return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
  }

  @GetMapping("/name/{nome}")
  public ResponseEntity<City> buscarPeloNome(@PathVariable String nome) {
    City city = cityRepository.findByCityByName(nome);
    return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
  }

//  @GetMapping("/estado/{estado}")
//  public List<City> buscaPorEstado(@PathVariable Estado estado) {
//    return cityRepository.findCityByEstado(estado);
//  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long codigo) {
    cityRepository.delete(codigo);
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<City> atualizar(@PathVariable Long codigo, @Valid @RequestBody City city) {
    City citySalva = cityService.atualizar(codigo, city);
    return ResponseEntity.ok(citySalva);
  }

}
