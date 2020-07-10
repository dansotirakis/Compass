package com.example.compass.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.compass.api.model.Client;
import com.example.compass.api.repository.ClientRepository;

/**
 * Class client resources.
 * @author Damianos Sotirakis
 *
 */

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client atualizar(Long codigo, Client client) {
		Client clientSalva = encontrarPessoa(codigo);
		BeanUtils.copyProperties(client, clientSalva, "codigo");
		return clientRepository.save(clientSalva);
		
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Client clientSalva = encontrarPessoa(codigo);
		clientSalva.setAtivo(ativo);
		clientRepository.save(clientSalva);
	}
	
	public Client encontrarPessoa(Long codigo) {
		Client clientSalva = clientRepository.findOne(codigo);
		if (clientSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clientSalva;
	}
}
