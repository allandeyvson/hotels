package br.com.hotels.domains;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa um reserva dentro da api.
 * @author allan
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
	
	@Id
	@GeneratedValue
	@Getter
	private Long id;
	
	@Getter @Setter
	private String numero;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String telefone;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	@JsonFormat(pattern = "YYYY-MM-dd")
	private LocalDate inicio;
	
	@Getter @Setter
	@JsonFormat(pattern = "YYYY-MM-dd")
	private LocalDate fim;
	
	@Getter @Setter
	@Enumerated(EnumType.STRING)
	private Hotel hotel;
	
	@Getter @Setter
	private String observacoes;
}
