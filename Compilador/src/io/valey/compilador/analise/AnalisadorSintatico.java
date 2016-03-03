package io.valey.compilador.analise;

public class AnalisadorSintatico {
	AnalisadorLexico al;
	TabelaSimbolos tabSimbolos;
	// ErrorHanndler errHandler;

	public AnalisadorSintatico(String path) {
		al = new AnalisadorLexico(path);
		tabSimbolos = new TabelaSimbolos();
	}

	public void execute() {
		Token t = null;

		do {
			t = al.nextToken();
		} while (true/* t.getTokenCode() != TokenCode.EOF */);
	}
}
