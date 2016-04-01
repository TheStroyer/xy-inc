package test.repositories;

import java.util.Arrays;

import org.junit.Test;

import br.com.fpc.entities.Attribute;
import br.com.fpc.entities.Attributes;
import br.com.fpc.entities.Model;
import br.com.fpc.repositories.ModelRepository;


public class ModelRepositoryTest {
	
	private ModelRepository mr = new ModelRepository();
	
	@Test
	public void testInsert(){
	
		Attribute att = new Attribute();
		att.setName("atributo 1");
		att.setType(0);
		
		Attributes atts = new Attributes();
		atts.setAttributes(Arrays.asList(att));
		
		Model model = new Model();
		model.setName("teste 1");
		model.setAttributes(atts);
		
		mr.create(model);
		
	}
	
	@Test
	public void findAll(){
		System.out.println(mr.findAll());
	}
	
}
