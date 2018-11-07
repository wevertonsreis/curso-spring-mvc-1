package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

public class DadosPagamento {
	
	private BigDecimal value;
	
	public DadosPagamento() {
		super();
	}

	public DadosPagamento(BigDecimal value) {
		super();
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}
	
}
