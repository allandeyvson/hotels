package br.com.hotels.strategies;

import java.time.LocalDate;
import java.util.List;

import br.com.hotels.domains.TipoCliente;

public class HotelLakewoodStrategy implements HotelStrategy {
	
	/**
	 * Classificação 3 <br>
	 * Taxas de dia de semana são: R$110 para REGULAR e R$80 para REWARD <br>
	 * Taxas de final de semana são: R$90 para REGULAR e R$80 para REWARD
	 */
	@Override
	public Double calculaCusto(List<LocalDate> datas, TipoCliente tipoCliente) {
		Double custo = datas.stream().map(data -> {
			if (isWeekend(data)) {
				if (tipoCliente.equals(TipoCliente.REGULAR))
					return 90.0;
				else
					return 80.0;
			} else {
				if (tipoCliente.equals(TipoCliente.REGULAR))
					return 110.0;
				else
					return 80.0;
			}
		}).reduce(0.0, Double::sum);

		return custo;
	}
}
