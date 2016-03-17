package io.valey.compilador.analise;

public class Token {
	
	private String lexema;
	
	private TipoToken codigoToken;
	
	private int linha;
	
	private int coluna;

	public Token(String lexema, TipoToken tipo, int linha, int coluna){
		this.lexema = lexema;
		this.codigoToken = tipo;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public Token(String lexema, TipoToken tipo){
		this(lexema, tipo, 0, 0);
	}
	
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public String getLexema() {
		return lexema;
	}

	public TipoToken getCodigoToken() {
		return codigoToken;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
}
