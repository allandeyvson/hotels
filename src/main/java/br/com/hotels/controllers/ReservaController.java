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
