package br.dominioL.sisPro.dados.couch;

import br.dominioL.sisPro.dados.BancoDeDados;
import br.dominioL.sisPro.dados.couch.RequisicaoCouch;
import br.dominioL.sisPro.dados.couch.RespostaCouch;

public final class Couch implements BancoDeDados<RespostaCouch, RequisicaoCouch> {
	private static Couch INSTANCIA;
	private static final Integer PORTA = 8000;
	private static final String ENDERECO = "localhost";
	private static final String CAMINHO = "sisPro";

	private Couch() {
		
	}

	public static final Couch fornecerInstancia() {
		return (INSTANCIA != null) ? (INSTANCIA) : (INSTANCIA = new Couch());
	}

	public void popular() {
		
	}

	public RespostaCouch adicionar(RequisicaoCouch consulta) {
		return null;
		//TODO
	}

	public RespostaCouch alterar(RequisicaoCouch consulta) {
		return null;
		//TODO
	}

	public RespostaCouch remover(RequisicaoCouch consulta) {
		return null;
		//TODO
	}

	public RespostaCouch buscar(RequisicaoCouch consulta) {
		return null;
		//TODO
	}
}
