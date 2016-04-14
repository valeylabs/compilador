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

import java.io.EOFException;
import java.io.IOException;

import io.valey.compilador.FileLoader;
import sun.security.x509.IssuingDistributionPointExtension;

public class AnalisadorLexico {

	private static final int STATE_INITIAL = 1;

	private static final int STATE_ID = 2;

	private static final int STATE_ADDSUB_OP = 6;

	private static final int STATE_MULTDIV_OP = 7;

	private static final int STATE_ATTRIB_OP = 8;

	private static final int STATE_TERM = 9;

	private static final int STATE_L_PAR = 10;

	private static final int STATE_R_PAR = 11;

	private static final int STATE_QUOTE = 12;

	private static final int STATE_DIGIT_INITIAL = 41;
	private static final int STATE_DIGIT_2 = 42;
	private static final int STATE_DIGIT_3 = 43;
	private static final int STATE_DIGIT_4 = 44;
	private static final int STATE_DIGIT_5 = 45;
	private static final int STATE_DIGIT_6 = 46;
	private static final int STATE_DIGIT_7 = 47;
	private static final int STATE_DIGIT_8 = 48;

	private static final int STATE_REL_OP_INITIAL = 5;
	private static final int STATE_REL_OP_F = 13;
	private static final int STATE_REL_OP_T_OR_E = 14;
	private static final int STATE_REL_OP_Q = 15;
	private static final int STATE_REL_OP_FINAL = 16;
	private static final int STATE_COMMENT = 17;
	private static final int STATE_COMMENT_FINISH = 18;

	private FileLoader file;

	public AnalisadorLexico(String path) throws IOException {
		this.file = new FileLoader(path);
	}

