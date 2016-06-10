/**
 * Alunos: 
 * Erik Zerbinatti      37816817851
 * Gabriel Malaquias    42143954840
 * Guilherme Ventura    43596980895
 * Luiz Fernando Santos 42547332833 
 * 
 * Curso: Ciï¿½ncia da Computaï¿½ï¿½o 7ï¿½ Semestre
 */

package io.valey.compilador.analise;

import java.io.IOException;

public class Sintatico {
	Lexico al;
	Mapa mapa = new Mapa();

	public Sintatico(String path) throws IOException {
		al = new Lexico(path);
		// this.executeTest();
	}

	public void execute() {
		derivaS();
	}

	/*
	 * S::= program id term BLOCO end_prog term
	 */
	public void derivaS() {
		Token t = al.nextToken();

		if (t.getCodigoToken() != TokenType.PROGRAM) {
			this.RegisterErrorSintatico("O programa deve iniciar com PROGRAM", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.ID) {
			this.RegisterErrorSintatico("Defina um nome para seu programa. Exemplo PROGRAM meuPrograma", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um ; na declaração do programa", t, true);
		}

		derivaBLOCO();

		t = al.nextToken();

		while (t.getCodigoToken() != TokenType.END_PROGRAM && t.getCodigoToken() != TokenType.EOF) {
			this.RegisterErrorSintatico("É esperado em END_PROGR não um " + t.getLexema(), t, true);
			t = al.nextToken();
		}

		if (t.getCodigoToken() != TokenType.END_PROGRAM) {
			this.RegisterErrorSintatico("É necessário fechar seu PROGRAM com END_PROG", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um ; após END_PROG", t, true);
		}
	}

	/*
	 * BLOCO::= begin CMDS end BLOCO::= CMD
	 */
	public void derivaBLOCO() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.BEGIN) {
			derivaCMDS();
			t = al.nextToken();
			if (t.getCodigoToken() != TokenType.END) {
				this.RegisterErrorSintatico("É esperado um END ao final de um bloco", t, true);
			}
		} else if (mapa.cmd.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaCMD();
		} else if (t.getCodigoToken() == TokenType.END_PROGRAM) {
			al.storeToken(t);
		} else {
			this.RegisterErrorSintatico("Não é possivel iniciar um bloco com " + t.getLexema(), t,
					(t.getCodigoToken() == TokenType.END_PROGRAM));
			derivaCMDS();
			t = al.nextToken();
			if (t.getCodigoToken() != TokenType.END) {
				this.RegisterErrorSintatico("É esperado um END ao final de um bloco", t, true);
			}
		}
	}

	/*
	 * CMDS::= REPW CMDS CMDS::= id IDFLW CMDS::= REPF CMDS CMDS::= if IFFLW
	 * CMDS::= declare DCFLW CMDS:= vazio
	 */
	public void derivaCMDS() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
			if (!t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " não foi declarado", t);
			}
			derivaIDFLW();
		} else if (t.getCodigoToken() == TokenType.IF) {
			derivaIFFLW();
		} else if (t.getCodigoToken() == TokenType.DECLARE) {
			derivaDCFLW();
		} else if (mapa.repw.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaREPW();
			derivaCMDS();
		} else if (mapa.repf.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaREPF();
			derivaCMDS();
		} else {
			al.storeToken(t);
			if (!mapa.cmds.follow.contains(t.getCodigoToken()))
				this.RegisterErrorSintatico("É esperado um END ao final de um bloco", t, false);
		}
	}

	/*
	 * IFFLW::= l_par EXPL r_par then BLOCO CNDB CMDS
	 */
	public void derivaIFFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() != TokenType.L_PAR) {
			this.RegisterErrorSintatico("É esperado um ( no inicio da expressão", t, true);
		}

		derivaEXPL();

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.R_PAR) {
			this.RegisterErrorSintatico("É esperado um ) no final da expressão", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.THEN) {
			this.RegisterErrorSintatico("Ao final de um IF utilize a palavra THEN", t, true);
		}

		derivaBLOCO();
		derivaCNDB();
		derivaCMDS();
	}

	/*
	 * IDFLW::= attrib_op EXP term CMDS
	 */
	public void derivaIDFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() != TokenType.ATTRIB_OP) {
			this.RegisterErrorSintatico("Falta de atribuição encontrada", t, true);
		}

		derivaEXP();

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um ;", t, true);
		}

		derivaCMDS();
	}

	/*
	 * DCFLW::= id type term CMDS
	 */
	public void derivaDCFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
			if (t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " já foi declarado", t);
			}
		} else {
			this.RegisterErrorSintatico("Defina um ID depois de DECLARE", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TYPE) {
			this.RegisterErrorSintatico("Defina um TIPO para a variável", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um ; depois do tipo de variavel", t, true);
		}

		derivaCMDS();
	}

	/*
	 * CMD::=REP CMD::=ATRIB CMD::= COND CMD::=DECL
	 */
	public void derivaCMD() {
		Token t = al.nextToken();
		if (mapa.rep.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaREP();
		} else if (mapa.atrib.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaATRIB();
		} else if (mapa.cond.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaCOND();
		} else if (mapa.decl.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaDECL();
		} else if (t.getCodigoToken() == TokenType.ATTRIB_OP) {
			al.storeToken(t);
			derivaATRIB();
		} else if (t.getCodigoToken() == TokenType.DECLARE || t.getCodigoToken() == TokenType.TYPE) {
			al.storeToken(t);
			derivaDECL();
		} else {
			al.storeToken(t);
			derivaCOND();
		}
	}

	/*
	 * DECL::= declare id type term
	 */
	public void derivaDECL() {
		Token t = al.nextToken();
		if (t.getCodigoToken() != TokenType.DECLARE) {
			this.RegisterErrorSintatico("Use DECLARE para declarar uma varivel", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
			if (t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " já foi declarado", t);
			}
		} else {
			this.RegisterErrorSintatico("Defina o ID depois de DECLARE", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TYPE) {
			this.RegisterErrorSintatico("Defina um TIPO para a variável", t, true);
		}

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um ; depois do tipo de variavel", t, true);
		}
	}

	/*
	 * COND::= if l_par EXPL r_par then BLOCO CNDB
	 */
	public void derivaCOND() {
		Token t = al.nextToken();
		if (t.getCodigoToken() != TokenType.IF) {
			this.RegisterErrorSintatico("Inicie uma condição com IF", t, true);
		}
		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.L_PAR) {
			this.RegisterErrorSintatico("É esperado um ( no inicio da expressão", t, true);
		}

		derivaEXPL();

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.R_PAR) {
			this.RegisterErrorSintatico("É esperado um ) no final da expressão", t, true);
		}
		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.THEN) {
			this.RegisterErrorSintatico("Ao final de um IF utilize a palavra THEN", t, true);
		}
		derivaBLOCO();
		derivaCNDB();
	}

	/*
	 * CNDB::= vazio CNDB::= else BLOCO
	 */
	public void derivaCNDB() {
		Token t = al.nextToken();
		if (t.getCodigoToken() != TokenType.ELSE)
			derivaBLOCO();
		else if (!mapa.cndb.follow.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * ATRIB::= id attrib_op EXP term
	 */
	public void derivaATRIB() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
			if (!t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " não foi declarado", t);
			}
		} else {
			this.RegisterErrorSintatico("É esperado uma variavel", t, true);
		}
		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.ATTRIB_OP) {
			this.RegisterErrorSintatico("É esperado uma atribuição depois da variavel", t, true);
		}
		derivaEXP();

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um ;", t, true);
		}
	}

	/*
	 * EXP::= l_par EXPN r_par GENFLW1 EXP::= id GENFLW EXP::= num_float GENFLW1
	 * EXP::= num_int GENFLW1 EXP::= logic_val LOGFLW EXP::= literal
	 */
	public void derivaEXP() {

		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.L_PAR) {
			derivaEXPN();
			t = al.nextToken();
			if (t.getCodigoToken() != TokenType.R_PAR) {
				this.RegisterErrorSintatico("É esperado um ) depois da expressão", t, true);
			}
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.ID) {
			if (!t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " não foi declarado", t);
			}
			derivaGENFLW();
		} else if (t.getCodigoToken() == TokenType.NUM_FLOAT || t.getCodigoToken() == TokenType.NUM_INT) {
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.LOGIC_VAL) {
			derivaLOGFLW();
		} else if (t.getCodigoToken() == TokenType.LITERAL) {
			return;
		}
	}

	/*
	 * EXPL::= l_par EXPN r_par GENFLW1 EXPL::= id GENFLW EXPL::= num_float
	 * GENFLW1 EXPL::= num_int GENFLW1 EXPL::= logic_val LOGFLW
	 */
	public void derivaEXPL() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.L_PAR) {
			derivaEXPN();
			t = al.nextToken();
			if (t.getCodigoToken() != TokenType.R_PAR) {
				this.RegisterErrorSintatico("É esperado um ) depois da expressão", t, true);
			}
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.ID) {
			if (!t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " não foi declarado", t);
			}
			derivaGENFLW();
		} else if (t.getCodigoToken() == TokenType.NUM_FLOAT || t.getCodigoToken() == TokenType.NUM_INT) {
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.LOGIC_VAL) {
			derivaLOGFLW();
		}
	}

	/*
	 * LOGFLW::= vazio LOGFLW::= logic_op EXPL
	 */
	public void derivaLOGFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXPL();
		} else if (!mapa.logflw.follow.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * GENFLW::= GENFLW1 GENFLW::= logic_op EXPL
	 */
	public void derivaGENFLW() {
		Token t = al.nextToken();
		if (mapa.genflw1.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXPL();
		} else {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
			derivaEXPL();
		}
	}

	/*
	 * GENFLW1::= TERMON1 EXPN1 GENFLW2
	 */
	public void derivaGENFLW1() {
		Token t = al.nextToken();
		if (mapa.termon1.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaTERMON1();
			derivaEXPN1();
			derivaGENFLW2();
		} else {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * GENFLW2::= vazio GENFLW2::= rel_op EXPN GENFLW3
	 */
	public void derivaGENFLW2() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXPN();
			derivaGENFLW3();
		} else if (!mapa.genflw2.follow.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * GENFLW3::= vazio GENFLW3::= logic_op EXPR
	 */
	public void derivaGENFLW3() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXP();
		} else if (!mapa.genflw3.follow.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * EXPR::= EXPN rel_op EXPN
	 */
	public void derivaEXPR() {
		Token t = al.nextToken();
		if (!mapa.expn.first.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, false);
		}
		al.storeToken(t);
		derivaEXPN();
		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.REL_OP) {
			this.RegisterErrorSintatico("É esperado um operador de relação " + t.getLexema(), t, true);
		}
	}

	/*
	 * EXPN::= TERMON EXPN1
	 */
	public void derivaEXPN() {
		Token t = al.nextToken();
		if (!mapa.termon.first.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, false);
		}
		al.storeToken(t);
		derivaTERMON();
		derivaEXPN1();
	}

	/*
	 * EXPN1::= vazio EXPN1::=addsub_op TERMON EXPN1
	 */
	public void derivaEXPN1() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ADDSUB_OP) {
			derivaTERMON();
			derivaEXPN1();
		} else if (!mapa.expn1.follow.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * TERMON::= VALN TERMON1
	 */
	public void derivaTERMON() {
		Token t = al.nextToken();
		if (!mapa.valn.first.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, false);
		}
		al.storeToken(t);
		derivaVALN();
		derivaTERMON1();
	}

	/*
	 * TERMON1::= vazio 
	 * TERMON1::= multdiv_op VALN TERMON1
	 */
	public void derivaTERMON1() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.MULTDIV_OP) {
			derivaVALN();
			derivaTERMON1();
		} else if (!mapa.termon1.follow.contains(t.getCodigoToken())) {
			this.RegisterErrorSintatico("Erro ao processar o lexema " + t.getLexema(), t, true);
		}
	}

	/*
	 * VALN::= l_par EXPN r_par 
	 * VALN::= id 
	 * VALN::= num_float 
	 * VALN::= num_int
	 */
	public void derivaVALN() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.L_PAR) {

		} else if (t.getCodigoToken() == TokenType.ID) {
			if (!t.isDeclarado()) {
				RegisterErrorSemantico("ID " + t.getLexema() + " não foi declarado", t);
			}

		} else if (t.getCodigoToken() == TokenType.NUM_FLOAT) {

		} else if (t.getCodigoToken() == TokenType.NUM_INT) {

		} else {
			// TODO::
		}
	}

	/*
	 * REP::=REPW REP::= REPF
	 */
	public void derivaREP() {
		Token t = al.nextToken();
		if (mapa.repw.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaREPW();
		} else if (mapa.repf.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaREPF();
		} else {
			// TODO::
		}
	}

	/*
	 * REPF::= for id attrib_op EXPN to EXPN BLOCO
	 */
	public void derivaREPF() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.FOR) {
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.ID) {
				if (!t.isDeclarado()) {
					RegisterErrorSemantico("ID " + t.getLexema() + " não foi declarado", t);
				}
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.ATTRIB_OP) {
					derivaEXPN();
					t = al.nextToken();
					if (t.getCodigoToken() == TokenType.TO) {
						derivaEXPN();
						derivaBLOCO();
					} else {
						// TODO::
					}
				} else {
					// TODO::
				}
			} else {
				// TODO::
			}
		} else {
			// TODO::
		}
	}

	/*
	 * REPW::= while l_par EXPL r_par BLOCO
	 */
	public void derivaREPW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.WHILE) {
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.L_PAR) {
				derivaEXPL();
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.R_PAR) {
					derivaBLOCO();
				} else {
					// TODO::
				}
			} else {
				// TODO::
			}
		} else {
			// TODO::
		}
	}

	public void RegisterErrorSintatico(String msg, Token t, Boolean storeToken) {
		Error e = new Error(msg, t.getLinha(), t.getColuna(), TipoErro.Sintatico);
		if (storeToken)
			al.storeToken(t);

		ErrorHandler.addError(e);
	}

	public void RegisterErrorSemantico(String msg, Token t, Boolean storeToken) {
		Error e = new Error(msg, t.getLinha(), t.getColuna(), TipoErro.Sintatico);
		if (storeToken)
			al.storeToken(t);

		ErrorHandler.addError(e);
	}

	public void RegisterErrorSintatico(String msg, Token t) {
		this.RegisterErrorSintatico(msg, t, false);
	}

	public void RegisterErrorSemantico(String msg, Token t) {
		this.RegisterErrorSemantico(msg, t, false);
	}

	public void executeTest() {
		Token t = null;

		System.out.println("-------------------------------------------------");
		System.out.println("|          TOKENS DO ANALISADOR LEXICO          |");
		System.out.println("-------------------------------------------------");
		System.out.println("|  #  |    TIPO    |    LEXEMA    |   POSICAO   |");
		System.out.println("-------------------------------------------------");
		int i = 0;
		do {
			i++;
			t = al.nextToken();

			System.out.println(
					"|" + StringHelper.padRight(i, 5) + "|" + StringHelper.padRight(t.getCodigoToken().toString(), 12)
							+ "|" + StringHelper.padRight(t.getLexema(), 14) + "|"
							+ StringHelper.padRight(t.getLinhaColuna(), 13) + "|");
		} while (t.getCodigoToken() != TokenType.EOF);
		System.out.println("-------------------------------------------------");
	}
}
