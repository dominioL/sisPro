package br.dominioL.sisPro.recursos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;

import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

import br.dominioL.sisPro.dados.couch.RespostaCouch;
import br.dominioL.sisPro.dados.couch.RepositorioCouch;
import br.dominioL.sisPro.modelo.Entidade;

import javax.ws.rs.core.Response;

public class Recurso {
	private static final String ERRO_DADOS = "Dados inválidos.";
	private static final String ERRO_JSON = "Objeto Json inválido.";
	private static final String ERRO_BANCO = "Erro no banco de dados.";

	public <T extends Entidade> Response postarEntidadeJson(RepositorioCouch<T> repositorio, T entidade, String dados) {
		try {
			ObjetoJson dadosJson = Json.criarObjeto(dados);
			entidade.fixarDados(dadosJson);
			if (!entidade.validar()) {
				return fornecerRespostaDeErroDoCliente(ERRO_DADOS);
			}
			RespostaCouch respostaDoBanco = repositorio.inserir(entidade);
			if (!respostaDoBanco.fornecerCodigoDeEstado().sucesso()) {
				return fornecerRespostaDeErroDoServidor(ERRO_BANCO);
			}
			return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.JSON, respostaDoBanco.fornecerEntidade().comoTextoJson());
		} catch (ExcecaoJsonDeAnalise excecao) {
			return fornecerRespostaDeErroDoCliente(ERRO_JSON);
		}
	}

	public Response fornecerRespostaDeErroDoCliente(String mensagemDeErro) {
		ObjetoJson respostaJson = Json.criarObjeto();
		respostaJson.inserir(Json.criarIdentificador("mensagemDeErro"), Json.criarTexto(mensagemDeErro));
		return CodigoDeEstado.HTTP_400.fornecerResposta(TipoDeMidia.JSON, respostaJson.comoTextoJson());
	}

	public Response fornecerRespostaDeErroDoServidor(String mensagemDeErro) {
		ObjetoJson respostaJson = Json.criarObjeto();
		respostaJson.inserir(Json.criarIdentificador("mensagemDeErro"), Json.criarTexto(mensagemDeErro));
		return CodigoDeEstado.HTTP_500.fornecerResposta(TipoDeMidia.JSON, respostaJson.comoTextoJson());
	}
}
