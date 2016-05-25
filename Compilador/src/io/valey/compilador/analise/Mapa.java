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

public class Mapa {

	FirstFollow s = new FirstFollow();
	FirstFollow bloco = new FirstFollow();
	FirstFollow cmds = new FirstFollow();
	FirstFollow ifflw = new FirstFollow();
	FirstFollow idflw= new FirstFollow();
	FirstFollow dcflw = new FirstFollow();
	FirstFollow cmd = new FirstFollow();
	FirstFollow decl = new FirstFollow();
	FirstFollow cond = new FirstFollow();
	FirstFollow cndb = new FirstFollow();
	FirstFollow atrib = new FirstFollow();
	FirstFollow exp = new FirstFollow();
	FirstFollow expl = new FirstFollow();
	FirstFollow logflw = new FirstFollow();
	FirstFollow genflw = new FirstFollow();
	FirstFollow genflw1 = new FirstFollow();
	FirstFollow genflw2 = new FirstFollow();
	FirstFollow genflw3 = new FirstFollow();
	FirstFollow expr = new FirstFollow();
	FirstFollow expn = new FirstFollow();
	FirstFollow expn1 = new FirstFollow();
	FirstFollow termon = new FirstFollow();
	FirstFollow termon1 = new FirstFollow();
	FirstFollow valn = new FirstFollow();
	FirstFollow rep = new FirstFollow();
	FirstFollow repf = new FirstFollow();
	FirstFollow repw = new FirstFollow();
	
