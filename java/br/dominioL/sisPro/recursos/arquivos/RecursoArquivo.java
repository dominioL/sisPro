package br.dominioL.sisPro.recursos.arquivos;

import java.io.File;

import javax.ws.rs.core.Response;

import br.dominioL.sisPro.http.CodigoDeEstado;
import br.dominioL.sisPro.http.TipoDeMidia;

public final class RecursoArquivo {
	public Response obterArquivo(File arquivo, CodigoDeEstado codigoDeEstado, TipoDeMidia tipoDeMidia) {
		if (!arquivo.exists()) {
			return CodigoDeEstado.HTTP_404.fornecerResposta();
		}
		return codigoDeEstado.fornecerResposta(tipoDeMidia, arquivo);
	}
}
