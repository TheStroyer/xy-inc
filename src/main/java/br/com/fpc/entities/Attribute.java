package br.com.fpc.entities;

import br.com.fpc.utils.Types;

/**
 * @author fernando.costa
 * 
 * Entidade responsável por mapear um atributo de um modelo
 *
 */
public class Attribute {

	private Long id;
	private String name;
	private int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Attribute: " + getName() + " | Type: " + Types.getFor(getType());
	}

}
