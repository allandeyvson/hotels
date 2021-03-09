package br.com.hotels.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;
import br.com.hotels.repository.ReservaRepository;
import br.com.hotels.services.ReservaService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ReservaServiceImplTest {
	
	
	ReservaService reservaService;
	
	@MockBean
	private ReservaRepository reservaRepository;
	
	private Reserva mockReserva;
	
	@BeforeAll
	private void setUp(){		
		reservaService = new ReservaServiceImpl(reservaRepository);		
		mockReserva = new Reserva(1L, "12345", "mock", "84999990000", "mock@mock.com", 
				LocalDate.now(), LocalDate.now(), Hotel.BRIDGEWOOD, "mock observações");
	}
	
	@Test
	void deveSalvarReservaTest() {
		BDDMockito.given(reservaRepository.save(Mockito.any(Reserva.class))).willReturn(mockReserva);
		
		Reserva reservaBD = reservaService.save(new Reserva());
		assertNotNull(reservaBD);
		assertNotNull(reservaBD.getId());
		assertEquals("12345", reservaBD.getNumero());
	}
	
	@Test
	void deveBuscarReservaPorNumeroTest() {
		BDDMockito.given(reservaRepository.findByNumero(Mockito.anyString())).willReturn(Optional.of(new Reserva()));
		
		Optional<Reserva> reservaBD = reservaService.findByNumeroReserva("12345");
		assertTrue(reservaBD.isPresent());;
	}
	
	@Test
	void deveBuscarReservaPorHotelTest() {
		BDDMockito.given(reservaRepository.findByHotel(Mockito.any(Hotel.class)))
			.willReturn(Optional.of(Arrays.asList(new Reserva())));
		
		Optional<List<Reserva>> reservasBD = reservaService.findByHotel(Hotel.BRIDGEWOOD);
		assertTrue(reservasBD.isPresent());
		assertNotNull(reservasBD.get().get(0));
	}

}
