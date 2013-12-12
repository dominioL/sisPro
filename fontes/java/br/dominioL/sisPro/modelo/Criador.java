package br.dominioL.sisPro.modelo;

import javax.ws.rs.core.Response;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.ConstrutorDeUri;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.SisPro;
import br.dominioL.sisPro.dados.couch.RepositorioCouch;
import br.dominioL.sisPro.dados.couch.RespostaCouch;

public final class Criador<T extends Entidade<T>> {
	private static final String ERRO_DADOS = "Dados inválidos.";
	private static final String ERRO_JSON = "Objeto Json inválido.";
	private static final String ERRO_BANCO = "Erro no banco de dados.";
	private static final IdentificadorJson IDENTIFICADOR_MENSAGEM = Json.criarIdentificador("mensagemDeErro");
	private static final IdentificadorJson IDENTIFICADOR_ERRO = Json.criarIdentificador("erro");

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
			RepositorioCouch<T> repositorio = entidade.fornecerRepositorio();
			RespostaCouch respostaDoBanco = repositorio.inserir(entidade);
			ObjetoJson entidadeDaResposta = respostaDoBanco.fornecerEntidade();
			CodigoDeEstado codigoDeEstadoDaResposta = respostaDoBanco.fornecerCodigoDeEstado();
			if (!codigoDeEstadoDaResposta.sucesso()) {
				resposta = fornecerRespostaDeErroDoServidor(entidadeDaResposta, ERRO_BANCO);
			} else {
				String identificador = respostaDoBanco.fornecerIdentificador();
				ConstrutorDeUri localizacao = SisPro.fornecerInstancia().fornecerConstrutorDeUri()
					.caminho(classeDoRecurso)
					.substituirParametro(identificador);
				resposta = fornecerRespostaDeSucesso(entidadeDaResposta, localizacao);
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
		return CodigoDeEstado.HTTP_201.fornecerResposta(TipoDeMidia.JSON, sucesso.comoTextoJson(), localizacao.construirAbsoluto());
	}

	private Response fornecerRespostaDeErroDoCliente(String mensagemDeErro) {
		ObjetoJson respostaJson = Json.criarObjeto();
		respostaJson.inserir(IDENTIFICADOR_MENSAGEM, Json.criarTexto(mensagemDeErro));
		return CodigoDeEstado.HTTP_400.fornecerResposta(TipoDeMidia.JSON, respostaJson.comoTextoJson());
	}

	private Response fornecerRespostaDeErroDoServidor(ObjetoJson erro, String mensagemDeErro) {
		ObjetoJson respostaJson = Json.criarObjeto();
		respostaJson.inserir(IDENTIFICADOR_MENSAGEM, Json.criarTexto(mensagemDeErro));
		respostaJson.inserir(IDENTIFICADOR_ERRO, erro);
		return CodigoDeEstado.HTTP_500.fornecerResposta(TipoDeMidia.JSON, respostaJson.comoTextoJson());
	}
}
