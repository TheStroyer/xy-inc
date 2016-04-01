package test.repositories;

import org.junit.Test;

import br.com.fpc.repositories.ValueRepository;

public class ValueRepositoryTest {

	ValueRepository vr = new ValueRepository();
	
	@Test
	public void findAll(){
		System.out.println(vr.find(29));
	}
	
	@Test
	public void findByKey(){
		System.out.println(vr.findByKey(29,52,36));
	}
	
	@Test
	public void find(){
		System.out.println(vr.find(29));
	}
}
