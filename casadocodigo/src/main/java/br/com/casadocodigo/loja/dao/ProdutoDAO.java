package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.exception.DaoException;
import br.com.casadocodigo.loja.model.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto findById(Integer id) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select distinct p ");
			builder.append("from Produto p ");
			builder.append("join fetch p.precos ");
			builder.append("where p.id = :id ");
			
			return manager.createQuery(builder.toString(), Produto.class)
					.setParameter("id", id)
					.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException("Erro no findById", e);
		}
		
	}
	
}