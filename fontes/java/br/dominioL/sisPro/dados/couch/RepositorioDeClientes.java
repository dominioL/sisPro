package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;

public final class RepositorioDeClientes implements RepositorioCouch {
	private static RepositorioDeClientes INSTANCIA;
	private static final TextoJson TIPO = Json.criarTexto("cliente");
	private static final IdentificadorJson IDENTIFICADOR_TIPO = Json.criarIdentificador("tipo");

	private RepositorioDeClientes() {
		//TODO
	}

	public static RepositorioDeClientes fornecerInstancia() {
		return (INSTANCIA == null) ? (INSTANCIA = new RepositorioDeClientes()) : INSTANCIA;
	}

	@Override
	public RespostaCouch inserir(ObjetoJson documento) {
		documento.inserir(IDENTIFICADOR_TIPO, TIPO);
		RequisicaoCouch requisicao = RequisicaoCouch.criar().comDocumento(documento);
		return Couch.fornecerInstancia().adicionar(requisicao);
	}

	@Override
	public RespostaCouch remover(String identificador) {
		return null;
		//TODO
	}

	@Override
	public void popular() {
		//TODO
	}
}
