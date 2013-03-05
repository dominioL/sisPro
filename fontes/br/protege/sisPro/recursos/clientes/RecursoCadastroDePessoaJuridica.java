package br.protege.sisPro.recursos.clientes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.modelo.json.ManipuladorJson;
import br.protege.sisPro.recursos.ModeloDeRecursoDePaginaHtml;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/cadastroDePessoaJuridica")
public final class RecursoCadastroDePessoaJuridica {
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtmlPessoaJurídica() {
		JSONObject clientesJson = new JSONObject();
		new ManipuladorJson(clientesJson).adicionarCampo(UriBuilder.fromResource(RecursoClientes.class).build().toString(), "uriClientes");
		return new ModeloDeRecursoDePaginaHtml().obterHtml(ArquivoHtml.CADASTRO_DE_PESSOA_JURÍDICA, ArquivoHtml.MENU_CLIENTES, clientesJson);
	}
}
