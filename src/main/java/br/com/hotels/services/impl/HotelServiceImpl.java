package br.com.hotels.services.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import java.util.Optional;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.TipoCliente;
import br.com.hotels.services.HotelService;
import br.com.hotels.strategies.HotelBridgewoodStrategy;
import br.com.hotels.strategies.HotelLakewoodStrategy;
import br.com.hotels.strategies.HotelRidgewoodStrategy;

/**
 * Entidade que irá prover os servicos/funcionalidades da api no escopo dos hoteis.
 * @author allan
 *
 */
@Service
public class HotelServiceImpl implements HotelService{
	
	@Override
	public Hotel cheapest(List<LocalDate> datas, TipoCliente tipoCliente) {
		HashMap<Hotel, Double> mapaCustos = new HashMap<Hotel, Double>();
		mapaCustos.put(Hotel.LAKEWOOD, (new HotelLakewoodStrategy()).calculaCusto(datas, tipoCliente));
		mapaCustos.put(Hotel.BRIDGEWOOD, (new HotelBridgewoodStrategy()).calculaCusto(datas, tipoCliente));
		mapaCustos.put(Hotel.RIDGEWOOD, (new HotelRidgewoodStrategy()).calculaCusto(datas, tipoCliente));

		Entry<Hotel, Double> minEntry = Map.entry(Hotel.LAKEWOOD, mapaCustos.get(Hotel.LAKEWOOD));

		for (Hotel h : mapaCustos.keySet()) {
			if (minEntry.getValue() > mapaCustos.get(h)) {
				minEntry = Map.entry(h, mapaCustos.get(h));
			} else if (minEntry.getValue().equals(mapaCustos.get(h))) {
				if (h.getClassificacao() > minEntry.getKey().getClassificacao()) {
					minEntry = Map.entry(h, mapaCustos.get(h));
				}
			}
		}
		return minEntry.getKey();
	}
	
	
	
}
