package br.com.casadocodigo.loja.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.model.CarrinhoItem;
import br.com.casadocodigo.loja.model.Produto;
import br.com.casadocodigo.loja.model.TipoPreco;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		
		carrinhoCompras.add(carrinhoItem);
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("/carrinho/itens");
		return modelAndView;
	}
	
	@RequestMapping(value = "/remover", method = RequestMethod.POST)
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinhoCompras.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.findById(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
	
}
