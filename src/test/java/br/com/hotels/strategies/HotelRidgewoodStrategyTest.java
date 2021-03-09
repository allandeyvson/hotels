package br.com.hotels.strategies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.hotels.domains.TipoCliente;

class HotelRidgewoodStrategyTest {
	
	public HotelRidgewoodStrategy hotel;
	
	@BeforeEach
	public void setUp() {
		hotel = new HotelRidgewoodStrategy();
	}

	@Test
	public void deveRetornar220ParaDiaDeSemanaAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 26));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(220, valor);
	}
	
	@Test
	public void deveRetornar150ParaSabadoAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(150, valor);
	}
	
	@Test
	public void deveRetornar40ParaSabadoAndClienteRewardTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(40, valor);
	}
	
	@Test
	public void deveRetornar150ParaDomingoAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(150, valor);
	}
	
	@Test
	public void deveRetornar40ParaDomingoAndClienteRewardTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(40, valor);
	}
	
	@Test
	public void deveRetornar300FinalDeSemanaAndClienteRegularTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27), LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(300, valor);
	}
	
	@Test
	public void deveRetornar80FinalDeSemanaAndClienteRewardTest() {
		
		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27), LocalDate.of(2021, 02, 28));
		
		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(80, valor);
	}
}