	public Token nextToken() {

		int state = STATE_INITIAL;

		char c = ' ';

		int startLineLexema = file.getLine();
		int startColumnLexema = file.getColumn();

		StringBuilder lexema = new StringBuilder();

		try {
			while (true) {
				switch (state) {
				case STATE_INITIAL:
					lexema = new StringBuilder();
					do {
						c = this.file.getNextChar();
					} while (Character.isWhitespace(c));

					startLineLexema = file.getLine();
					startColumnLexema = file.getColumn();

					if (c == '+' || c == '-') {
						state = STATE_ADDSUB_OP;
						break;
					} else if (c == '*' || c == '/') {
						state = STATE_MULTDIV_OP;
						break;
					} else if (c == ';') {
						state = STATE_TERM;
						break;
					} else if (c == '(') {
						state = STATE_L_PAR;
						break;
					} else if (c == ')') {
						state = STATE_R_PAR;
						break;
					} else if (c == ':') {
						lexema.append(c);
						state = STATE_ATTRIB_OP;
						break;
					} else if (isLetter(c)) {
						lexema.append(c);
						state = STATE_ID;
						break;
					} else if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_INITIAL;
						break;
					} else if (isQuote(c)) {
						lexema.append(c);
						state = STATE_QUOTE;
						break;
					} else if (isDollar(c)) {
						lexema.append(c);
						state = STATE_REL_OP_INITIAL;
						break;
					} else if (c == FileLoader.EOF_CHAR) {
						return new Token("eof", TokenType.EOF, startLineLexema, startColumnLexema);
					} else {
						this.registerError(lexema.toString(), c);
						state = STATE_INITIAL;
						break;
					}
				case STATE_ADDSUB_OP:
					return new Token(c, TokenType.ADDSUB_OP, startLineLexema, startColumnLexema);
				case STATE_MULTDIV_OP:
					return new Token(c, TokenType.MULTDIV_OP, startLineLexema, startColumnLexema);
				case STATE_TERM:
					return new Token(c, TokenType.TERM, startLineLexema, startColumnLexema);
				case STATE_L_PAR:
					return new Token(c, TokenType.L_PAR, startLineLexema, startColumnLexema);
				case STATE_R_PAR:
					return new Token(c, TokenType.R_PAR, startLineLexema, startColumnLexema);
				case STATE_QUOTE:
					c = this.file.getNextChar();
					lexema.append(c);
					if (isQuote(c))
						return new Token(lexema.toString(), TokenType.LITERAL, startLineLexema, startColumnLexema);
					break;
				case STATE_ID:
					c = file.getNextChar();
					if (isLetter(c) || isDigit(c))
						lexema.append(c);
					else {
						this.file.rollbackChar();
						return SymbolTable.installToken(lexema, startLineLexema, startColumnLexema);
					}
					break;
				case STATE_ATTRIB_OP:
					c = this.file.getNextChar();

					if (c == '=')
						return new Token(lexema.append(c).toString(), TokenType.ATTRIB_OP, startLineLexema,
								startColumnLexema);
					else if (c == '[') {
						lexema.append(c);
						state = STATE_COMMENT;
					} else {
						this.registerError(lexema.toString(), c);
						this.file.rollbackChar();
						state = STATE_INITIAL;
					}

					break;
				case STATE_COMMENT:
					c = this.file.getNextChar();
					if (c == ']') {
						lexema.append(c);
						state = STATE_COMMENT_FINISH;
					}
					break;
				case STATE_COMMENT_FINISH:
					c = this.file.getNextChar();
					if (c == ':') {
						state = STATE_INITIAL;
					} else {
						lexema.append(c);
						state = STATE_COMMENT;
					}
					break;
				case STATE_REL_OP_INITIAL:
					c = this.file.getNextChar();
					if (c == 'g' || c == 'l') {
						lexema.append(c);
						state = STATE_REL_OP_T_OR_E;
					} else if (c == 'e') {
						lexema.append(c);
						state = STATE_REL_OP_Q;
					} else if (c == 'd') {
						lexema.append(c);
						state = STATE_REL_OP_F;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_REL_OP_T_OR_E:
					c = this.file.getNextChar();
					if (c == 't' || c == 'e') {
						lexema.append(c);
						state = STATE_REL_OP_FINAL;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_REL_OP_Q:
					c = this.file.getNextChar();
					if (c == 'q') {
						lexema.append(c);
						state = STATE_REL_OP_FINAL;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_REL_OP_F:
					c = this.file.getNextChar();
					if (c == 'f') {
						lexema.append(c);
						state = STATE_REL_OP_FINAL;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_REL_OP_FINAL:
					c = this.file.getNextChar();
					if (isDollar(c)) {
						return new Token(lexema.append(c).toString(), TokenType.REL_OP, startLineLexema,
								startColumnLexema);
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_DIGIT_INITIAL:
					c = file.getNextChar();
					if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_INITIAL;
					} else if (isDot(c)) {
						lexema.append(c);
						state = STATE_DIGIT_2;
					} else if (c == 'e') {
						lexema.append(c);
						state = STATE_DIGIT_3;
					} else {
						this.file.rollbackChar();
						return new Token(lexema.toString(), TokenType.NUM_INT, startLineLexema, startColumnLexema);
					}
					break;
				case STATE_DIGIT_2:
					c = file.getNextChar();
					if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_2;
					} else if (c == 'e') {
						lexema.append(c);
						state = STATE_DIGIT_6;
					} else {
						this.file.rollbackChar();
						return new Token(lexema.toString(), TokenType.NUM_FLOAT, startLineLexema, startColumnLexema);
					}
					break;
				case STATE_DIGIT_3:
					c = file.getNextChar();
					if (c == '+') {
						lexema.append(c);
						state = STATE_DIGIT_4;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_DIGIT_4:
					c = file.getNextChar();
					if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_5;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_DIGIT_5:
					c = file.getNextChar();
					if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_5;
					} else {
						this.file.rollbackChar();
						return new Token(lexema.toString(), TokenType.NUM_INT, startLineLexema, startColumnLexema);
					}
					break;
				case STATE_DIGIT_6:
					c = file.getNextChar();
					if (c == '+') {
						lexema.append(c);
						state = STATE_DIGIT_7;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_DIGIT_7:
					c = file.getNextChar();
					if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_8;
					} else {
						this.registerError(lexema.toString() + c, c);
						//this.file.rollbackChar();
						state = STATE_INITIAL;
					}
					break;
				case STATE_DIGIT_8:
					c = file.getNextChar();
					if (isDigit(c)) {
						lexema.append(c);
						state = STATE_DIGIT_8;
					} else {
						this.file.rollbackChar();
						return new Token(lexema.toString(), TokenType.NUM_FLOAT, startLineLexema, startColumnLexema);
					}
					break;
				}
			}
		} catch (EOFException e) {
			return new Token("eof", TokenType.EOF);
		} catch (IOException e) {

		}

		return new Token("eof", TokenType.EOF);
	}

	private void registerError(String lexema, char c) {
		ErrorHandler.addError(new Error("Unexpected char \"" + c + "\" in lexema: \"" + lexema + "\"",
				this.file.getLine(), this.file.getColumn(), TipoErro.Lexico));
	}

	private boolean isLetter(char c) {
		int dec = (int) c;

		return (dec > 64 && dec < 91) || (dec > 96 && dec < 123) || (dec == 95);
	}

	private boolean isDigit(char c) {
		int dec = (int) c;

		return (dec > 47 && dec < 58);
	}

	private boolean isDot(char c) {
		int dec = (int) c;
		return dec == 46;
	}

	private boolean isQuote(char c) {
		int dec = (int) c;
		return dec == 39;
	}

	private boolean isDollar(char c) {
		int dec = (int) c;
		return dec == 36;
	}

}
