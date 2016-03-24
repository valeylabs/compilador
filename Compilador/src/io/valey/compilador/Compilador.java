package io.valey.compilador;

import io.valey.compilador.analise.AnalisadorSintatico;
import io.valey.compilador.analise.SymbolTable;

public class Compilador {
	public static void main(String[] args) {
		teste();

		AnalisadorSintatico as = new AnalisadorSintatico("source.vl");
		as.execute();
	}

	private static void teste() {
		try {

			AnalisadorSintatico as = new AnalisadorSintatico("c:/users/42143954840/desktop/meuarquivo.txt");
			as.execute();
			
			SymbolTable.getInstance();

		} catch (Exception e) {
			System.out.println("\nChegamos ao fim!");
		}
	}
}
