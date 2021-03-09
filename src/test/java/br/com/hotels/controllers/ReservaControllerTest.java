package br.com.hotels.controllers;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;
import br.com.hotels.dto.ReservaDTO;
import br.com.hotels.response.Response;
import br.com.hotels.services.ReservaService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class ReservaControllerTest {
	
	@MockBean
	private ReservaService reservaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ReservaDTO reservaRequest;
	
	private ObjectMapper mapper;
	
	@BeforeAll
	public void setUp() {
		reservaRequest = new ReservaDTO(null, "teste01", "084", "teste@teste", LocalDate.now(),
				LocalDate.now().plusDays(2), Hotel.BRIDGEWOOD, "sem observacoes");
		
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
	}
	
	private Reserva getMockReserva() {
		return new Reserva(1L, "123456", "teste01", "084", "teste@teste", LocalDate.now(), LocalDate.now().plusDays(2),
				Hotel.BRIDGEWOOD, "sem observacoes");
	}
	
	@Test
	@Order(1)
	void deveCadastrarReservaTest() throws Exception {		
		BDDMockito.given(reservaService.save(Mockito.any(Reserva.class))).willReturn(getMockReserva());
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservas")
					.content(mapper.writeValueAsString(reservaRequest))
					.contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(MockMvcResultHandlers.print());
		
		result.andExpect(MockMvcResultMatchers.status().isCreated());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"));
		result.andExpect(MockMvcResultMatchers.jsonPath("$.data.nome").value("teste01"));
	}


	@Test
	@Order(2)
	void deveRetornarErroCamposInvalidosTest() throws Exception {
		BDDMockito.given(reservaService.save(Mockito.any(Reserva.class))).willReturn(getMockReserva());
		
		reservaRequest.setInicio(null);
		reservaRequest.setFim(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reservas")
					.content(mapper.writeValueAsString(reservaRequest))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isBadRequest())
			.andExpect(MockMvcResultMatchers.jsonPath("$.errors").isArray())			
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
	}

}
