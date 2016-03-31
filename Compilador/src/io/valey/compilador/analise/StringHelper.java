package io.valey.compilador.analise;

public class StringHelper {

	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
	
	public static String padRight(int s, int n) {
	     return String.format("%1$-" + n + "s", String.valueOf(s));  
	}

	public static String padLeft(int s, int n) {
	    return String.format("%1$" + n + "s", String.valueOf(s));  
	}
	
}
