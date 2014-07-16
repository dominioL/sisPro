package br.dominioL.sisPro.testes.dominio;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.dominioL.sisPro.dominio.http.ConstrutorDeUri;

public class TesteConstrutorDeUri {
	@Test
	public void criarPadrao() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/"));
	}

	@Test
	public void criarComProtocolo() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.protocolo("https");
		assertThat(construtor.construirAbsoluto(), is("https://localhost:80/"));
	}

	@Test
	public void criarComEndereco() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.endereco("dominiol.com.br");
		assertThat(construtor.construirAbsoluto(), is("http://dominiol.com.br:80/"));
	}

	@Test
	public void criarComPorta() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.porta(7000);
		assertThat(construtor.construirAbsoluto(), is("http://localhost:7000/"));
	}

	@Test
	public void criarComCaminhoVazio() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/"));
	}

	@Test
	public void criarComCaminhoVazioComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/"));
	}

	@Test
	public void criarComCaminho() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("recurso");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/"));
	}

	@Test
	public void criarComBarraNoFim() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("recurso/");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/"));
	}

	@Test
	public void criarComCaminhoComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/"));
	}

	@Test
	public void criarComCaminhoDuplo() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("recurso/qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso/qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraDupla() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso//qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraTripla() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso///qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraQuadrupla() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso////qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}
	
	@Test
	public void criarComCaminhoDuploComBarraQuadruplaRelativo() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso////qualquer");
		assertThat(construtor.construirRelativo(), is("/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoAdicionadoMultiplasVezes() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("recurso");
		construtor.caminho("qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoAdicionadoMultiplasVezesComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("recurso");
		construtor.caminho("/qualquer");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarSubstituicaoDeParametro() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso/{identificador}/");
		construtor.substituirParametro("1");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/1/"));
	}

	@Test
	public void criarSubstituicaoDeDuasVezes() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho("/recurso/{identificador}/qualquer/{outro}");
		construtor.substituirParametro("1");
		construtor.substituirParametro("2");
		assertThat(construtor.construirAbsoluto(), is("http://localhost:80/recurso/1/qualquer/2/"));
	}
}
