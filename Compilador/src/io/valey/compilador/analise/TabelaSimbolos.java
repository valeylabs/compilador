package io.valey.compilador.analise;

import java.util.HashMap;

public class TabelaSimbolos extends HashMap<String, Token> {
	private static TabelaSimbolos instance = new TabelaSimbolos();

	private TabelaSimbolos(){
		this.installKeyword(new Token("true",TipoToken.LOGIC_VAL));
		this.installKeyword(new Token("false",TipoToken.LOGIC_VAL));
		this.installKeyword(new Token("not",TipoToken.LOGIC_OP));
		this.installKeyword(new Token("and",TipoToken.LOGIC_OP));
		this.installKeyword(new Token("or",TipoToken.LOGIC_OP));
		this.installKeyword(new Token("bool",TipoToken.TYPE));
		this.installKeyword(new Token("text",TipoToken.TYPE));
		this.installKeyword(new Token("int",TipoToken.TYPE));
		this.installKeyword(new Token("program",TipoToken.PROGRAM));
		this.installKeyword(new Token("end_prog",TipoToken.END_PROGRAM));
		this.installKeyword(new Token("begin",TipoToken.BEGIN));
		this.installKeyword(new Token("end",TipoToken.END));
		this.installKeyword(new Token("if",TipoToken.IF));
		this.installKeyword(new Token("then",TipoToken.THEN));
		this.installKeyword(new Token("else",TipoToken.ELSE));
		this.installKeyword(new Token("for",TipoToken.FOR));
		this.installKeyword(new Token("while",TipoToken.WHILE));
		this.installKeyword(new Token("declare",TipoToken.DECLARE));
		this.installKeyword(new Token("to",TipoToken.TO));
	}
	
	public static TabelaSimbolos getInstance(){
		return TabelaSimbolos.instance;
	}
	
	public Token installToken(Token tk) throws Exception{
		
		if(tk.getCodigoToken() != TipoToken.ID)
			throw new Exception("Erro");
		
		
		if(this.containsKey(tk.getLexema()))
			return this.get(tk.getLexema());
		
		this.put(tk.getLexema(), tk);
		return tk;
		//procura na tabela 
		//se nao encontrar instala na tabela e retorna o token
		//se encontrar retorna o id 
	}
	
	private void installKeyword(Token tk){
		this.put(tk.getLexema(), tk);
	}
}
