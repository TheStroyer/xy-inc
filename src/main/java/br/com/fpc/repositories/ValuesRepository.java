package br.com.fpc.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fpc.entities.Attribute;
import br.com.fpc.entities.Model;
import br.com.fpc.entities.Value;
import br.com.fpc.entities.Values;
import br.com.fpc.utils.Types;

/**
 * @author fernando.costa
 *
 */
@Repository
public class ValuesRepository implements IRepository<Values> {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ValueRepository vr = new ValueRepository();
	
	@Autowired
	private AttributeRepository ar = new AttributeRepository();

	/**
	 * 
	 */
	private final String sqlSelectByAttributeId = " SELECT av.id, av.attribute_id, av.value_id, v.value "
			+ " FROM  attribute_values av, %s v" + " WHERE av.value_id = v.id " + "		AND attribute_id = ?";

	/**
	 * 
	 */
	private final String sqlInsert = "INSERT INTO attribute_values VALUES (?, ?, ?)";

	/**
	 * 
	 */
	private final String sqlSelect = "SELECT * FROM attribute_values av where id = ?";

	/**
	 * 
	 */
	private final String sqlDelete = "DELETE FROM attribute_values WHERE id = ? and attribute_id = ? AND value_id = ?";

	@Override
	public long add(Values values) {

		long valuesId = System.currentTimeMillis();

		try {
			for (Value v : values.getValues()) {
				Attribute att = ar.find(v.getAttributeId());

				long valueid = vr.add(v);

				Object[] params = new Object[] { valuesId, att.getId(), valueid };
				jdbcTemplate.update(sqlInsert, params);

			}
		} catch (Exception e) {
			System.out.println(
					"ValuesRepository -> add(): Erro ao adicionar valores. (" + e.getClass() + "). " + e.getMessage());
			valuesId = 0;
		}

		return valuesId;
	}

	@Override
	public void remove(Values values) {
		for (Value v : values.getValues()) {
			vr.remove(v);

			try {
				Object[] params = new Object[] { v.getId(), v.getAttributeId(), v.getValueId() };
				jdbcTemplate.update(sqlDelete, params);
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * @param model
	 * @return
	 */
	public List<Values> findByModel(Model model) {

		List<Attribute> atts = model.getAttributes() != null ? model.getAttributes().getAttributes()
				: new ArrayList<Attribute>();

		int qtdAttributes = atts.size();
		List<List<Value>> valuesList = new ArrayList<List<Value>>(qtdAttributes);

		for (Attribute att : atts) {

			Object[] params = new Object[] { att.getId() };
			try {
				List<Value> vs = new ArrayList<Value>();
				vs = jdbcTemplate.query(String.format(sqlSelectByAttributeId, Types.getFor(att.getType()).getTable()),
						params, new BeanPropertyRowMapper<Value>(Value.class));

				if (!vs.isEmpty()) {
					valuesList.add(vs);
				} else {
					qtdAttributes--;
				}

			} catch (Exception e) {
				throw e;
			}
		}

		int qtdModels = valuesList.size() > 0 ? valuesList.get(0).size() : 0;

		List<Values> values = new ArrayList<Values>(qtdModels);
		for (int i = 0; i < qtdModels; i++) {

			Values vs = new Values();
			List<Value> v = new ArrayList<Value>();

			for (int j = 0; j < qtdAttributes; j++) {
				v.add(valuesList.get(j).get(i));
				vs.setId(valuesList.get(j).get(i).getId());
			}
			vs.setValues(v);

			values.add(vs);
		}

		return values;

	}

	@Override
	public Values find(long id) {

		Values values = new Values();

		List<Value> vs = new ArrayList<Value>();
		try {
			Object[] params = new Object[] { id };
			vs.addAll(jdbcTemplate.query(sqlSelect, params, new BeanPropertyRowMapper<Value>(Value.class)));

		} catch (Exception e) {
			throw e;
		}

		List<Value> listValues = new ArrayList<Value>();
		for (Value v : vs) {
			listValues.add(vr.findByKey(v.getId(), v.getAttributeId(), v.getValueId()));
		}
		values.setId(id);
		values.setValues(listValues);

		return values;
	}

	/**
	 * @param values
	 */
	public void update(Values values) {

		for (Value value : values.getValues()) {
			vr.update(value);
		}

	}
}
