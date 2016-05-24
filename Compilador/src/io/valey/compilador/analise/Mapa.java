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
	}
	

}
