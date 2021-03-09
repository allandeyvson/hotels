package br.com.hotels.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;
import br.com.hotels.repository.ReservaRepository;
import br.com.hotels.services.ReservaService;

/**
 * Classe que implementa os métodos do servico de reserva
 * @author allan
 *
 */
@Service
public class ReservaServiceImpl implements ReservaService{
	
	private ReservaRepository reservaRepository;
	
	@Autowired
	public ReservaServiceImpl(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	@Override
	public Reserva save(Reserva reserva) {
		reserva.setNumero(String.valueOf(System.currentTimeMillis()));
		return reservaRepository.save(reserva);
	}

	@Override
	public void deleteById(Long idReserva) {
		reservaRepository.deleteById(idReserva);
	}

	@Override
	public Optional<Reserva> findById(Long idReserva) {
		return reservaRepository.findById(idReserva);
	}

	@Override
	public Optional<Reserva> findByNumeroReserva(String numero) {
		return reservaRepository.findByNumero(numero);
	}

	@Override
	public Optional<List<Reserva>> findByHotel(Hotel hotel) {
		return reservaRepository.findByHotel(hotel);
	}
	
	@Override
	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}

	@Override
	public Page<Reserva> findBetweenDates(LocalDate inicio, LocalDate fim, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


}
