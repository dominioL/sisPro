package br.dominioL.sisPro;

import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import br.dominioL.sisPro.ambiente.AmbienteSisPro;
import br.dominioL.sisPro.ambiente.AplicacaoSisPro;

public final class ServidorSisPro {

	public static void main(String[] argumentos) {
		AmbienteSisPro ambiente = new AmbienteSisPro();
		ResourceConfig configuracao = new ResourceConfig(AplicacaoSisPro.class);
		JettyHttpContainerFactory.createServer(ambiente.construtorDeUri().comoUri(), configuracao);
	}

}