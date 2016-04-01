package br.com.fpc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author fernando.costa
 *
 *	Entidade que representa o valor de um atributo
 */
public class Value {

	@JsonIgnore
	public long id;
	
	@JsonIgnore
	public long attributeId;
	
	@JsonIgnore
	public long valueId;
	
	@JsonProperty("atributo")
	public String attributeValue;
	
	@JsonProperty("valor")
	public String value;

	public long getValueId() {
		return valueId;
	}

	public void setValueId(long valueId) {
		this.valueId = valueId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
