package br.dominioL.sisPro.testes.infraestrutura.http;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.sisPro.infraestrutura.http.ConstrutorDeUri;

public class TesteConstrutorDeUri {

	@Test
	public void criarPadrao() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/"));
	}

	@Test
	public void criarComProtocolo() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.protocolo(Texto.criar("https"));
		assertThat(construtor.construirAbsoluto().valor(), is("https://localhost:80/"));
	}

	@Test
	public void criarComEndereco() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.endereco(Texto.criar("dominiol.com.br"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://dominiol.com.br:80/"));
	}

	@Test
	public void criarComPorta() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.porta(Numero.criar(7000));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:7000/"));
	}

	@Test
	public void criarComCaminhoVazio() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar());
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/"));
	}

	@Test
	public void criarComCaminhoVazioComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/"));
	}

	@Test
	public void criarComCaminho() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("recurso"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/"));
	}

	@Test
	public void criarComBarraNoFim() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("recurso/"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/"));
	}

	@Test
	public void criarComCaminhoComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/"));
	}

	@Test
	public void criarComCaminhoDuplo() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("recurso/qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso/qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraDupla() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso//qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraTripla() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso///qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraQuadrupla() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso////qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoDuploComBarraQuadruplaRelativo() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso////qualquer"));
		assertThat(construtor.construirRelativo().valor(), is("/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoAdicionadoMultiplasVezes() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("recurso"));
		construtor.caminho(Texto.criar("qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarComCaminhoAdicionadoMultiplasVezesComBarra() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("recurso"));
		construtor.caminho(Texto.criar("/qualquer"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/qualquer/"));
	}

	@Test
	public void criarSubstituicaoDeParametro() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso/{identificador}/"));
		construtor.substituirParametro(Texto.criar("1"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/1/"));
	}

	@Test
	public void criarSubstituicaoDeDuasVezes() {
		ConstrutorDeUri construtor = ConstrutorDeUri.criar();
		construtor.caminho(Texto.criar("/recurso/{identificador}/qualquer/{outro}"));
		construtor.substituirParametro(Texto.criar("1"));
		construtor.substituirParametro(Texto.criar("2"));
		assertThat(construtor.construirAbsoluto().valor(), is("http://localhost:80/recurso/1/qualquer/2/"));
	}

}
