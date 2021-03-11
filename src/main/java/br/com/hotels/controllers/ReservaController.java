package br.com.hotels.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.Reserva;
import br.com.hotels.dto.ReservaDTO;
import br.com.hotels.response.Response;
import br.com.hotels.services.ReservaService;

/**
 * Controller que provê todos os servicos relacionados a reserva.
 * @author allan
 *
 */
@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {
	
	
	private ReservaService reservaService;
	
	@Autowired
	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}
	
	@PostMapping
	public ResponseEntity<Response<Reserva>> create(@Valid @RequestBody ReservaDTO dto, BindingResult result){
		
		Response<Reserva> response = new Response<Reserva>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrors(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Reserva reserva = reservaService.save(dto.convertDTOtoEntity());
		response.setData(reserva);
		
		return new ResponseEntity<Response<Reserva>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Response<List<Reserva>>> findAll() {
		Response<List<Reserva>> response = new Response<List<Reserva>>();

		List<Reserva> reservas = reservaService.findAll();
		response.setData(reservas);

		return new ResponseEntity<Response<List<Reserva>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/numeroReserva/{numeroReserva}")
	public ResponseEntity<Response<Reserva>> findByNumero(@PathVariable String numeroReserva) {
		Response<Reserva> response = new Response<Reserva>();

		Optional<Reserva> reserva = reservaService.findByNumeroReserva(numeroReserva);
		if(reserva.isEmpty()) {
			response.addErrors("A reserva de numero " + numeroReserva + " nao existe na api.");
			return new ResponseEntity<Response<Reserva>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.setData(reserva.get());
		return new ResponseEntity<Response<Reserva>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{hotel}")
	public ResponseEntity<Response<List<Reserva>>> findByHotel(@PathVariable Hotel hotel) {
		Response<List<Reserva>> response = new Response<List<Reserva>>();

		Optional<List<Reserva>> reservas = reservaService.findByHotel(hotel);
		if(reservas.isPresent() && reservas.get().isEmpty()) {
			response.addErrors("Nao existem reservas cadastradas para o hotel " + hotel.name());
			return new ResponseEntity<Response<List<Reserva>>>(response, HttpStatus.NOT_FOUND);
		}
		response.setData(reservas.get());
		return new ResponseEntity<Response<List<Reserva>>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable Long id){
		Response<String> response = new Response<String>();
		
		Optional<Reserva> reserva = reservaService.findById(id);
		
		if(reserva.isEmpty()) {
			response.addErrors("O elemento selecionado nao existe na api.");
			return new ResponseEntity<Response<String>>(response, HttpStatus.NOT_FOUND);
		}
		
		reservaService.deleteById(reserva.get().getId());
		response.setData("Reserva id=" + reserva.get().getId() + " deletada com sucesso.");
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
}
