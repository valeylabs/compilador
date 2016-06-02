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

public class Token {

	private String lexema;

	private TokenType codigoToken;

	private int linha;

	private int coluna;
	
	private Boolean declarado;

	public Token(String lexema, TokenType tipo, int linha, int coluna) {
		this.lexema = lexema;
		this.codigoToken = tipo;
		this.linha = linha;
		this.coluna = coluna;
	}

	public Token(String lexema, TokenType tipo) {
		this(lexema, tipo, 0, 0);
	}

	public Token(char lexema, TokenType tipo) {
		this(String.valueOf(lexema), tipo, 0, 0);
	}

	public Token(char lexema, TokenType tipo, int linha, int coluna) {
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

	public String getLinhaColuna() {
		return "(" + this.getLinha() + "," + this.getColuna() + ")";
	}

	public void printToken() {
		System.out.println("|" + StringHelper.padRight(this.getLexema(), 16) + "|"
				+ StringHelper.padRight(this.getCodigoToken().toString(), 14) + "|");
	}

	public void setLinhaColuna(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void setDeclarado(Boolean b){
		this.declarado = b;
	}
	
	public Boolean isDeclarado(){
		return this.declarado;
	}
}
