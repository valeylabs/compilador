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
	boolean lineBreak = false;

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

		this.column++;
	}

	public void roolbackChar() throws IOException {
		this.buffer.reset();
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}
}
