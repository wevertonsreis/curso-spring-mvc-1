package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.model.CarrinhoItem;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public int getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public Integer getQuantidade() {
		return itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
	
}
