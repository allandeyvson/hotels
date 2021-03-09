package br.com.hotels.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.TipoCliente;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HotelControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Map<String, Object> montarEntradaTeste(List<LocalDate> dates, TipoCliente tipoCliente) {
		Map<String, Object> input01 = new HashMap<String, Object>();
		input01.put("tipoCliente", tipoCliente);
		input01.put("dates", dates.stream().map(d -> d.toString()).collect(Collectors.joining(",")));
		return input01;
	}
	
	/**
	 * Entrada 1: Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed) Saída 1: Lakewood 
	 */
	@Test
	void deveRetornarLakewoodParaEntrada01Test() {
		List<LocalDate> dates = Arrays.asList(LocalDate.of(2009, 03, 16), LocalDate.of(2009, 03, 17), LocalDate.of(2009, 03, 18));
		Map<String, Object> input01 = montarEntradaTeste(dates, TipoCliente.REGULAR);
		
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/cheapest?tipoCliente={tipoCliente}&dates={dates}", HttpMethod.GET, null,
				String.class, input01);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains(Hotel.LAKEWOOD.name()));
	}
	
	/**
	 * Entrada 2: Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun) Saída 2: Bridgewood 
	 */
	@Test
	void deveRetornarBridgewoodParaEntrada02Test() {
		List<LocalDate> dates = Arrays.asList(LocalDate.of(2009, 03, 20), LocalDate.of(2009, 03, 21), LocalDate.of(2009, 03, 22));
		Map<String, Object> input02 = montarEntradaTeste(dates, TipoCliente.REGULAR);
		
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/cheapest?tipoCliente={tipoCliente}&dates={dates}", HttpMethod.GET, null,
				String.class, input02);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains(Hotel.BRIDGEWOOD.name()));
	}
	
	/**
	 * Entrada 3: Reward: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat) Saída 3: Ridgewood 
	 */
	@Test
	void deveRetornarBridgewoodParaEntrada03Test() {
		List<LocalDate> dates = Arrays.asList(LocalDate.of(2009, 03, 26), LocalDate.of(2009, 03, 27), LocalDate.of(2009, 03, 28));
		Map<String, Object> input03 = montarEntradaTeste(dates, TipoCliente.REWARD);

		
		ResponseEntity<String> resposta = testRestTemplate.exchange("/api/v1/cheapest?tipoCliente={tipoCliente}&dates={dates}", HttpMethod.GET, null,
				String.class, input03);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertTrue(resposta.getBody().contains(Hotel.RIDGEWOOD.name()));
	}

}
