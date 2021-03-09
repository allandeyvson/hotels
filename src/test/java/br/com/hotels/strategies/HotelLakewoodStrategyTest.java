package br.com.hotels.strategies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.hotels.domains.TipoCliente;

class HotelLakewoodStrategyTest {
	
	public HotelLakewoodStrategy hotel;
	
	@BeforeEach
	public void setUp() {
		hotel = new HotelLakewoodStrategy();
	}

	@Test
	public void deveRetornar110ParaDiaDeSemanaAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 26));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(110, valor);
	}
	
	@Test
	public void deveRetornar90ParaSabadoAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(90, valor);
	}
	
	@Test
	public void deveRetornar80ParaSabadoAndClienteRewardTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(80, valor);
	}
	
	@Test
	public void deveRetornar90ParaDomingoAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(90, valor);
	}
	
	@Test
	public void deveRetornar80ParaDomingoAndClienteRewarTestd() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(80, valor);
	}
	
	@Test
	public void deveRetornar180FinalDeSemanaAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27), LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(180, valor);
	}
	
	@Test
	public void deveRetornar160FinalDeSemanaAndClienteRewardTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27), LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(160, valor);
	}
	
	
	

}
