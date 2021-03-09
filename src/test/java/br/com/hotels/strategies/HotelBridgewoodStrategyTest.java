package br.com.hotels.strategies;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.hotels.domains.TipoCliente;

@TestInstance(Lifecycle.PER_CLASS)
class HotelBridgewoodStrategyTest {

	public HotelBridgewoodStrategy hotel;

	@BeforeAll
	public void setUp() {
		hotel = new HotelBridgewoodStrategy();
	}

	@Test
	public void deveRetornar160ParaDiaDeSemanaAndClienteRegularTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 26));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(160, valor);
	}

	@Test
	public void deveRetornar60ParaSabadoAndClienteRegularTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(60, valor);
	}

	@Test
	public void deveRetornar50ParaSabadoAndClienteRewardTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(50, valor);
	}

	@Test
	public void deveRetornar60ParaDomingoAndClienteRegularTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 28));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(60, valor);
	}

	@Test
	public void deveRetornar50ParaDomingoAndClienteRewardTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 28));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(50, valor);
	}

	@Test
	public void deveRetornar120FinalDeSemanaAndClienteRegularTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27), LocalDate.of(2021, 02, 28));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REGULAR);
		assertEquals(120, valor);
	}

	@Test
	public void deveRetornar100FinalDeSemanaAndClienteRewardTest() {

		List<LocalDate> datas = Arrays.asList(LocalDate.of(2021, 02, 27), LocalDate.of(2021, 02, 28));

		Double valor = hotel.calculaCusto(datas, TipoCliente.REWARD);
		assertEquals(100, valor);
	}

}
