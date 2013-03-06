package br.dominioL.sisPro.recursos.abstratos;

import br.dominioL.conexaoH.CodigoDeEstado;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class RecursoExcecao implements ExceptionMapper<WebApplicationException> {
	@Override
	public Response toResponse(WebApplicationException excecao) {
		return CodigoDeEstado.fornecerCodigoDeEstado(excecao.getResponse().getStatus()).fornecerResposta();
	}
}
