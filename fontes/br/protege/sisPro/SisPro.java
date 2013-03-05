package br.protege.sisPro;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public final class SisPro {
	
	private static final int PORTA = 7002;
	private static final String CONTEXTO = "/";
	private static final String URLS_MAPEADAS = "/*";
	
	public static void main(String[] args) throws Exception {
		ServletHolder contâinerDeServlet = new ServletHolder(ServletContainer.class);
		contâinerDeServlet.setInitParameter("com.sun.jersey.config.property.packages", "br.protege.sisPro.recursos");
		contâinerDeServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
		Server servidor = new Server(PORTA);
		ServletContextHandler contextoDeServlet = new ServletContextHandler(servidor, CONTEXTO, true, false);
		contextoDeServlet.addServlet(contâinerDeServlet, URLS_MAPEADAS);
		servidor.start();
	}
}
