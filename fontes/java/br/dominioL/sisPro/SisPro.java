package br.dominioL.sisPro;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import br.dominioL.conexaoH.ConstrutorDeUri;
import br.dominioL.sisPro.dados.couch.Couch;
import br.dominioL.sisPro.recursos.abstratos.RecursoExcecaoJava;
import br.dominioL.sisPro.recursos.abstratos.RecursoExcecaoWeb;

public final class SisPro extends ResourceConfig {
	private static SisPro INSTANCIA;
	private static final String PROTOCOLO = "http";
	private static final String ENDERECO = "localhost";
	private static final Integer PORTA = 7000;
	private static final String CAMINHO = "/";
	private static final String PACOTE_DOS_RECURSOS = "br.dominioL.sisPro.recursos";

	private SisPro() {
		packages(PACOTE_DOS_RECURSOS);
		registerClasses(RecursoExcecaoWeb.class, RecursoExcecaoJava.class);
	}

	public static final SisPro fornecerInstancia() {
		return (INSTANCIA != null) ? (INSTANCIA) : (INSTANCIA = new SisPro());
	}

	public ConstrutorDeUri fornecerConstrutorDeUri() {
		return uri();
	}

	public static ConstrutorDeUri uri() {
		return ConstrutorDeUri.criar()
			.protocolo(PROTOCOLO)
			.endereco(ENDERECO)
			.porta(PORTA)
			.caminho(CAMINHO);
	}

	public static void main(String[] argumentos) throws IOException {
		SisPro sisPro = SisPro.fornecerInstancia();
		Couch.fornecerInstancia().popular();
		URI uriBase = UriBuilder.fromUri(sisPro.fornecerConstrutorDeUri().construirAbsoluto()).build();
		HttpServer servidorHttp = GrizzlyHttpServerFactory.createHttpServer(uriBase, sisPro);
		try {
			servidorHttp.start();
			System.out.println(String.format("Servidor iniciado em %s. Aperte enter para encerrar.", uriBase.toString()));
			System.in.read();
		} catch (Exception excecao) {
			System.err.println(excecao);
		}
	}
}
