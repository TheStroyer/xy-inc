package br.com.fpc.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fpc.entities.Model;
import br.com.fpc.entities.Values;
import br.com.fpc.utils.Formatter;

/**
 * @author fernando.costa
 *
 */
@Repository
public class ModelRepository implements IRepository<Model>{

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private final AttributesRepository attsr = new AttributesRepository();
	
	@Autowired
	private final AttributesRepository attr = new AttributesRepository();
	
	@Autowired
	private final ValuesRepository vlsr = new ValuesRepository();
	
	/**
	 * 
	 */
	private final String sqlInsert = "INSERT INTO models VALUES (?, ?, ?, ?)";
	/**
	 * 
	 */
	private final String sqlDelete = "DELETE FROM models where id = ?";
	/**
	 * 
	 */
	private final String sqlFindAll = "SELECT * FROM models";
	/**
	 * 
	 */
	private final String sqlFindById = "SELECT * FROM models where id = ?";
	/**
	 * 
	 */
	private final String sqlFindByUrl = "SELECT * FROM models where url = ?";
	
	@Override
	public long add(Model model) {
		long modelId = System.currentTimeMillis();
		try {
			Object[] params = new Object[]{modelId, Formatter.slugify(model.getUrl()).toLowerCase(), model.getName(), model.getDescription()};
			jdbcTemplate.update(sqlInsert, params);
			return modelId;
		}catch( Exception e ){
			return 0l;
		}
		
	}
	
	/**
	 * @param model
	 * @return
	 */
	public long create(Model model){
		if(!model.getUrl().isEmpty()) {
			long modelId = add(model);
			
			if(modelId > 0) {
				model.getAttributes().setModelId(modelId);
				
				if(attsr.add(model.getAttributes()) > 0) {
					return modelId;
				}
				
				if(modelId > 0) {
					remove(model);
				}
				
			}
			
		}
		return 0l;
	}
	

	@Override
	public void remove(Model model) {
		List<Values> values = vlsr.findByModel(model);
		
		for(Values vs: values){
			vlsr.remove(vs);
		}
		
		attsr.remove(model.getAttributes());

		try{
			Object[] params = new Object[]{model.getId()};
			jdbcTemplate.update(sqlDelete,params);
		}catch( Exception e ){
			throw e;
		}
		
	}

	/**
	 * @param modelUrl
	 * @return
	 */
	public Model findByUrl(String modelUrl) { 
		Model model = new Model();
		try{
			Object[] params = new Object[]{modelUrl};
			model = jdbcTemplate.queryForObject(sqlFindByUrl, params, new BeanPropertyRowMapper<Model>(Model.class));
			model.setAttributes(attr.findByModel(model.getId()));
			
		}catch( Exception e ){
			model = new Model();
		}
		
		return model;
	}
	
	@Override
	public Model find(long modelId) {
		Model model = new Model();
		try{
			Object[] params = new Object[]{modelId};
			model = jdbcTemplate.queryForObject(sqlFindById, params, new BeanPropertyRowMapper<Model>(Model.class));
			model.setAttributes(attr.findByModel(modelId));
			
		}catch( Exception e ){
			throw e;
		}
		
		return model;
	}
	
	/**
	 * @return
	 */
	public List<Model> findAll(){
		List<Model> models = new ArrayList<Model>();

		try{
			models = jdbcTemplate.query(sqlFindAll,new BeanPropertyRowMapper<Model>(Model.class));
		}catch( Exception e ){
			throw e;
		}

		return models;
	}
	
}