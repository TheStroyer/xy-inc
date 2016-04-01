package br.com.fpc.repositories;

/**
 * @author fernando.costa
 *
 * @param <TEntity>
 */
public interface IRepository<TEntity> {

	/**
	 * Adiciona uma entidade no banco 
	 * 
	 * @param item
	 * @return
	 */
	long add(TEntity item);
	
	/**
	 * Remove uma entidade do banco
	 * 
	 * @param id
	 */
	void remove(TEntity item);
	
	/**
	 * Busca uma entidade pelo id
	 * 
	 * @param id
	 * @return
	 */
	TEntity find(long id);
}
