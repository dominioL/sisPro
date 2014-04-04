package br.dominioL.sisPro.dados.couch;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import br.dominioL.conexaoH.ConstrutorDeUri;
import br.dominioL.conexaoH.Metodo;
import br.dominioL.conexaoH.TipoDeMidia;
import br.dominioL.sisPro.dados.BancoDeDados;

public final class Couch implements BancoDeDados<RespostaCouch, RequisicaoCouch> {
	private static Couch INSTANCIA;
	private static final String PROTOCOLO = "http";
	private static final String ENDERECO = "localhost";
	private static final Integer PORTA = 5984;
	private static final String CAMINHO = "sispro";

	private Couch() {}

	public static final Couch fornecerInstancia() {
		return (INSTANCIA != null) ? (INSTANCIA) : (INSTANCIA = new Couch());
	}
	
	public ConstrutorDeUri fornecerConstrutorDeUri() {
		return uri();
	}
	
	public static ConstrutorDeUri uri() {
		return ConstrutorDeUri.criar()
			.protocolo(PROTOCOLO)
			.endereco(ENDERECO)
			.porta(PORTA)
			.caminho(CAMINHO);
	}

	@Override
	public RespostaCouch inserir(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicaoComEntidade(Metodo.POST, requisicao);
	}

	@Override
	public RespostaCouch colocar(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicaoComEntidade(Metodo.PUT, requisicao);
	}

	@Override
	public RespostaCouch remover(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicao(Metodo.DELETE, requisicao);
	}

	@Override
	public RespostaCouch obter(RequisicaoCouch requisicao) {
		return fornecerRespostaDeRequisicao(Metodo.GET, requisicao);
	}

	@Override
	public void popular() {
		criarBanco();
		RepositorioDeClientes.fornecerInstancia().popular();
	}

	private void criarBanco() {
		RequisicaoCouch requisicao = RequisicaoCouch.criar();
		colocar(requisicao);
	}

	private RespostaCouch fornecerRespostaDeRequisicao(Metodo metodo, RequisicaoCouch requisicao) {
		String uriTextual = requisicao.fornecerConstrutorDeUri().construirAbsoluto();
		String tipoDeMidiaDaRespostaTextual = TipoDeMidia.JSON.comoTextoSemCharset();
		String metodoTextual = metodo.comoTexto();
		Response resposta = ClientBuilder.newClient()
			.target(uriTextual)
			.request(tipoDeMidiaDaRespostaTextual)
			.method(metodoTextual);
		return fornecerRespostaCouch(resposta);
	}

	private RespostaCouch fornecerRespostaDeRequisicaoComEntidade(Metodo metodo, RequisicaoCouch requisicao) {
		String uriTextual = requisicao.fornecerConstrutorDeUri().construirAbsoluto();
		String entidadeTextual = requisicao.fornecerDocumento().comoTextoJson();
		String tipoDeMidiaDaRequisicaoTextual = TipoDeMidia.JSON.comoTextoSemCharset();
		String tipoDeMidiaDaRespostaTextual = TipoDeMidia.JSON.comoTextoSemCharset();
		String metodoTextual = metodo.comoTexto();
		Response resposta = ClientBuilder.newClient()
			.target(uriTextual)
			.request(tipoDeMidiaDaRespostaTextual)
			.method(metodoTextual, Entity.entity(entidadeTextual, tipoDeMidiaDaRequisicaoTextual));
		return fornecerRespostaCouch(resposta);
	}

	private RespostaCouch fornecerRespostaCouch(Response respostaHttp) {
		RespostaCouch resposta = RespostaCouch.criar()
			.comCodigoDeEstado(respostaHttp.getStatus())
			.comEntidade(respostaHttp.readEntity(String.class));
		if (respostaHttp.getLocation() != null) {
			resposta.comLocalizacao(respostaHttp.getLocation().toString());
		}
		return resposta;
	}
}
