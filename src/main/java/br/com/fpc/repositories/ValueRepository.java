package br.com.fpc.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fpc.entities.Attribute;
import br.com.fpc.entities.Value;
import br.com.fpc.utils.Types;

/**
 * @author fernando.costa
 *
 */
@Repository
public class ValueRepository implements IRepository<Value> {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AttributeRepository ar = new AttributeRepository();

	/**
	 * 
	 */
	private final String sqlInsert = "insert into %s values (?, ?)";

	/**
	 * 
	 */
	private final String sqlUpdate = "update %s set value = ? where id = ?";

	/**
	 * 
	 */
	private final String sqlSelectValue = " SELECT atts.name as attributeValue, av.id, av.attribute_id, av.value_id, v.value "
			+ " FROM attribute_values av, %s v, attributes atts" + " WHERE av.value_id = v.id "
			+ "		AND av.id = ? AND av.attribute_id = atts.id AND v.id = ?";

	/**
	 * 
	 */
	private final String sqlDelete = "delete from %s where id = ?";

	@Override
	public long add(Value value) {
		
		long valueId = 0;
		
		try {
			Attribute att = ar.find(value.getAttributeId());
	
			valueId = System.currentTimeMillis();
			
			Object[] params = new Object[] { valueId , Types.getFor(att.getType()).getValue(value.getValue()) };
			
			jdbcTemplate.update(String.format(sqlInsert, Types.getFor(att.getType()).getTable()), params);
			
		} catch (Exception e) {
			System.out.println("ValueRepository -> add(): Erro ao adicionar valor. (" + e.getClass() + "). " + e.getMessage());
			throw e;
		}
		return valueId;
	}

	@Override
	public void remove(Value value) {
		Attribute att = ar.find(value.getAttributeId());
		try {
			Object[] params = new Object[] { value.getValueId() };
			jdbcTemplate.update(String.format(sqlDelete, Types.getFor(att.getType()).getTable()), params);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param id
	 * @param attId
	 * @param valueId
	 * @return
	 */
	public Value findByKey(long id, long attId, long valueId) {
		Value value = new Value();
		try {
			Attribute att = ar.find(attId);

			Object[] params = new Object[] { id, valueId };
			value = jdbcTemplate.queryForObject(String.format(sqlSelectValue, Types.getFor(att.getType()).getTable()),
					params, new BeanPropertyRowMapper<Value>(Value.class));

		} catch (Exception e) {
			throw e;
		}

		return value;
	}

	@Override
	public Value find(long id) {
		return null;
	}

	/**
	 * @param value
	 * @return
	 */
	public int update(Value value) {
		Attribute att = ar.find(value.getAttributeId());

		Object[] params = new Object[] { value.getValue(), value.getValueId() };
		try {
			return jdbcTemplate.update(String.format(sqlUpdate, Types.getFor(att.getType()).getTable()), params);
		} catch (Exception e) {
			throw e;
		}

	}
}
