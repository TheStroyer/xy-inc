package br.com.fpc.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fpc.entities.Attribute;
import br.com.fpc.entities.Attributes;

/**
 * @author fernando.costa
 *
 */
@Repository
public class AttributesRepository implements IRepository<Attributes> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private final AttributeRepository ar = new AttributeRepository();

	/**
	 * 
	 */
	private final String sqlInsert = "INSERT INTO attributes VALUES (?, ?, ?, ?)";

	/**
	 * 
	 */
	private final String sqlSelectByModel = "select * FROM attributes where model_id = ?";

	@Override
	public long add(Attributes atts) {

		int numAtts = atts.getAttributes().size();
		int numAttsEmpty = 0;
		
		for (Attribute att : atts.getAttributes()) {
			
			if(!att.getName().isEmpty()) {
				
				Object[] params = new Object[] { System.currentTimeMillis(), atts.getModelId(), att.getName(),
						att.getType() };
	
				try {
					jdbcTemplate.update(sqlInsert, params);
				} catch (Exception e) {
					return 0l;
				}
			}
			else {
				numAttsEmpty++;
			}

		}
		
		return numAtts == numAttsEmpty ? 0l : 1l;
	}

	@Override
	public void remove(Attributes attributes) {
		for (Attribute att : attributes.getAttributes()) {
			ar.remove(att);
		}
	}

	/**
	 * @param modelId
	 * @return
	 */
	public Attributes findByModel(long modelId) {
		Object[] params = new Object[] { modelId };

		Attributes attributes = new Attributes();
		try {
			List<Attribute> atts = new ArrayList<Attribute>();
			atts = jdbcTemplate.query(sqlSelectByModel, params, new BeanPropertyRowMapper<Attribute>(Attribute.class));
			attributes.setAttributes(atts);

		} catch (Exception e) {
			throw e;
		}

		return attributes;
	}

	@Override
	public Attributes find(long id) {
		return null;
	}

}
