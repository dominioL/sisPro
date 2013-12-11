package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;
import br.dominioL.estruturados.json.ValorJson;

import br.dominioL.sisPro.modelo.Cliente;

public final class RepositorioDeClientes implements RepositorioCouch<Cliente> {
	private static RepositorioDeClientes INSTANCIA;
	private static final TextoJson TIPO = Json.criarTexto("cliente");
	private static final IdentificadorJson IDENTIFICADOR_TIPO = Json.criarIdentificador("tipo");
	private static final IdentificadorJson IDENTIFICADOR_ID = Json.criarIdentificador("id");
	private static final IdentificadorJson IDENTIFICADOR_IDENTIFICADOR = Json.criarIdentificador("identificador");
	private static final IdentificadorJson IDENTIFICADOR_REV = Json.criarIdentificador("rev");
	private static final IdentificadorJson IDENTIFICADOR_REVISAO = Json.criarIdentificador("revisao");

	private RepositorioDeClientes() {}

	public static RepositorioDeClientes fornecerInstancia() {
		return (INSTANCIA == null) ? (INSTANCIA = new RepositorioDeClientes()) : INSTANCIA;
	}

	@Override
	public RespostaCouch inserir(Cliente cliente) {
		ObjetoJson documento = cliente.comoJson();
		documento.inserir(IDENTIFICADOR_TIPO, TIPO);
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comDocumento(documento);
		return Couch.fornecerInstancia().inserir(requisicao);
	}

	@Override
	public RespostaCouch colocar(Cliente cliente) {
		ObjetoJson documento = cliente.comoJson();
		ValorJson identificador = documento.fornecer(IDENTIFICADOR_IDENTIFICADOR);
		ValorJson revisao = documento.fornecer(IDENTIFICADOR_REVISAO);
		documento.remover(IDENTIFICADOR_IDENTIFICADOR);
		documento.remover(IDENTIFICADOR_REVISAO);
		documento.inserir(IDENTIFICADOR_ID, identificador);
		documento.inserir(IDENTIFICADOR_REV, revisao);
		documento.inserir(IDENTIFICADOR_TIPO, TIPO);
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comDocumento(documento)
			.comIdentificador(identificador.comoTexto());
		return Couch.fornecerInstancia().colocar(requisicao);
	}

	@Override
	public RespostaCouch obter(String identificador) {
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comIdentificador(identificador);
		return Couch.fornecerInstancia().obter(requisicao);
	}

	@Override
	public RespostaCouch remover(String identificador) {
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comIdentificador(identificador);
		return Couch.fornecerInstancia().remover(requisicao);
	}

	@Override
	public void popular() {}
}
