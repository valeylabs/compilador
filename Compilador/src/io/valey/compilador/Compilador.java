package io.valey.compilador;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import io.valey.compilador.analise.AnalisadorSintatico;

public class Compilador {
	public static void main(String[] args) {
		teste();
		
		AnalisadorSintatico as = new AnalisadorSintatico("source.vl");
		as.execute();
	}

	private static void teste() {
		try {
			FileLoader file = new FileLoader("C:/Users/43596980895/Desktop/meuarquivo.txt");

			try {

				//while (true)
					//System.out.print(file.getNextChar() + "(" + file.getLine() + "," + file.getColumn() + ")");
				
				System.out.print(file.getNextChar() + "(" + file.getLine() + "," + file.getColumn() + ")");
				System.out.print(file.getNextChar() + "(" + file.getLine() + "," + file.getColumn() + ")");
				System.out.print(file.getNextChar() + "(" + file.getLine() + "," + file.getColumn() + ")");

				System.out.print(file.getNextChar() + "(" + file.getLine() + "," + file.getColumn() + ")");
				file.rollbackChar();
				System.out.print(file.getNextChar() + "(" + file.getLine() + "," + file.getColumn() + ")");
				

			} catch (EOFException e) {
				//file.rollbackChar();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Problemas para abrir o arquivo. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Problemas na leitura do arquivo. " + e.getMessage());
		}
	}
}
