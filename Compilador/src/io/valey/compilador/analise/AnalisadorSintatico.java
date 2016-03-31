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
		System.out.println("|  #  |    TIPO    |    LEXEMA    |   POSICAO   |");
		System.out.println("-------------------------------------------------");
		int i = 0;
		do {
			i++;
			t = al.nextToken();
			System.out.println(i + "|" + t.getCodigoToken() + "|" + t.getLexema() + "|" + t.getLinhaColuna());
		} while (t.getCodigoToken() != TokenType.EOF);
	}
}
