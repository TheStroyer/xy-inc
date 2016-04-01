package test.repositories;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import br.com.fpc.entities.Attribute;
import br.com.fpc.entities.Attributes;
import br.com.fpc.entities.Model;
import br.com.fpc.repositories.ValuesRepository;

public class ValuesRepositoryTest {
	ValuesRepository vrs = new ValuesRepository();
	
	@Test
	public void find(){
		System.out.println(vrs.find(29));
	}
	
	@Test
	public void findByModel(){
		Model m = new Model();
		
		List<Attribute> l = new ArrayList<Attribute>();
		
		Attribute a1 = new Attribute();
		Attribute a2 = new Attribute();
		Attribute a3 = new Attribute();

		a1.setId(54l);
		a1.setType(2);
		
		a2.setId(53l);
		a2.setType(2);

		a3.setId(52l);
		a3.setType(2);
		
		l.add(a1);
		l.add(a2);
		l.add(a3);
		
		Attributes atts = new Attributes();
		atts.setAttributes(l);
		
		m.setAttributes(atts);
		
		System.out.println(vrs.findByModel(m));
	}
}
