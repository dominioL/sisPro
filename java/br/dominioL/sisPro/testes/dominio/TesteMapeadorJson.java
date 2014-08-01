package br.dominioL.sisPro.testes.dominio;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeMapeamentoComCampoImpedido;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeMapeamentoComCampoInexistente;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeRenomeacaoDeCampoNaoMapeado;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado;
import br.dominioL.sisPro.dominio.mapeadores.excecoes.ExcecaoDeRenomeacaoDeCampoParaCampoJaRenomeadoComMesmoNome;
import br.dominioL.sisPro.dominio.mapeadores.objetoJson.MapeadorObjetoJson;

public class TesteMapeadorJson {
	private ObjetoJson vazio;
	private MapeadorObjetoJson mapeador;
	private ObjetoJson comId;
	private ObjetoJson comIdentificador;
	private ObjetoJson comIdentificadorComRevisao;
	private ObjetoJson comIdComIdentificador;

	@Before
	public void cirarFiguracao() {
		vazio = Json.criarObjeto();
		comId = ConstrutorJson.deObjeto().inserir("id", 1).construir();
		comIdentificador = ConstrutorJson.deObjeto().inserir("identificador", 1).construir();
		comIdentificadorComRevisao = ConstrutorJson.deObjeto().inserir("identificador", 1).inserir("revisao", 1).construir();
		comIdComIdentificador = ConstrutorJson.deObjeto().inserir("id", 1).inserir("identificador", 2).construir();
		mapeador = MapeadorObjetoJson.criar();
	}

	@Test
	public void criarMapeador() {
		assertThat(MapeadorObjetoJson.criar(), not(is(sameInstance(mapeador))));
	}

	@Test
	public void mapearObjetoVazio() {
		assertThat(mapeador.mapear(vazio), not(is(sameInstance(vazio))));
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test
	public void mapearCampoExistente() {
		mapeador.comCampo("identificador");
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificador)));
	}

	@Test
	public void mapearDoisCamposExistentes() {
		mapeador.comCampo("identificador").comCampo("revisao");
		assertThat(mapeador.mapear(comIdentificadorComRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void mapearCampoExistenteComComportamentoPadraoParaCamposNaoMapeados() {
		mapeador.comCampo("identificador");
		assertThat(mapeador.mapear(comIdentificadorComRevisao), is(equalTo(comIdentificador)));
	}

	@Test
	public void mapearCampoExistenteIgnorandoCamposNaoMapeados() {
		mapeador.comCampo("identificador").ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComRevisao), is(equalTo(comIdentificador)));
	}

	@Test
	public void mapearCampoExistenteIncluindoCamposNaoMapeados() {
		mapeador.comCampo("identificador").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test(expected = ExcecaoDeMapeamentoComCampoImpedido.class)
	public void mapearCampoExistenteImpedindoCamposNaoMapeados() {
		mapeador.comCampo("identificador").impedirCamposNaoMapeados().mapear(comIdentificadorComRevisao);
	}

	@Test(expected = ExcecaoDeMapeamentoComCampoInexistente.class)
	public void mapearCampoInexistente() {
		mapeador.comCampo("identificador").mapear(vazio);
	}

	@Test
	public void mapearCampoOpcionalExistente() {
		mapeador.comCampoOpcional("identificador");
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificador)));
	}

	@Test
	public void mapearCampoOpcionalInexistente() {
		mapeador.comCampoOpcional("identificador");
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test
	public void renomearCampoMapeadoExistente() {
		mapeador.comCampo("id").renomearCampo("id", "identificador");
		assertThat(mapeador.mapear(comId), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoMapeadoExistenteIncluindoNaoMapeados() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comId), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoMapeadoExistenteImpedindoNaoMapeados() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comId), is(equalTo(comIdentificador)));
	}

	@Test(expected = ExcecaoDeMapeamentoComCampoInexistente.class)
	public void renomearCampoMapeadoInexistente() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").mapear(vazio);
	}

	@Test
	public void renomearCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("id").renomearCampo("id", "identificador");
		assertThat(mapeador.mapear(comId), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoOpcionalMapeadoInexistente() {
		mapeador.comCampoOpcional("id").renomearCampo("id", "identificador").mapear(vazio);
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoNaoMapeado.class)
	public void renomearCampoNaoMapeadoExistente() {
		mapeador.renomearCampo("id", "identificador").mapear(comId);
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoNaoMapeado.class)
	public void renomearCampoNaoMapeadoInexistente() {
		mapeador.renomearCampo("id", "identificador").mapear(vazio);
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampo("id").comCampo("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoOpcionalMapeadoInexistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comId);
	}

	@Test
	public void renomearCampoMapeadoExistenteParaCampoNaoMapeadoExistente() {
		mapeador.comCampo("id").renomearCampo("id", "identificador");
		assertThat(mapeador.mapear(comIdComIdentificador), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoMapeadoExistenteParaCampoNaoMapeadoExistenteIncluindoNaoMapeados() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdComIdentificador), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoMapeadoExistenteParaCampoNaoMapeadoExistenteImpedindoNaoMapeados() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdComIdentificador), is(equalTo(comIdentificador)));
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoParaCampoJaRenomeadoComMesmoNome.class)
	public void renomearCampoParaCampoJaRenomeadoComMesmoNome() {
		mapeador.comCampo("id").comCampo("identificador").renomearCampo("id", "chave").renomearCampo("identificador", "chave").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeRenomeacaoDeCampoNaoMapeado.class)
	public void renomearCampoRenomeadoNaoMapeado() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").renomearCampo("identificador", "chave").mapear(comId);
	}
}
