package io.valey.compilador.analise;

public class AnalisadorSintatico {
	AnalisadorLexico al;
	SymbolTable tabSimbolos;
	// ErrorHanndler errHandler;

	public AnalisadorSintatico(String path) {
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
