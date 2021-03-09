package br.com.hotels.domains;

/**
 * Enum que representa um hotel
 * 
 * @author allan
 *
 */
public enum Hotel {
	LAKEWOOD(3), BRIDGEWOOD(4), RIDGEWOOD(5);
	
	private int classificacao;
	
	private Hotel(int classificacao) {
		this.classificacao = classificacao;
	}
	
	public int getClassificacao(){
		return this.classificacao;
	}
}
