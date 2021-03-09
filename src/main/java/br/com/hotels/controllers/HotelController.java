package br.com.hotels.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.TipoCliente;
import br.com.hotels.services.HotelService;
import br.com.hotels.services.impl.HotelServiceImpl;
import io.swagger.annotations.ApiOperation;
/**
 * RestController que provê todos os servicos relacionados a hotel.
 * 
 * @author allan
 *
 */
@RestController
@RequestMapping("/api/v1")
public class HotelController {
	
	public HotelService hotelService;
	
	@Autowired
	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	@ApiOperation(value = "Retorna o hotel mais barato com base no tipo de cliente e dias repassados.")
	@GetMapping("/cheapest")	
	public ResponseEntity<Map<String, Object>> cheapest(@RequestParam TipoCliente tipoCliente, @RequestParam @DateTimeFormat(iso = ISO.DATE) List<LocalDate> dates){
		Hotel hotel = hotelService.cheapest(dates, tipoCliente);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("cheapest", hotel.name());
		
		return ResponseEntity.ok(body);
	}
	
}
