package br.dominioL.sisPro.dados.couch;

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

	private Couch() {
		//TODO
	}

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
		System.out.println(resposta.fornecerCodigoDeEstado().comoTextoFormatado());
		System.out.println(resposta.fornecerEntidade().fornecerComoTextoJson());
	}

	private RespostaCouch fornecerRespostaDeRequisicao(Metodo metodo, ConstrutorDeUri construtorDeUri) {
		String uri = construtorDeUri.construirAbsoluto();
		Client cliente = Client.create();
		WebResource recurso = cliente.resource(uri);
		ClientResponse respostaHttp = recurso.method(metodo.comoTexto(), ClientResponse.class);
		RespostaCouch resposta = RespostaCouch.criar().comCodigoDeEstado(respostaHttp.getStatus());
		resposta.comEntidade(respostaHttp.getEntity(String.class));
		return resposta;
	}

	private RespostaCouch fornecerRespostaDeRequisicao(Metodo metodo, ObjetoJson entidade, ConstrutorDeUri construtorDeUri) {
		String uri = construtorDeUri.construirAbsoluto();
		Client cliente = Client.create();
		WebResource recurso = cliente.resource(uri);
		recurso.entity(entidade.fornecerComoTextoJson());
		recurso.type(TipoDeMidia.JSON.comoTexto());
		ClientResponse respostaHttp = recurso.method(metodo.comoTexto(), ClientResponse.class);
		RespostaCouch resposta = RespostaCouch.criar().comCodigoDeEstado(respostaHttp.getStatus());
		resposta.comEntidade(respostaHttp.getEntity(String.class));
		return resposta;
	}

	protected ConstrutorDeUri fornecerConstrutorDeUriBase() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criarCom().endereco(ENDERECO).porta(PORTA).caminho(NOME);
		return construtor;
	}
}
