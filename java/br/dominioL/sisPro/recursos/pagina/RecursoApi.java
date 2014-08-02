package br.dominioL.sisPro.recursos.pagina;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.dominioL.sisPro.http.CodigoDeEstado;
import br.dominioL.sisPro.http.TipoDeMidia;
import br.dominioL.sisPro.http.TiposDeMidia;
import br.dominioL.sisPro.recursos.Recurso;
import br.dominioL.sisPro.recursos.arquivos.Arquivo;

@Path("/api")
public final class RecursoApi extends Recurso {
	@GET
	@Produces(TiposDeMidia.HTML)
	public Response obterHtml() {
		return CodigoDeEstado.HTTP_200.fornecerResposta(TipoDeMidia.HTML, Arquivo.PRINCIPAL.fornecerArquivo());
	}
}
