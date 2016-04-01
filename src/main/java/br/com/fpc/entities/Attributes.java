package br.com.fpc.entities;

import java.util.List;

/**
 * @author fernando.costa
 * 
 * Entidade responsável por mapear os atributos de um modelo
 *
 */
public class Attributes {
	private long modelId;
	private List<Attribute> attributes;

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public long getModelId() {
		return modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

}
