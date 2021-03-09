package br.com.hotels.strategies;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import br.com.hotels.domains.Hotel;
import br.com.hotels.domains.TipoCliente;

/**
 * Interface utilizada para padronizar o calculo de custo de cada hotel.
 * @author allan
 *
 */
public interface HotelStrategy {
	
	/**
	 * Método que calcula o custo da estadia de acordo com a estrategia do hotel.
	 * @param datas
	 * @return
	 */
	public Double calculaCusto(List<LocalDate> datas, TipoCliente tipoCliente);	
	
	public default boolean isWeekend(LocalDate data) {
		return data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY;
	}
	
}
