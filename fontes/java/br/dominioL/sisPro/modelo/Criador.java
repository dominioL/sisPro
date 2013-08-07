package br.dominioL.sisPro.modelo;

import br.dominioL.conexaoH.Atributo;
import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.ConstrutorDeUri;
import br.dominioL.conexaoH.TipoDeMidia;

import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;

import br.dominioL.sisPro.dados.couch.RespostaCouch;
import br.dominioL.sisPro.dados.couch.RepositorioCouch;
import br.dominioL.sisPro.modelo.Entidade;

import javax.ws.rs.core.Response;

public final class Criador<T extends Entidade<T>> {
	private static final String ERRO_DADOS = "Dados inválidos.";
	private static final String ERRO_JSON = "Objeto Json inválido.";
	private static final String ERRO_BANCO = "Erro no banco de dados.";
	private static final IdentificadorJson MENSAGEM = Json.criarIdentificador("mensagemDeErro");
	private static final IdentificadorJson ERRO = Json.criarIdentificador("erro");
	private T entidade;
	private Class<?> classeDoRecurso;
	private Response resposta;

	public static <U extends Entidade<U>> Criador<U> criar(U entidade, Class<?> classeDoRecurso) {
		return new Criador<U>(entidade, classeDoRecurso);
	}

	private Criador(T entidade, Class<?> classeDoRecurso) {
		this.entidade = entidade;
		this.classeDoRecurso = classeDoRecurso;
	}

	public void fixarDados(String dados) {
		try {
			ObjetoJson dadosJson = Json.criarObjeto(dados);
			entidade.fixarDados(dadosJson);
		} catch (ExcecaoJsonDeAnalise excecao) {
			resposta = fornecerRespostaDeErroDoCliente(ERRO_JSON);
		}
	}

	public void validar() {
		if (!possuiErro()) {
			Boolean validacao = entidade.validar();
			if (!validacao) {
				resposta = fornecerRespostaDeErroDoCliente(ERRO_DADOS);
			}
		}
	}

	public void salvar() {
		if (!possuiErro()) {
			RespostaCouch respostaDoBanco = entidade.fornecerRepositorio().inserir(entidade);
			if (!respostaDoBanco.fornecerCodigoDeEstado().sucesso()) {
				resposta = fornecerRespostaDeErroDoServidor(respostaDoBanco.fornecerEntidade(), ERRO_BANCO);
			} else {
				String identificador = respostaDoBanco.fornecerIdentificador();
				ConstrutorDeUri localizacao = ConstrutorDeUri.criar(classeDoRecurso).substituirParametro(identificador);
				resposta = fornecerRespostaDeSucesso(respostaDoBanco.fornecerEntidade(), localizacao);
			}
		}
	}

	public Response fornecerResposta() {
		return resposta;
	}

	private Boolean possuiErro() {
		return (resposta != null);
	}

	private Response fornecerRespostaDeSucesso(ObjetoJson sucesso, ConstrutorDeUri localizacao) {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.JSON, sucesso.comoTextoJson(), localizacao.construirRelativo());
	}

	private Response fornecerRespostaDeErroDoCliente(String mensagemDeErro) {
		ObjetoJson respostaJson = Json.criarObjeto();
		respostaJson.inserir(MENSAGEM, Json.criarTexto(mensagemDeErro));
		return CodigoDeEstado.HTTP_400.fornecerResposta(TipoDeMidia.JSON, respostaJson.comoTextoJson());
	}

	private Response fornecerRespostaDeErroDoServidor(ObjetoJson erro, String mensagemDeErro) {
		ObjetoJson respostaJson = Json.criarObjeto();
		respostaJson.inserir(MENSAGEM, Json.criarTexto(mensagemDeErro));
		respostaJson.inserir(ERRO, erro);
		return CodigoDeEstado.HTTP_500.fornecerResposta(TipoDeMidia.JSON, respostaJson.comoTextoJson());
	}
}
