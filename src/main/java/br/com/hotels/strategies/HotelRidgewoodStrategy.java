package br.com.hotels.strategies;

import java.time.LocalDate;
import java.util.List;

import br.com.hotels.domains.TipoCliente;

public class HotelRidgewoodStrategy implements HotelStrategy {
	
	/**
	 * Classificação 5 <br>
	 * Taxas de dia de semana são: R$220 para REGULAR e R$100 para REWARD <br>
	 * Taxas de final de semana são: R$150 para REGULAR e R$40 para REWARD
	 */
	@Override
	public Double calculaCusto(List<LocalDate> datas, TipoCliente tipoCliente) {
		Double custo = datas.stream().map(data -> {
			if (isWeekend(data)) {
				if (tipoCliente.equals(TipoCliente.REGULAR))
					return 150.0;
				else
					return 40.0;
			} else {
				if (tipoCliente.equals(TipoCliente.REGULAR))
					return 220.0;
				else
					return 100.0;
			}
		}).reduce(0.0, Double::sum);

		return custo;
	}

}
