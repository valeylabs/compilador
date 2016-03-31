package io.valey.compilador.analise;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map.Entry;

public class SymbolTable extends HashMap<String, Token> {
	private static SymbolTable instance = new SymbolTable();

	private SymbolTable() {
		this.installKeyword(new Token("true", TokenType.LOGIC_VAL));
		this.installKeyword(new Token("false", TokenType.LOGIC_VAL));
		this.installKeyword(new Token("not", TokenType.LOGIC_OP));
		this.installKeyword(new Token("and", TokenType.LOGIC_OP));
		this.installKeyword(new Token("or", TokenType.LOGIC_OP));
		this.installKeyword(new Token("bool", TokenType.TYPE));
		this.installKeyword(new Token("text", TokenType.TYPE));
		this.installKeyword(new Token("int", TokenType.TYPE));
		this.installKeyword(new Token("program", TokenType.PROGRAM));
		this.installKeyword(new Token("end_prog", TokenType.END_PROGRAM));
		this.installKeyword(new Token("begin", TokenType.BEGIN));
		this.installKeyword(new Token("end", TokenType.END));
		this.installKeyword(new Token("if", TokenType.IF));
		this.installKeyword(new Token("then", TokenType.THEN));
		this.installKeyword(new Token("else", TokenType.ELSE));
		this.installKeyword(new Token("for", TokenType.FOR));
		this.installKeyword(new Token("while", TokenType.WHILE));
		this.installKeyword(new Token("declare", TokenType.DECLARE));
		this.installKeyword(new Token("to", TokenType.TO));
	}

	public static SymbolTable getInstance() {
		return SymbolTable.instance;
	}

	public static Token installToken(String lexema, int line, int column) {
		if (getInstance().containsKey(lexema)){
			Token t = getInstance().get(lexema);
			if(t.getCodigoToken() != TokenType.ID)
				t.setLinhaColuna(line, column);
				
			return t;
		}

		Token token = new Token(lexema, TokenType.ID, line, column);
		getInstance().put(lexema, token);
		return token;
	}
	
	public static Token installToken(StringBuilder lexema, int line, int column) {
		return installToken(lexema.toString(), line, column);
	}

	private void installKeyword(Token tk) {
		this.put(tk.getLexema(), tk);
	}
	
	public static void printTable(){
		for(Entry<String, Token> entry : getInstance().entrySet()) {
		    String key = entry.getKey();
		    Token value = entry.getValue();
		    
		    value.printToken();
		}
	}
}
