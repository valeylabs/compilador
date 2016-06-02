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
			this.RegisterErrorSintatico("É esperado um terminal na declaração do programa", t, true);
		}

		derivaBLOCO();

		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.END_PROGRAM) {
			this.RegisterErrorSintatico("É necessário fechar seu PROGRAM com END_PROG", t, true);
		}
		
		t = al.nextToken();
		if (t.getCodigoToken() != TokenType.TERM) {
			this.RegisterErrorSintatico("É esperado um terminal após END_PROG", t, true);
		}
	}

	/*
	 * BLOCO::= begin CMDS end BLOCO::=CMD
	 */
	public void derivaBLOCO() {
		Token t = al.nextToken();
		if (t.getCodigoToken() != TokenType.BEGIN) {
			derivaCMDS();
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.END) {

			} else {
				// TODO::
			}
		} else if (mapa.cmd.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaCMD();
		} else {
			// TODO::
		}
	}

	/*
	 * CMDS::= REPW CMDS CMDS::= id IDFLW CMDS::= REPF CMDS CMDS::= if IFFLW
	 * CMDS::= declare DCFLW CMDS:= vazio
	 */
	public void derivaCMDS() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
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
		} else if (mapa.cmds.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// TODO::
		}
	}

	/*
	 * IFFLW::= l_par EXPL r_par then BLOCO CMDS
	 */
	public void derivaIFFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.L_PAR) {
			derivaEXPL();
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.R_PAR) {
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.THEN) {
					derivaBLOCO();
					derivaCMDS();
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
	 * IDFLW::= attrib_op EXP term CMDS
	 */
	public void derivaIDFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ATTRIB_OP) {
			derivaEXP();
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.TERM) {
				derivaCMDS();
			} else {
				// TODO::
			}
		} else {
			// TODO::
		}
	}

	/*
	 * DCFLW::= id type term CMDS
	 */
	public void derivaDCFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.TYPE) {
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.TERM) {
					derivaCMDS();
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
		} else {
			// TODO::
		}
	}

	/*
	 * DECL::= declare id type term
	 */
	public void derivaDECL() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.DECLARE) {
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.ID) {
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.TYPE) {
					t = al.nextToken();
					if (t.getCodigoToken() == TokenType.TERM) {

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
	 * COND::= if l_par EXPL r_par then BLOCO CNDB
	 */
	public void derivaCOND() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.IF) {
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.L_PAR) {
				derivaEXPL();
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.R_PAR) {
					t = al.nextToken();
					if (t.getCodigoToken() == TokenType.THEN) {
						derivaBLOCO();
						derivaCNDB();
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
	 * CNDB::= vazio CNDB::= else BLOCO
	 */
	public void derivaCNDB() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ELSE) {

		} else if (mapa.cndb.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// todo::
		}
	}

	/*
	 * ATRIB::= id attrib_op EXP term
	 */
	public void derivaATRIB() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ID) {
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.ATTRIB_OP) {
				derivaEXP();
				t = al.nextToken();
				if (t.getCodigoToken() == TokenType.TERM) {

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
	 * EXP::= l_par EXPN r_par GENFLW1, EXP::= id GENFLW EXP::= num_float
	 * GENFLW1 EXP::= num_int GENFLW1 EXP::= logic_val LOGFLW EXP::= literal
	 */
	public void derivaEXP() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.L_PAR) {
			derivaEXPN();
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.R_PAR) {
				derivaGENFLW1();
			} else {
				// TODO::
			}
		} else if (t.getCodigoToken() == TokenType.ID) {
			derivaGENFLW();
		} else if (t.getCodigoToken() == TokenType.NUM_FLOAT || t.getCodigoToken() == TokenType.NUM_INT) {
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.LOGIC_VAL) {
			derivaLOGFLW();
		} else if (t.getCodigoToken() == TokenType.LITERAL) {

		} else {
			// TODO::
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
			if (t.getCodigoToken() == TokenType.R_PAR) {
				derivaGENFLW1();
			} else {
				// TODO::
			}
		} else if (t.getCodigoToken() == TokenType.ID) {
			derivaGENFLW();
		} else if (t.getCodigoToken() == TokenType.NUM_FLOAT || t.getCodigoToken() == TokenType.NUM_INT) {
			derivaGENFLW1();
		} else if (t.getCodigoToken() == TokenType.LOGIC_VAL) {
			derivaLOGFLW();
		} else {
			// TODO::
		}
	}

	/*
	 * LOGFLW::= vazio LOGFLW::= logic_op EXPL
	 */
	public void derivaLOGFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXPL();
		} else if (mapa.logflw.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// TODO::
		}
	}

	/*
	 * GENFLW::= GENFLW1 GENFLW::= logic_op EXPL
	 */
	public void derivaGENFLW() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXPL();
		} else if (mapa.genflw1.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaGENFLW1();
		} else {
			// TODO::
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
			// TODO::
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
		} else if (mapa.genflw2.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// TODO::
		}
	}

	/*
	 * GENFLW3::= vazio GENFLW3::= logic_op EXPR
	 */
	public void derivaGENFLW3() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.LOGIC_OP) {
			derivaEXP();
		} else if (mapa.genflw3.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// TODO::
		}
	}

	/*
	 * EXPR::= EXPN rel_op EXPN
	 */
	public void derivaEXPR() {
		Token t = al.nextToken();
		if (mapa.expn.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaEXPN();
			t = al.nextToken();
			if (t.getCodigoToken() == TokenType.REL_OP) {
				derivaEXPN();
			} else {
				// TODO::
			}
		} else {
			// TODO:
		}
	}

	/*
	 * EXPN::= TERMON EXPN1
	 */
	public void derivaEXPN() {
		Token t = al.nextToken();
		if (mapa.termon.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaTERMON();
			derivaEXPN1();
		} else {
			// TODO::
		}
	}

	/*
	 * EXPN1::= vazio EXPN1::=addsub_op TERMON EXPN1
	 */
	public void derivaEXPN1() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.ADDSUB_OP) {
			derivaTERMON();
			derivaEXPN1();
		} else if (mapa.expn1.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// TODO::
		}
	}

	/*
	 * TERMON::= VALN TERMON1
	 */
	public void derivaTERMON() {
		Token t = al.nextToken();
		if (mapa.valn.first.contains(t.getCodigoToken())) {
			al.storeToken(t);
			derivaVALN();
			derivaTERMON1();
		} else {
			// TODO::
		}
	}

	/*
	 * TERMON1::= vazio TERMON1::= multdiv_op VALN TERMON1
	 */
	public void derivaTERMON1() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.MULTDIV_OP) {
			derivaVALN();
			derivaTERMON1();
		} else if (mapa.termon1.follow.contains(t.getCodigoToken())) {
			al.storeToken(t);
		} else {
			// TODO::
		}
	}

	/*
	 * VALN::= l_par EXPN r_par VALN::= id VALN::= num_float VALN::= num_int
	 */
	public void derivaVALN() {
		Token t = al.nextToken();
		if (t.getCodigoToken() == TokenType.L_PAR) {

		} else if (t.getCodigoToken() == TokenType.ID) {

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
