package br.com.hotels.strategies;

import java.time.LocalDate;
import java.util.List;

import br.com.hotels.domains.TipoCliente;

public class HotelBridgewoodStrategy implements HotelStrategy{
	
	/**
	 * Classificação 4 <br>
	 * Taxas de dia de semana são: R$160 para REGULAR e R$110 para REWARD <br>
	 * Taxas de final de semana são: R$60 para REGULAR e R$50 para REWARD
	 */
	@Override
	public Double calculaCusto(List<LocalDate> datas, TipoCliente tipoCliente) {
		Double custo = datas.stream().map(data -> {
			if (isWeekend(data)) {
				if (tipoCliente.equals(TipoCliente.REGULAR))
					return 60.0;
				else
					return 50.0;
			} else {
				if (tipoCliente.equals(TipoCliente.REGULAR))
					return 160.0;
				else
					return 110.0;
			}
		}).reduce(0.0, Double::sum);

		return custo;
	}

}
