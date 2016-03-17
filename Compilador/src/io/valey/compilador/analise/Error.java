package io.valey.compilador.analise;

public class Error {

	private String mensagem;

	private int linha;
	
	private int coluna;
	
	private TipoErro tipo; 
	
	public Error(String mensagem, int linha, int coluna, TipoErro tipo){
		this.mensagem = mensagem;
		this.linha = linha;
		this.coluna = coluna;
		this.tipo = tipo;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public TipoErro getTipo() {
		return tipo;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public void setTipo(TipoErro tipo) {
		this.tipo = tipo;
	}
}