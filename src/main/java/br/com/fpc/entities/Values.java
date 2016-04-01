package br.com.fpc.entities;

import java.util.List;

/**
 * @author fernando.costa
 * 
 * Entidade que representa os valores de um atributo
 *
 */
public class Values {
	public long id;
	public List<Value> values;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

}
