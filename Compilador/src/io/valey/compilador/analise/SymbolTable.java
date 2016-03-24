package io.valey.compilador.analise;

import java.security.GeneralSecurityException;
import java.util.HashMap;

public class SymbolTable extends HashMap<String, Token> {
	private static SymbolTable instance = new SymbolTable();

	private SymbolTable() {
		this.installKeyword(new Token("true", TipoToken.LOGIC_VAL));
		this.installKeyword(new Token("false", TipoToken.LOGIC_VAL));
		this.installKeyword(new Token("not", TipoToken.LOGIC_OP));
		this.installKeyword(new Token("and", TipoToken.LOGIC_OP));
		this.installKeyword(new Token("or", TipoToken.LOGIC_OP));
		this.installKeyword(new Token("bool", TipoToken.TYPE));
		this.installKeyword(new Token("text", TipoToken.TYPE));
		this.installKeyword(new Token("int", TipoToken.TYPE));
		this.installKeyword(new Token("program", TipoToken.PROGRAM));
		this.installKeyword(new Token("end_prog", TipoToken.END_PROGRAM));
		this.installKeyword(new Token("begin", TipoToken.BEGIN));
		this.installKeyword(new Token("end", TipoToken.END));
		this.installKeyword(new Token("if", TipoToken.IF));
		this.installKeyword(new Token("then", TipoToken.THEN));
		this.installKeyword(new Token("else", TipoToken.ELSE));
		this.installKeyword(new Token("for", TipoToken.FOR));
		this.installKeyword(new Token("while", TipoToken.WHILE));
		this.installKeyword(new Token("declare", TipoToken.DECLARE));
		this.installKeyword(new Token("to", TipoToken.TO));
	}

	public static SymbolTable getInstance() {
		return SymbolTable.instance;
	}

	public static Token installToken(String lexema, int line, int column) {
		if (getInstance().containsKey(lexema))
			return getInstance().get(lexema);

		Token token = new Token(lexema, TipoToken.ID, line, column);
		getInstance().put(lexema, token);
		return token;
	}
	
	public static Token installToken(StringBuilder lexema, int line, int column) {
		return installToken(lexema.toString(), line, column);
	}

	private void installKeyword(Token tk) {
		this.put(tk.getLexema(), tk);
	}
}
