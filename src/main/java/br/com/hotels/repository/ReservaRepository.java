package br.com.hotels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;
/**
 * Repositorio que implementa um repositorio de dados para Reservas, contendo métodos para CRUD e buscas customizadas.
 * @author allan
 *
 */
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	
	public Optional<Reserva> findByNumero(String numero);
	
	public Optional<List<Reserva>> findByHotel(Hotel hotel);

}
