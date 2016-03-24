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

		do {
			t = al.nextToken();
		} while (t.getCodigoToken() != TipoToken.EOF);
	}
}
