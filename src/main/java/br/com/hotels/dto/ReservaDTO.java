package br.com.hotels.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que implementa um DTO para uma reserva
 * @author allan
 *
 */
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

	private String numero;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	@NotNull(message = "O campo inicio nao pode ser vazio.")
	private LocalDate inicio;
	
	@NotNull(message = "O campo fim nao pode ser vazio.")
	private LocalDate fim;
	
	private Hotel hotel;
	
	private String observacoes;
	
	public Reserva convertDTOtoEntity() {
		return new Reserva(null, this.numero, this.nome, this.telefone, this.email, this.inicio, this.fim, this.hotel,
				this.observacoes);
	}
}
