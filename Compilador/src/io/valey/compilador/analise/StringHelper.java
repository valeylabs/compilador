/**
 * Alunos: 
 * Erik Zerbinatti      37816817851
 * Gabriel Malaquias    42143954840
 * Guilherme Ventura    43596980895
 * Luiz Fernando Santos 42547332833 
 * 
 * Curso: Ciência da Computação 7º Semestre
 */

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
