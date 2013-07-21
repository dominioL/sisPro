package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;

import br.dominioL.sisPro.modelo.Cliente;

public final class RepositorioDeClientes implements RepositorioCouch<Cliente> {
	private static RepositorioDeClientes INSTANCIA;
	private static final TextoJson TIPO = Json.criarTexto("cliente");
	private static final String IDENTIFICADOR_TIPO = "tipo";

	private RepositorioDeClientes() {
		//TODO
	}

	public static RepositorioDeClientes fornecerInstancia() {
		return (INSTANCIA == null) ? (INSTANCIA = new RepositorioDeClientes()) : INSTANCIA;
	}

	@Override
	public RespostaCouch inserir(Cliente cliente) {
		ObjetoJson documento = cliente.comoJson();
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
