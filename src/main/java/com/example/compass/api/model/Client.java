package com.example.compass.api.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class client.
 * @author Damianos Sotirakis
 *
 */

@Data
@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String nome;

	@Enumerated
	private Sexo sexo;

	private LocalDate nascimento;

	private int idade;

	private Boolean ativo;

	@ManyToOne
	private City city;

}
