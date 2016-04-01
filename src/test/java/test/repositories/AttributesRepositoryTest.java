package test.repositories;

import org.junit.Test;

import br.com.fpc.repositories.AttributesRepository;

public class AttributesRepositoryTest {

	AttributesRepository ar = new AttributesRepository();
	
	@Test
	public void findById(){
		System.out.println(ar.find(1));
	}
}
