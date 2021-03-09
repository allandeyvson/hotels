package br.com.hotels.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Classe que implementa um response generico para a api.
 * @author allan
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

	private T data;
	
	private List<ResponseError> errors = new ArrayList<ResponseError>();
	
	public void addErrors(String msgError) {
		ResponseError error = new ResponseError();
		error.setError(msgError);
		error.setHora(LocalDateTime.now());
		
		getErrors().add(error);
	}
}
