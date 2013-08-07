package br.dominioL.sisPro.dados.couch;

import br.dominioL.conexaoH.Atributo;
import br.dominioL.conexaoH.ConstrutorDeUri;
import br.dominioL.conexaoH.Metodo;
import br.dominioL.conexaoH.TipoDeMidia;

import br.dominioL.estruturados.json.ObjetoJson;

import br.dominioL.sisPro.dados.BancoDeDados;
import br.dominioL.sisPro.dados.couch.RequisicaoCouch;
import br.dominioL.sisPro.dados.couch.RespostaCouch;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public final class Couch implements BancoDeDados<RespostaCouch, RequisicaoCouch> {
	private static Couch INSTANCIA;
	private static final Integer PORTA = 5984;
	private static final String ENDERECO = "localhost";
	private static final String NOME = "sispro";

	private Couch() {}

	public static final Couch fornecerInstancia() {
		return (INSTANCIA != null) ? (INSTANCIA) : (INSTANCIA = new Couch());
	}

	@Override
	public RespostaCouch adicionar(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicao(Metodo.POST, requisicao.fornecerDocumento(), requisicao.fornecerConstrutorDeUri());
	}

	@Override
	public RespostaCouch colocar(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicao(Metodo.PUT, requisicao.fornecerDocumento(), requisicao.fornecerConstrutorDeUri());
	}

	@Override
	public RespostaCouch remover(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicao(Metodo.DELETE, requisicao.fornecerConstrutorDeUri());
	}

	@Override
	public RespostaCouch buscar(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicao(Metodo.GET, requisicao.fornecerConstrutorDeUri());
	}

	@Override
	public void popular() {
		criarBanco();
		RepositorioDeClientes.fornecerInstancia().popular();
	}

	private void criarBanco() {
		RequisicaoCouch requisicao = RequisicaoCouch.criar();
		RespostaCouch resposta = colocar(requisicao);
	}

	private RespostaCouch fornecerRespostaDeRequisicao(Metodo metodo, ConstrutorDeUri construtorDeUri) {
		// WebResource recurso = fornecerRecurso(construtorDeUri);
		// return fornecerResposta(metodo, recurso);
		return null;
	}

	private RespostaCouch fornecerRespostaDeRequisicao(Metodo metodo, ObjetoJson entidade, ConstrutorDeUri construtorDeUri) {
		WebResource recurso = fornecerRecurso(construtorDeUri);
		recurso.entity(entidade.comoTextoJson(), TipoDeMidia.JSON.comoTextoSemCharset());
		return fornecerResposta(metodo, recurso.entity(entidade.comoTextoJson(), TipoDeMidia.JSON.comoTextoSemCharset()));
	}

	private WebResource fornecerRecurso(ConstrutorDeUri construtorDeUri) {
		String uri = construtorDeUri.construirAbsoluto();
		Client cliente = Client.create();
		return cliente.resource(uri);
	}

	private RespostaCouch fornecerResposta(Metodo metodo, WebResource.Builder recurso) {
		ClientResponse respostaHttp = recurso.method(metodo.comoTexto(), ClientResponse.class);
		RespostaCouch resposta = RespostaCouch.criar().comCodigoDeEstado(respostaHttp.getStatus());
		resposta.comEntidade(respostaHttp.getEntity(String.class));
		if (respostaHttp.getLocation() != null) {
			resposta.comLocalizacao(respostaHttp.getLocation().toString());
		}
		return resposta;
	}

	protected ConstrutorDeUri fornecerConstrutorDeUriBase() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar().endereco(ENDERECO).porta(PORTA).caminho(NOME);
		return construtor;
	}
}
