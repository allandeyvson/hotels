package br.com.hotels.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;

/**
 * Interface que prove metodos para manipulacao de reservas
 * @author allan
 *
 */
public interface ReservaService {
	
	Reserva save(Reserva reserva);
	
	void deleteById(Long idReserva);
	
	Optional<Reserva> findById(Long idReserva);
	
	Optional<Reserva> findByNumeroReserva(String numero);
	
	Optional<List<Reserva>> findByHotel(Hotel hotel);
	
	List<Reserva> findAll();
	
	Page<Reserva> findBetweenDates(LocalDate inicio, LocalDate fim, Pageable pageable);
}
