package io.valey.compilador;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
	
	public static final int EOF_CHAR = -1;
	public static final char LINE_FEED = '\n';
	public static final char CARRIAGE_RETURN = '\r';
	
	/**
	 * Armazena o arquivo para leitura
	 */
	public BufferedReader buffer;
	
	/**
	 * Armazena a linha atual da leitura
	 */
	private int line;
	
	/**
	 * Armazena a coluna atual da leitura
	 */
	private int column;
	
	/**
	 * Verifica se o ultimo caracter lido foi um LINE_FEED
	 */
	private boolean lineBreak = false;
	
	/**
	 * Armazena a ultima coluna armazenada para retorna de linha
	 */
	private int lastColumn;
	
	public FileLoader(String path) throws IOException{
		this.line = 1;
		this.column = 0;
		this.buffer = new BufferedReader(new FileReader(path));
	}

	/**
	 * Resgata o próximo caracter do buffer
	 * @return char 
	 * @throws IOException
	 * @throws EOFException
	 */
	public char getNextChar() throws IOException, EOFException {
		this.buffer.mark(1);

		int _char = this.buffer.read();
		char result_char;

		if (_char == EOF_CHAR)
			throw new EOFException("");
		
		result_char = (char) _char;

		this.countLineColumn(result_char);
		
		//TODO:: Remover
		/*if(result_char == FileLoader.LINE_FEED)
			result_char = '%';
		else if(result_char == FileLoader.CARRIAGE_RETURN)
			result_char = '*';*/
		//TODO:: Remover

		return result_char;
	}

	/**
	 * Responsável pela contagem de linhas e colunas
	 * @param _char
	 * @throws EOFException
	 * @throws IOException
	 */
	private void countLineColumn(char _char) throws EOFException, IOException {
		if (this.lineBreak) {
			this.line++;
			this.column = 0;
			this.lineBreak = false;
		}

		this.lastColumn = this.column;
		
		if (_char == LINE_FEED)
			this.lineBreak = true;

		this.column++;
	}

	/**
	 * Retorna um caracter e a contagem de linha e coluna 
	 * @throws IOException
	 */
	public void rollbackChar() throws IOException {
		this.buffer.reset();
		this.buffer.mark(1);
		char c = (char) this.buffer.read();
		if(c == LINE_FEED){
			this.column = this.lastColumn;
			this.lineBreak = false;
		}else{
			this.column--;
		}
		this.buffer.reset();
	}

	/** 
	 * @return Retorna o elemento atual em forma de string
	 */
	public String getElement(){
		return "(" + this.getLine() + "," + this.getColumn() + ")";
	}
	
	/**
	 * @return Retorna a linha atual
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * @return Retorna a coluna atual
	 */
	public int getColumn() {
		return column;
	}

}