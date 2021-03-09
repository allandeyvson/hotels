package br.com.hotels.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.TipoCliente;
import br.com.hotels.services.HotelService;

@TestInstance(Lifecycle.PER_CLASS)
class HotelServiceImplTest {
	
	public HotelService hotelService;
	
	@BeforeAll
	public void setUp() {
		hotelService = new HotelServiceImpl();
	}
	
	@Test
	void deveRetornarLakewoodParaEntrada01Test() {	
		List<LocalDate> entrada01 = Arrays.asList(LocalDate.of(2009, 03, 16), LocalDate.of(2009, 03, 17), LocalDate.of(2009, 03, 18));		
		assertEquals(Hotel.LAKEWOOD, hotelService.cheapest(entrada01, TipoCliente.REGULAR));
	}
	
	@Test
	void deveRetornarBridgewoodParaEntrada02Test() {	
		List<LocalDate> entrada02 = Arrays.asList(LocalDate.of(2009, 03, 20), LocalDate.of(2009, 03, 21), LocalDate.of(2009, 03, 22));		
		assertEquals(Hotel.BRIDGEWOOD, hotelService.cheapest(entrada02, TipoCliente.REGULAR));
	}
	
	@Test
	void deveRetornarRigewoodParaEntrada03Test() {	
		List<LocalDate> entrada03 = Arrays.asList(LocalDate.of(2009, 03, 26), LocalDate.of(2009, 03, 27), LocalDate.of(2009, 03, 28));		
		assertEquals(Hotel.RIDGEWOOD, hotelService.cheapest(entrada03, TipoCliente.REWARD));
	}

}
