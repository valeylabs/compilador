package io.valey.compilador;

import java.io.IOException;
import java.util.Map.Entry;

import io.valey.compilador.analise.AnalisadorSintatico;
import io.valey.compilador.analise.ErrorHandler;
import io.valey.compilador.analise.SymbolTable;
import io.valey.compilador.analise.Token;

public class Compilador {
	
	public static void main(String[] args) throws IOException {
		AnalisadorSintatico as = new AnalisadorSintatico("c:/users/42143954840/desktop/meuarquivo.txt");
		as.execute();
		
		SymbolTable.printTable();
		
		ErrorHandler.printErrors();
	}
}
