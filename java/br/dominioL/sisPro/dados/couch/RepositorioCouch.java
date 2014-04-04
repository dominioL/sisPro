package br.dominioL.sisPro.dados.couch;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;
import br.dominioL.estruturados.json.ValorJson;

import br.dominioL.sisPro.modelo.Entidade;

public abstract class RepositorioCouch<T extends Entidade<T>> {
	private static final IdentificadorJson IDENTIFICADOR_TIPO = Json.criarIdentificador("tipo");
	private static final IdentificadorJson IDENTIFICADOR_ID = Json.criarIdentificador("id");
	private static final IdentificadorJson IDENTIFICADOR_IDENTIFICADOR = Json.criarIdentificador("identificador");
	private static final IdentificadorJson IDENTIFICADOR_REV = Json.criarIdentificador("rev");
	private static final IdentificadorJson IDENTIFICADOR_REVISAO = Json.criarIdentificador("revisao");

	public final RespostaCouch inserir(T entidade) {
		ObjetoJson documento = entidade.comoJson();
		adicionarTipoDoCouch(documento);
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comDocumento(documento);
		RespostaCouch resposta = Couch.fornecerInstancia().inserir(requisicao);
		return ajustarResposta(resposta);
	}

	public final RespostaCouch colocar(T entidade) {
		ObjetoJson documento = entidade.comoJson();
		ValorJson identificador = adicionarAtributosDoCouch(documento);
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comDocumento(documento)
			.comIdentificador(identificador.comoTexto());
		RespostaCouch resposta = Couch.fornecerInstancia().colocar(requisicao);
		return ajustarResposta(resposta);
	}

	public final RespostaCouch obter(String identificador) {
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comIdentificador(identificador);
		RespostaCouch resposta = Couch.fornecerInstancia().obter(requisicao);
		return ajustarResposta(resposta);
	}

	public final RespostaCouch remover(String identificador) {
		RequisicaoCouch requisicao = RequisicaoCouch.criar()
			.comIdentificador(identificador);
		RespostaCouch resposta = Couch.fornecerInstancia().remover(requisicao);
		return ajustarResposta(resposta);
	}

	private final RespostaCouch ajustarResposta(RespostaCouch resposta) {
		if (resposta.possuiEntidade()) {
			ObjetoJson documentoRetornado = resposta.fornecerEntidade();
			removerAtributosDoCouch(documentoRetornado);
		}
		return resposta;
	}

	private final void adicionarTipoDoCouch(ObjetoJson documento) {
		documento.inserir(IDENTIFICADOR_TIPO, fornecerTipo());
	}

	private final void removerTipoDoCouch(ObjetoJson documento) {
		documento.remover(IDENTIFICADOR_TIPO);
	}

	private final ValorJson adicionarAtributosDoCouch(ObjetoJson documento) {
		ValorJson identificador = documento.fornecer(IDENTIFICADOR_IDENTIFICADOR);
		ValorJson revisao = documento.fornecer(IDENTIFICADOR_REVISAO);
		documento.remover(IDENTIFICADOR_IDENTIFICADOR);
		documento.remover(IDENTIFICADOR_REVISAO);
		documento.inserir(IDENTIFICADOR_ID, identificador);
		documento.inserir(IDENTIFICADOR_REV, revisao);
		adicionarTipoDoCouch(documento);
		return identificador;
	}

	private final ValorJson removerAtributosDoCouch(ObjetoJson documento) {
		ValorJson identificador = documento.fornecer(IDENTIFICADOR_ID);
		ValorJson revisao = documento.fornecer(IDENTIFICADOR_REV);
		documento.remover(IDENTIFICADOR_ID);
		documento.remover(IDENTIFICADOR_REV);
		documento.inserir(IDENTIFICADOR_IDENTIFICADOR, identificador);
		documento.inserir(IDENTIFICADOR_REVISAO, revisao);
		documento.remover(IDENTIFICADOR_TIPO);
		removerTipoDoCouch(documento);
		return identificador;
	}

	public abstract TextoJson fornecerTipo();

//	public abstract Mapeador fornecerMapeadorParaCouch();
//
//	public abstract Mapeador fornecerMapeadorParaEntidade();

	public abstract void popular();
}
