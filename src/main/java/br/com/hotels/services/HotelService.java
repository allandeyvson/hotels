package br.com.hotels.services;

import java.time.LocalDate;
import java.util.List;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.TipoCliente;

/**
 * Interface que prove metodos para manipulacao de hoteis 
 * @author allan
 *
 */
public interface HotelService {
	/**
	 * Com base nas datas passadas deverá retornar o hotel disponível mais barato e em caso de empate, o hotel com a maior classificação deverá ser retornado.
	 * @param datas
	 * @param tipoCliente
	 * @return
	 */
	Hotel cheapest(List<LocalDate> datas, TipoCliente tipoCliente);

}
