package br.protege.sisPro.bancoDeDados.couchdb;

import org.codehaus.jettison.json.JSONObject;

import br.protege.sisPro.utilidades.TipoDeMidia;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public final class RequisicaoHttpParaOCouchDb {
	
	private static final String NOME_DO_BANCO_DE_DADOS = "sispro";
	private static final int PORTA_DO_COUCH_DB = 5984;
	
	public ClientResponse enviarPost(String dados) {
		return fornecerRecursoPreparado().post(ClientResponse.class, dados);
	}
	
	public ClientResponse enviarGet() {
		return fornecerRecursoPreparado().get(ClientResponse.class);
	}
	
	public ClientResponse enviarGet(String uri) {
		return fornecerRecursoPreparado(uri).get(ClientResponse.class);
	}
	
	public ClientResponse enviarPut() {
		return fornecerRecursoPreparado().put(ClientResponse.class);
	}
	
	public ClientResponse enviarPut(String uri, JSONObject dados) {
		Builder recursoPreparado = fornecerRecursoPreparado(uri);
		recursoPreparado.header("Referer", "http://localhost:5984/sispro");
		
		return recursoPreparado.put(ClientResponse.class, dados);
	}
	
	private Builder fornecerRecursoPreparado() {
		return fornecerRecursoPreparado("/");
	}
	
	private Builder fornecerRecursoPreparado(String uri) {
		Client clienteHttp = Client.create();
		WebResource recurso = clienteHttp.resource("http://localhost:"+PORTA_DO_COUCH_DB+"/"+NOME_DO_BANCO_DE_DADOS+"/"+uri);
		
		return recurso.type(TipoDeMidia.JSON);
	}
}
