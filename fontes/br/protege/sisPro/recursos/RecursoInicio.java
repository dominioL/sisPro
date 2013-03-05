package br.protege.sisPro.recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.protege.sisPro.estruturaInterna.ArquivoHtml;
import br.protege.sisPro.utilidades.TipoDeMidia;

@Path("/inicio")
public final class RecursoInicio {
	
	@GET
	@Produces(TipoDeMidia.HTML)
	public Response obterHtml() {
		return new ModeloDeRecursoDePaginaHtml().obterHtml(ArquivoHtml.INICIO, ArquivoHtml.MENU_PADRÃO);
	}
}
