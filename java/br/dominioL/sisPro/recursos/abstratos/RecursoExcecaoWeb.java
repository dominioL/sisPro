package br.dominioL.sisPro.recursos.abstratos;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.dominioL.sisPro.dominio.http.CodigoDeEstado;

@Provider
public final class RecursoExcecaoWeb implements ExceptionMapper<WebApplicationException> {
	@Override
	public Response toResponse(WebApplicationException excecao) {
		return CodigoDeEstado.fornecerCodigoDeEstado(excecao.getResponse().getStatus()).fornecerResposta();
	}
}
