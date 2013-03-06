package br.dominioL.sisPro.recursos.abstratos;

import br.dominioL.conexaoH.CodigoDeEstado;
import br.dominioL.conexaoH.TipoDeMidia;

import java.io.File;

import javax.ws.rs.core.Response;

public final class RecursoArquivo {
	public Response obterArquivo(File arquivo, CodigoDeEstado codigoDeEstado, TipoDeMidia tipoDeMidia) {
		if (!arquivo.exists()) {
			return CodigoDeEstado.HTTP_404.fornecerResposta();
		}
		return codigoDeEstado.fornecerResposta(tipoDeMidia, arquivo);
	}
}
