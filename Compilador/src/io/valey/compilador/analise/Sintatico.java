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

import java.io.IOException;

public class Sintatico {
	Lexico al;
	Mapa mapa = new Mapa();

	public Sintatico(String path) throws IOException {
		al = new Lexico(path);
	}

	public void execute(){
		derivaS();
	}
	
	public void derivaS(){
		Token t = al.nextToken();
		
		if(t.getCodigoToken() == TokenType.PROGRAM){
			t = al.nextToken();
			if(t.getCodigoToken() == TokenType.ID){
				t = al.nextToken();
				if(t.getCodigoToken() == TokenType.TERM){
					derivaBLOCO();
					t = al.nextToken();
					if(t.getCodigoToken() == TokenType.END_PROGRAM){
						t = al.nextToken();
						if(t.getCodigoToken() == TokenType.TERM){

						}else{
							//TODO::
						}
					}else{
						//TODO::
					}
				}else{
					//TODO::
				}
			}else{
				//TODO::
			}
		}else{
			//registra o erro que nao achou o program
			derivaS();
		}
	}
	
	public void derivaBLOCO(){
		Token t = al.nextToken();
		if(t.getCodigoToken() == TokenType.BEGIN){
			derivaCMDS();
			t = al.nextToken();
			if(t.getCodigoToken() == TokenType.END){
				
			}
		}else if(mapa.cmd.first.contains(t)){
			derivaCMD();
		}else{
			//TODO::
		}
	}
	
	public void derivaCMDS(){
		Token t = al.nextToken();
		if(t.getCodigoToken() == TokenType.ID){
			derivaIDFLW();
		}else if(t.getCodigoToken() == TokenType.IF){
			derivaIFFLW();
		}else if(t.getCodigoToken() == TokenType.DECLARE){
			derivaDCFLW();
		}else if(mapa.repw.first.contains(t)){
			derivaREPW();
			derivaCMDS();
		}else if(mapa.cmds.follow.contains(t)){
			al.storeToken(t);
		}else{
			//TODO::
		}
	}
	
	public void derivaCMD(){
		Token t = al.nextToken();
		if(mapa.rep.first.contains(t)){
			derivaREP();
		}else if(mapa.atrib.first.contains(t)){
			derivaATRIB();
		}else if(mapa.cond.first.contains(t)){
			derivaCOND();
		}else if(mapa.decl.first.contains(t)){
			derivaDECL();
		}else{
			//TODO::
		}
	}

	public void derivaIDFLW(){
		Token t = al.nextToken();
		if(t.getCodigoToken() == TokenType.ATTRIB_OP){
			derivaEXP();
			t = al.nextToken();
			if(t.getCodigoToken() == TokenType.TERM){
				derivaCMDS();
			}else{
				//TODO::
			}
		}else{
			//TODO::
		}
	}

	public void derivaIFFLW(){
		Token t = al.nextToken();
		if(t.getCodigoToken() == TokenType.L_PAR){
			derivaEXPL();
			t = al.nextToken();
			if(t.getCodigoToken() == TokenType.R_PAR){
				t = al.nextToken();
				if(t.getCodigoToken() == TokenType.THEN){
					derivaBLOCO();
					derivaCMDS();
				}else{
					//TODO::					
				}
			}else{
				//TODO::
			}
		}else{
			//TODO::
		}
	}

	public void derivaDCFLW(){
		Token t = al.nextToken();
		if(t.getCodigoToken() == TokenType.ID){
			t = al.nextToken();
			if(t.getCodigoToken() == TokenType.TYPE){
				t = al.nextToken();
				if(t.getCodigoToken() == TokenType.TERM){
					derivaCMDS();
				}else{
					//TODO::
				}
			}else{
				//TODO::
			}
		}else{
			//TODO::
		}
	}

	public void derivaDECL(){
		Token t = al.nextToken();
		if(t.getCodigoToken() == TokenType.DECLARE){
			t = al.nextToken();
			if(t.getCodigoToken() == TokenType.ID){
				t = al.nextToken();
				if(t.getCodigoToken() == TokenType.TYPE){
					t = al.nextToken();
					if(t.getCodigoToken() == TokenType.TERM){
						
					}else{
						//TODO::
					}	
				}else{
					//TODO::
				}		
			}else{
				//TODO::
			}	
		}else{
			//TODO::
		}
	}
	
	
	public void derivaREPW(){}
	public void derivaREP(){}
	public void derivaATRIB(){}
	public void derivaCOND(){}
	
	public void derivaEXPL(){}
	public void derivaEXP(){}
	
	
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
