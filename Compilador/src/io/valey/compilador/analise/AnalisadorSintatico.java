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

import java.io.IOException;

public class AnalisadorSintatico {
	AnalisadorLexico al;
	SymbolTable tabSimbolos;
	// ErrorHanndler errHandler;

	public AnalisadorSintatico(String path) throws IOException {
		al = new AnalisadorLexico(path);
		tabSimbolos = SymbolTable.getInstance();
	}

	public void execute() {
		Token t = null;

		System.out.println("-------------------------------------------------");
		System.out.println("|          TOKENS DO ANALISADOR LEXICO          |");
		System.out.println("-------------------------------------------------");
		System.out.println("|  #  |    TIPO    |    LEXEMA    |   POSICAO   |");
		System.out.println("-------------------------------------------------");
		int i = 0;
		do {
			i++;
			t = al.nextToken();
			System.out.println(
					"|" + StringHelper.padRight(i, 5) + "|" + StringHelper.padRight(t.getCodigoToken().toString(), 12)
							+ "|" + StringHelper.padRight(t.getLexema(), 14) + "|"
							+ StringHelper.padRight(t.getLinhaColuna(), 13) + "|");
		} while (t.getCodigoToken() != TokenType.EOF);
		System.out.println("-------------------------------------------------");
	}
}
