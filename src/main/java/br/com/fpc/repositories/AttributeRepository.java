package br.com.fpc.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fpc.entities.Attribute;

/**
 * @author fernando.costa
 *
 */
@Repository
public class AttributeRepository implements IRepository<Attribute> {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 */
	private final String sqlSelect = "SELECT * FROM attributes WHERE id = ?";
	
	/**
	 * 
	 */
	private final String sqlDelete = "DELETE FROM attributes WHERE id = ?";

	@Override
	public long add(Attribute item) {
		return 0;
	}

	@Override
	public void remove(Attribute att) {
		try {
			Object[] params = new Object[] { att.getId() };
			jdbcTemplate.update(sqlDelete, params);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Attribute find(long id) {
		Object[] params = new Object[] { id };

		Attribute attribute = new Attribute();
		try {
			attribute = jdbcTemplate.queryForObject(sqlSelect, params,
					new BeanPropertyRowMapper<Attribute>(Attribute.class));

		} catch (Exception e) {
			throw e;
		}

		return attribute;
	}

}