	public Mapa(){
		// S
		s.first.add(TokenType.PROGRAM);
		s.follow.add(TokenType.EOF);
		
		// BLOCO
		bloco.first.add(TokenType.ID);
		bloco.first.add(TokenType.BEGIN);
		bloco.first.add(TokenType.DECLARE);
		bloco.first.add(TokenType.IF);
		bloco.first.add(TokenType.FOR);
		bloco.first.add(TokenType.WHILE);
		
		bloco.follow.add(TokenType.ID);
		bloco.follow.add(TokenType.END_PROGRAM);
		bloco.follow.add(TokenType.END);
		bloco.follow.add(TokenType.DECLARE);
		bloco.follow.add(TokenType.IF);
		bloco.follow.add(TokenType.ELSE);
		bloco.follow.add(TokenType.FOR);
		bloco.follow.add(TokenType.WHILE);
		
		//CMDS
		cmds.first.add(TokenType.ID);
		cmds.first.add(TokenType.DECLARE);
		cmds.first.add(TokenType.IF);
		cmds.first.add(TokenType.FOR);
		cmds.first.add(TokenType.WHILE);
		
		cmds.follow.add(TokenType.END);
		
		//IFFLW
		ifflw.first.add(TokenType.L_PAR);
		
		ifflw.follow.add(TokenType.END);
		
		//IDFLW
		idflw.first.add(TokenType.ATTRIB_OP);
		
		idflw.follow.add(TokenType.END);
		
		//DCFLW
		dcflw.first.add(TokenType.ID);
		
		dcflw.follow.add(TokenType.END);
		
		//CMD
		cmd.first.add(TokenType.ID);
		cmd.first.add(TokenType.DECLARE);
		cmd.first.add(TokenType.IF);
		cmd.first.add(TokenType.FOR);
		cmd.first.add(TokenType.WHILE);
		
		cmd.follow.add(TokenType.ID);
		cmd.follow.add(TokenType.END_PROGRAM);
		cmd.follow.add(TokenType.END);
		cmd.follow.add(TokenType.DECLARE);
		cmd.follow.add(TokenType.IF);
		cmd.follow.add(TokenType.ELSE);
		cmd.follow.add(TokenType.FOR);
		cmd.follow.add(TokenType.WHILE);
		
		//DECL
		decl.first.add(TokenType.DECLARE);

		decl.follow.add(TokenType.ID);
		decl.follow.add(TokenType.END_PROGRAM);
		decl.follow.add(TokenType.END);
		decl.follow.add(TokenType.DECLARE);
		decl.follow.add(TokenType.IF);
		decl.follow.add(TokenType.ELSE);
		decl.follow.add(TokenType.FOR);
		decl.follow.add(TokenType.WHILE);
		
		//COND
		cond.first.add(TokenType.IF);

		cond.follow.add(TokenType.ID);
		cond.follow.add(TokenType.END_PROGRAM);
		cond.follow.add(TokenType.END);
		cond.follow.add(TokenType.DECLARE);
		cond.follow.add(TokenType.IF);
		cond.follow.add(TokenType.ELSE);
		cond.follow.add(TokenType.FOR);
		cond.follow.add(TokenType.WHILE);
		
		//CNDB
		cndb.first.add(TokenType.ELSE);

		cndb.follow.add(TokenType.ID);
		cndb.follow.add(TokenType.END_PROGRAM);
		cndb.follow.add(TokenType.END);
		cndb.follow.add(TokenType.DECLARE);
		cndb.follow.add(TokenType.IF);
		cndb.follow.add(TokenType.ELSE);
		cndb.follow.add(TokenType.FOR);
		cndb.follow.add(TokenType.WHILE);
		
		//ATRIB
		atrib.first.add(TokenType.ID);

		atrib.follow.add(TokenType.ID);
		atrib.follow.add(TokenType.END_PROGRAM);
		atrib.follow.add(TokenType.END);
		atrib.follow.add(TokenType.DECLARE);
		atrib.follow.add(TokenType.IF);
		atrib.follow.add(TokenType.ELSE);
		atrib.follow.add(TokenType.FOR);
		atrib.follow.add(TokenType.WHILE);
		
		//EXP
		exp.first.add(TokenType.ID);
		exp.first.add(TokenType.L_PAR);
		exp.first.add(TokenType.LOGIC_VAL);
		exp.first.add(TokenType.NUM_INT);
		exp.first.add(TokenType.NUM_FLOAT);
		exp.first.add(TokenType.LITERAL);
		
		exp.follow.add(TokenType.TERM);
		
		//EXPL
		expl.first.add(TokenType.ID);
		expl.first.add(TokenType.L_PAR);
		expl.first.add(TokenType.LOGIC_VAL);
		expl.first.add(TokenType.NUM_INT);
		expl.first.add(TokenType.NUM_FLOAT);
		
		expl.follow.add(TokenType.TERM);
		expl.follow.add(TokenType.R_PAR);
		
		//LOGFLW
		logflw.first.add(TokenType.LOGIC_OP);
		
		logflw.follow.add(TokenType.TERM);
		
		//GENFLW
		genflw.first.add(TokenType.LOGIC_OP);
		genflw.first.add(TokenType.REL_OP);
		genflw.first.add(TokenType.ADDSUB_OP);
		genflw.first.add(TokenType.MULTDIV_OP);
		
		genflw.follow.add(TokenType.TERM);
		genflw.follow.add(TokenType.R_PAR);
		
		//GENFLW1
		genflw1.first.add(TokenType.REL_OP);
		genflw1.first.add(TokenType.ADDSUB_OP);
		genflw1.first.add(TokenType.MULTDIV_OP);
		
		genflw1.follow.add(TokenType.TERM);
		genflw1.follow.add(TokenType.R_PAR);
		
		//GENFLW2
		genflw2.first.add(TokenType.REL_OP);
		
		genflw2.follow.add(TokenType.TERM);
		genflw2.follow.add(TokenType.R_PAR);
		
		//GENFLW3
		genflw3.first.add(TokenType.LOGIC_OP);
		
		genflw2.follow.add(TokenType.TERM);
		genflw2.follow.add(TokenType.R_PAR);
		
		//EXPR
		expr.first.add(TokenType.ID);
		expr.first.add(TokenType.L_PAR);
		expr.first.add(TokenType.NUM_INT);
		expr.first.add(TokenType.NUM_FLOAT);
		
		expr.follow.add(TokenType.TERM);
		expr.follow.add(TokenType.R_PAR);
		
		//EXPN
		expn.first.add(TokenType.ID);
		expn.first.add(TokenType.L_PAR);
		expn.first.add(TokenType.NUM_INT);
		expn.first.add(TokenType.NUM_FLOAT);
		
		expn.follow.add(TokenType.ID);
		expn.follow.add(TokenType.TERM);
		expn.follow.add(TokenType.BEGIN);
		expn.follow.add(TokenType.DECLARE);
		expn.follow.add(TokenType.IF);
		expn.follow.add(TokenType.R_PAR);
		expn.follow.add(TokenType.LOGIC_OP);
		expn.follow.add(TokenType.REL_OP);
		expn.follow.add(TokenType.FOR);
		expn.follow.add(TokenType.TO);
		expn.follow.add(TokenType.WHILE);
		
		//EXPN1
		expn1.first.add(TokenType.ADDSUB_OP);
		
		expn1.follow.add(TokenType.ID);
		expn1.follow.add(TokenType.TERM);
		expn1.follow.add(TokenType.BEGIN);
		expn1.follow.add(TokenType.DECLARE);
		expn1.follow.add(TokenType.IF);
		expn1.follow.add(TokenType.R_PAR);
		expn1.follow.add(TokenType.LOGIC_OP);
		expn1.follow.add(TokenType.REL_OP);
		expn1.follow.add(TokenType.FOR);
		expn1.follow.add(TokenType.TO);
		expn1.follow.add(TokenType.WHILE);
		
		//TERMON
		termon.first.add(TokenType.ID);
		termon.first.add(TokenType.L_PAR);
		termon.first.add(TokenType.NUM_INT);
		termon.first.add(TokenType.NUM_FLOAT);
		
		termon.follow.add(TokenType.ID);
		termon.follow.add(TokenType.TERM);
		termon.follow.add(TokenType.BEGIN);
		termon.follow.add(TokenType.DECLARE);
		termon.follow.add(TokenType.IF);
		termon.follow.add(TokenType.R_PAR);
		termon.follow.add(TokenType.LOGIC_OP);
		termon.follow.add(TokenType.REL_OP);
		termon.follow.add(TokenType.ADDSUB_OP);
		termon.follow.add(TokenType.FOR);
		termon.follow.add(TokenType.TO);
		termon.follow.add(TokenType.WHILE);
		
		//TERMON1	
		termon1.first.add(TokenType.MULTDIV_OP);
		
		termon1.follow.add(TokenType.ID);
		termon1.follow.add(TokenType.TERM);
		termon1.follow.add(TokenType.BEGIN);
		termon1.follow.add(TokenType.DECLARE);
		termon1.follow.add(TokenType.IF);
		termon1.follow.add(TokenType.R_PAR);
		termon1.follow.add(TokenType.LOGIC_OP);
		termon1.follow.add(TokenType.REL_OP);
		termon1.follow.add(TokenType.ADDSUB_OP);
		termon1.follow.add(TokenType.FOR);
		termon1.follow.add(TokenType.TO);
		termon1.follow.add(TokenType.WHILE);
		
		//VALN
		valn.first.add(TokenType.ID);
		valn.first.add(TokenType.L_PAR);
		valn.first.add(TokenType.NUM_INT);
		valn.first.add(TokenType.NUM_FLOAT);
		
		valn.follow.add(TokenType.ID);
		valn.follow.add(TokenType.TERM);
		valn.follow.add(TokenType.BEGIN);
		valn.follow.add(TokenType.DECLARE);
		valn.follow.add(TokenType.IF);
		valn.follow.add(TokenType.R_PAR);
		valn.follow.add(TokenType.LOGIC_OP);
		valn.follow.add(TokenType.REL_OP);
		valn.follow.add(TokenType.ADDSUB_OP);
		valn.follow.add(TokenType.MULTDIV_OP);
		valn.follow.add(TokenType.FOR);
		valn.follow.add(TokenType.TO);
		valn.follow.add(TokenType.WHILE);
		
		//REP
		rep.first.add(TokenType.FOR);
		rep.first.add(TokenType.WHILE);
		
		rep.follow.add(TokenType.ID);
		rep.follow.add(TokenType.END_PROGRAM);
		rep.follow.add(TokenType.END);
		rep.follow.add(TokenType.DECLARE);
		rep.follow.add(TokenType.IF);
		rep.follow.add(TokenType.ELSE);
		rep.follow.add(TokenType.FOR);
		rep.follow.add(TokenType.WHILE);
		
		//REPF
		repf.first.add(TokenType.FOR);
		
		repf.follow.add(TokenType.ID);
		repf.follow.add(TokenType.END_PROGRAM);
		repf.follow.add(TokenType.END);
		repf.follow.add(TokenType.DECLARE);
		repf.follow.add(TokenType.IF);
		repf.follow.add(TokenType.ELSE);
		repf.follow.add(TokenType.FOR);
		repf.follow.add(TokenType.WHILE);
		
		//REPW
		repw.first.add(TokenType.WHILE);
		
		repw.follow.add(TokenType.ID);
		repw.follow.add(TokenType.END_PROGRAM);
		repw.follow.add(TokenType.END);
		repw.follow.add(TokenType.DECLARE);
		repw.follow.add(TokenType.IF);
		repw.follow.add(TokenType.ELSE);
		repw.follow.add(TokenType.FOR);
		repw.follow.add(TokenType.WHILE);
	}
	

}
