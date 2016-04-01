package br.com.fpc.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author fernando.costa
 *
 */
public class Formatter {

	private static final Pattern p = Pattern.compile("[^aA-zZ0-9-]");  
	private static final Pattern space = Pattern.compile("[\\s]"); 
	  
	/**
	 * @param url
	 * @return
	 */
	public static String slugify(String url){
		String nowhitespace = space.matcher(url).replaceAll("-");  
	    String normalized = Normalizer.normalize(nowhitespace, Form.NFD);  
	    String slug = p.matcher(normalized).replaceAll("-");  
	    return slug.toLowerCase(Locale.ENGLISH);  
	}
}
