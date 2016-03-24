package io.valey.compilador.analise;

import java.io.EOFException;
import java.io.IOException;

import io.valey.compilador.FileLoader;

public class AnalisadorLexico {

	private static final int STATE_INITIAL = 1;

	private static final int STATE_ID = 2;

	private static final int STATE_DIGIT = 3;

	private static final int STATE_DIGIT_DOT = 4;

	private static final int STATE_REL_OP = 5;

	private static final int STATE_ADDSUB_OP = 6;

	private static final int STATE_MULTDIV_OP = 7;

	private static final int STATE_ATTRIB_OP = 8;

	private static final int STATE_TERM = 9;
	private static final int STATE_L_PAR = 10;
	private static final int STATE_R_PAR = 11;

	private FileLoader file;

	public AnalisadorLexico(String path) throws IOException {
		this.file = new FileLoader(path);
	}

	public Token nextToken() {

		int state = STATE_INITIAL;

		char c = ' ';

		StringBuilder lexema = new StringBuilder();

		try {
			while (true) {
				switch (state) {
				case STATE_INITIAL:
					c = this.file.getNextChar();

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
					}

					return new Token(c, TipoToken.ADDSUB_OP, file.getLine(), file.getColumn());

				case STATE_ADDSUB_OP:
					return new Token(c, TipoToken.ADDSUB_OP, file.getLine(), file.getColumn());
				case STATE_MULTDIV_OP:
					return new Token(c, TipoToken.MULTDIV_OP, file.getLine(), file.getColumn());
				case STATE_TERM:
					return new Token(c, TipoToken.TERM, file.getLine(), file.getColumn());
				case STATE_L_PAR:
					return new Token(c, TipoToken.L_PAR, file.getLine(), file.getColumn());
				case STATE_R_PAR:
					return new Token(c, TipoToken.R_PAR, file.getLine(), file.getColumn());
				case STATE_ID:
					c = this.file.getNextChar();
					if (!isLetter(c) && !isDigit(c, false)) {
						this.file.rollbackChar();
						return SymbolTable.installToken(lexema, file.getLine(), file.getColumn());
					}
					break;
				case STATE_ATTRIB_OP:
					c = this.file.getNextChar();

					if (c == '=')
						return new Token(lexema.append(c).toString(), TipoToken.R_PAR, file.getLine(),
								file.getColumn());

					this.registerError(lexema.toString(), c);
					this.file.rollbackChar();
					state = STATE_INITIAL;
					break;
				}
			}
		} catch (EOFException e) {
			return new Token("eof", TipoToken.EOF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Token("eof", TipoToken.EOF);
	}

	private void registerError(String lexema, char c) {
		ErrorHandler.addError(
				new Error("Unexpected char " + c, this.file.getLine(), this.file.getColumn(), TipoErro.Lexico));
	}

	private boolean isLetter(char c) {
		int dec = (int) c;

		return (dec > 64 && dec < 91) || (dec > 96 && dec < 123) || (dec == 95);
	}

	private boolean isDigit(char c, boolean considerDot) {
		int dec = (int) c;

		return (dec > 47 && dec < 58) || (considerDot && dec == 46);
	}

}
