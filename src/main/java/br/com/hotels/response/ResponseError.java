package br.com.hotels.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que implementa um response generico para mapear errors na api.
 * @author allan
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseError {
	
	private String error;
	
	private LocalDateTime hora;
}
