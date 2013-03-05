package br.protege.sisPro.recursos.enderecos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.modelo.feijao.Cliente;
import br.protege.sisPro.modelo.json.ManipuladorJson;
import br.protege.sisPro.recursos.ModeloDeRecursoDePaginaHtml;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/cadastroDeEndereco")
public final class RecursoCadastroDeEndereço {
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtml(@QueryParam("identificadorDoCliente") String identificadorDoCliente) {
		Cliente cliente = new Cliente();
		cliente.fixar_id(identificadorDoCliente);
		JSONObject informaçõsDoCliente = cliente.fornecerComoRecursoJson(new JSONObject());
		new ManipuladorJson(informaçõsDoCliente).adicionarCampo(UriBuilder.fromResource(RecursoEnderecos.class).build().toString(), "uriEndereços");
		return new ModeloDeRecursoDePaginaHtml().obterHtml(ArquivoHtml.CADASTRO_DE_ENDERECO, ArquivoHtml.MENU_ENDERECOS, informaçõsDoCliente);
	}
}
