package com.example.compass.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/* 
 *	@author Damianos Sotirakis
 * 
 * Classe criada para implementação dos Eventos do tratamento de operações para criação dos recursos
 * 
 * */

public class RecursoCriadoEvent  extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long codigo;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.codigo = codigo;
		this.response = response;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}

}
