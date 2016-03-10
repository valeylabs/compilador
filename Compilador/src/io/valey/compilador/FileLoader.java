package io.valey.compilador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {

	public final int EOF_CHAR = -1;
	public BufferedReader buffer;
	private int line;
	private int column;
	private boolean lineBreak = false;
	private int lastColumn;

	public FileLoader(String path) throws IOException{
		this.line = 1;
		this.column = 0; //inicia em 0 para quando ler o primeiro inicializar
		this.buffer = new BufferedReader(new FileReader(path));
	}

	public char getNextChar() throws IOException, EOFException {
		this.buffer.mark(1);

		int _char = this.buffer.read();
		char result_char;

		if (_char == EOF_CHAR)
			throw new EOFException("");

		result_char = (char) _char;

		this.countLineColumn(result_char);

		return result_char;
	}

	private void countLineColumn(char _char) throws EOFException, IOException {
		if (this.lineBreak) {
			this.line++;
			this.column = 0;
			this.lineBreak = false;
		}

		if (_char == '\n')
			this.lineBreak = true;

		this.lastColumn = this.column;
		this.column++;
	}

	public void rollbackChar() throws IOException {
		this.buffer.reset();
		this.buffer.mark(1);
		char c = (char) this.buffer.read();
		if(c == '\n'){
			this.column = this.lastColumn;
			this.lastColumn = 0;
			this.line--;
		}else{
			this.column--;
		}
		this.buffer.reset();
	}

	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
}
/*package com.pilador.file;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

import com.pilador.log.Logger;

public class FileAccessor {
	public static final int LINE_FEED = 0x000A;
	public static final int CARRIAGE_RETURN = 0x000D;
	
	private static final int EOF_CODE = -1;
	
	private BufferedReader file;
	private int line;
	private int column;
	private boolean lineBreak;

	public FileAccessor(String path) throws IOException {
		this.file = new BufferedReader(new FileReader(path));
		this.line = 1;
		this.column = 0;
	}

	public char getNextChar() throws EOFException, IOException {
		// marca o ponto de retorno para o reset
		this.file.mark(1);
		int characterNumber = this.file.read();
		// -1 = EOF
		if (characterNumber == EOF_CODE)
			throw new EOFException();

		column++;

		char character = (char) characterNumber;

		if (character == LINE_FEED) {
			lineBreak = true;
		}
		
		if(lineBreak){
			line++;
			column = 0;
			lineBreak = false;
		}

		return (char) character;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public void rollbackChar() throws IOException {
		this.file.reset();
	}
}
*/