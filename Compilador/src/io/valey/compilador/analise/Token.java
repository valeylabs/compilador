package io.valey.compilador.analise;

public class Token {

	private String lexema;
	
	private TokenType codigoToken;
	
	private int linha;
	
	private int coluna;

	public Token(String lexema, TokenType tipo, int linha, int coluna){
		this.lexema = lexema;
		this.codigoToken = tipo;
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public Token(String lexema, TokenType tipo){
		this(lexema, tipo, 0, 0);
	}
	
	public Token(char lexema, TokenType tipo){
		this(String.valueOf(lexema), tipo, 0, 0);
	}
	
	public Token(char lexema, TokenType tipo, int linha, int coluna){
		this(String.valueOf(lexema), tipo, linha, coluna);
	}
	
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public String getLexema() {
		return lexema;
	}

	public TokenType getCodigoToken() {
		return codigoToken;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	public String getLinhaColuna(){
		return "("+this.getLinha()+","+this.getColuna()+")";
	}
	
	public void printToken(){
		System.out.println("Chave " + this.getLexema());
	    System.out.println("TipoToken " + this.getCodigoToken());
	    System.out.println("Lexema "  + this.getLexema());
	    System.out.println("----------------------------------------");
	}


	public void setLinhaColuna(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
}
