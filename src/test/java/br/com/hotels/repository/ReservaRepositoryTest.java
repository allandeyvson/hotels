package br.com.hotels.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;

@SpringBootTest
@ActiveProfiles("dev")
@TestInstance(Lifecycle.PER_CLASS)
class ReservaRepositoryTest {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@BeforeAll
	private void setUp() {
		Reserva reserva01 = new Reserva(null, "54321", "Allan", "84998290576", "allan.araujo@hotmail.com.br", 
				LocalDate.of(2021, 03, 9), LocalDate.of(2021, 03, 11), Hotel.LAKEWOOD, "sem observações");
		reservaRepository.save(reserva01);
	}

	@Test
	void deveSalvarReservaTest() {
		Reserva reserva02 = new Reserva(null, "12345", "Allan", "84998290576", "allan.araujo@hotmail.com.br", 
				LocalDate.of(2021, 03, 9), LocalDate.of(2021, 03, 11), Hotel.BRIDGEWOOD, "sem observações");
		
		Reserva reservaBd = reservaRepository.save(reserva02);
		assertNotNull(reservaBd);
		assertNotNull(reservaBd.getId());

	}
	
	@Test
	void deveBuscarReservaPorNumberoTest() {		
		Optional<Reserva> reserva01BD = reservaRepository.findByNumero("54321");
		assertTrue(reserva01BD.isPresent());
	}
	
	@Test
	void deveBuscarReservarPorHotelTest() {		
		Optional<List<Reserva>> reserva01BD = reservaRepository.findByHotel(Hotel.LAKEWOOD);
		assertTrue(reserva01BD.isPresent());
	}

}
