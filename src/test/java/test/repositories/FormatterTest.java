package test.repositories;

import org.junit.Test;

import br.com.fpc.utils.Formatter;

public class FormatterTest {

	@Test
	public void findAll(){
		String url = "/#$TRSDAND 0 - - 8DAS 5168/*/*96";
		
		System.out.println(Formatter.slugify(url));
	}
}
