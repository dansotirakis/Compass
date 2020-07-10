package com.example.compass.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.compass.api.model.City;
import com.example.compass.api.model.Client;
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

import com.example.compass.api.event.RecursoCriadoEvent;
import com.example.compass.api.repository.ClientRepository;
import com.example.compass.api.service.ClientService;

/**
 * Class client resources.
 * @author Damianos Sotirakis
 *
 */

@RestController
@RequestMapping("/client")
public class ClientResource {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public List <Client> listar(){
		return clientRepository.findAll();
	}
	
	@PostMapping
	public Long criar(@Valid @RequestBody Client client, HttpServletResponse response){
		Client clientSalva = clientRepository.save(client);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clientSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clientSalva).getBody().getCodigo();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Client> buscaPeloCodigo(@PathVariable Long codigo){
		Client client = clientRepository.findOne(codigo);
		return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
	}

	@GetMapping("/name/{nome}")
	public ResponseEntity<Client> buscarPeloNome(@PathVariable String nome) {
		Client client = clientRepository.findClientByName(nome);
		return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable Long codigo) {
		clientRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Client> atualizar(@PathVariable Long codigo, @Valid @RequestBody Client client){
		Client clientSalva = clientService.atualizar(codigo, client);
		return ResponseEntity.ok(clientSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		clientService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
	
}
