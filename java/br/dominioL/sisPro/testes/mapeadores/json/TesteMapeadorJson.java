package br.dominioL.sisPro.testes.mapeadores.json;

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
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoNaoMapeado;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoImpedido;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoInexistente;
import br.dominioL.sisPro.mapeadores.json.objeto.MapeadorObjetoJson;

public class TesteMapeadorJson {
	private ObjetoJson vazio;
	private MapeadorObjetoJson mapeador;
	private ObjetoJson comId;
	private ObjetoJson comIdentificador;
	private ObjetoJson comIdentificadorComRevisao;
	private ObjetoJson comIdComIdentificador;
	private ObjetoJson comIdentificadorComSegundaRevisao;

	@Before
	public void cirarFiguracao() {
		vazio = Json.criarObjeto();
		comId = ConstrutorJson.deObjeto().inserir("id", 1).construir();
		comIdentificador = ConstrutorJson.deObjeto().inserir("identificador", 1).construir();
		comIdentificadorComRevisao = ConstrutorJson.deObjeto().inserir("identificador", 1).inserir("revisao", 1).construir();
		comIdentificadorComSegundaRevisao = ConstrutorJson.deObjeto().inserir("identificador", 1).inserir("revisao", 2).construir();
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
	public void renomearCampoMapeadoExistenteIgnorandoNaoMapeados() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").ignorarCamposNaoMapeados();
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

	@Test
	public void renomearCampoMapeadoExistenteParaCampoNaoMapeadoExistenteIgnorandoNaoMapeados() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").ignorarCamposNaoMapeados();
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

	@Test
	public void renomearCampoOpcionalMapeadoExistenteParaCampoNaoMapeadoExistenteIgnorandoNaoMapeados() {
		mapeador.comCampoOpcional("id").renomearCampo("id", "identificador").ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdComIdentificador), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoOpcionalMapeadoExistenteParaCampoNaoMapeadoExistenteIncluindoNaoMapeados() {
		mapeador.comCampoOpcional("id").renomearCampo("id", "identificador").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdComIdentificador), is(equalTo(comIdentificador)));
	}

	@Test
	public void renomearCampoOpcionalMapeadoExistenteParaCampoNaoMapeadoExistenteImpedindoNaoMapeados() {
		mapeador.comCampoOpcional("id").renomearCampo("id", "identificador").impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdComIdentificador), is(equalTo(comIdentificador)));
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void renomearCampoNaoMapeadoExistente() {
		mapeador.renomearCampo("id", "identificador").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void renomearCampoNaoMapeadoInexistente() {
		mapeador.renomearCampo("id", "identificador").mapear(vazio);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void renomearCampoRenomeadoNaoMapeado() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").renomearCampo("identificador", "chave").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampo("id").comCampo("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoOpcionalMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampo("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoOpcionalMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoOpcionalMapeadoInexistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome.class)
	public void renomearCampoParaCampoJaRenomeadoComMesmoNome() {
		mapeador.comCampo("id").comCampo("identificador").renomearCampo("id", "chave").renomearCampo("identificador", "chave").mapear(comIdComIdentificador);
	}

	@Test
	public void clonarCampoMapeadoExistenteIgnorandoNaoMapeados() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "revisao").ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoMapeadoExistenteIncluindoNaoMapeados() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "revisao").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoMapeadoExistenteImpedindoNaoMapeados() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "revisao").impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("identificador").clonarCampo("identificador", "revisao");
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoOpcionalMapeadoInexistente() {
		mapeador.comCampoOpcional("identificador").clonarCampo("identificador", "revisao");
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test
	public void clonarCampoMapeadoExistenteParaCampoNaoMapeadoExistenteIgnorandoNaoMapeados() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "revisao").ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComSegundaRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoMapeadoExistenteParaCampoNaoMapeadoExistenteIncluindoNaoMapeados() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "revisao").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComSegundaRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoMapeadoExistenteParaCampoNaoMapeadoExistenteImpedindoNaoMapeados() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "revisao").impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComSegundaRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoOpcionalMapeadoExistenteParaCampoNaoMapeadoExistenteIgnorandoNaoMapeados() {
		mapeador.comCampoOpcional("identificador").clonarCampo("identificador", "revisao").ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComSegundaRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoOpcionalMapeadoExistenteParaCampoNaoMapeadoExistenteIncluindoNaoMapeados() {
		mapeador.comCampoOpcional("identificador").clonarCampo("identificador", "revisao").incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComSegundaRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void clonarCampoOpcionalMapeadoExistenteParaCampoNaoMapeadoExistenteImpedindoNaoMapeados() {
		mapeador.comCampoOpcional("identificador").clonarCampo("identificador", "revisao").impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificadorComSegundaRevisao), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void clonarCampoNaoMapeadoExistente() {
		mapeador.clonarCampo("id", "identificador").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void clonarCampoNaoMapeadoInexistente() {
		mapeador.clonarCampo("id", "identificador").mapear(vazio);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void clonarCampoRenomeadoNaoMapeado() {
		mapeador.comCampo("id").renomearCampo("id", "identificador").clonarCampo("identificador", "chave").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoNaoMapeado.class)
	public void clonarCampoClonadoNaoMapeado() {
		mapeador.comCampo("id").clonarCampo("id", "identificador").clonarCampo("identificador", "chave").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void clonarCampoMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampo("id").comCampo("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void clonarCampoOpcionalMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampo("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void clonarCampoMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void clonarCampoOpcionalMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampoOpcional("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaMapeado.class)
	public void clonarCampoMapeadoExistenteParaCampoOpcionalMapeadoInexistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").clonarCampo("id", "identificador").mapear(comId);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome.class)
	public void clonarCampoParaCampoJaRenomeadoComMesmoNome() {
		mapeador.comCampo("id").comCampo("identificador").renomearCampo("id", "chave").clonarCampo("identificador", "chave").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome.class)
	public void clonarCampoParaCampoJaClonadoComMesmoNome() {
		mapeador.comCampo("id").comCampo("identificador").clonarCampo("id", "chave").clonarCampo("identificador", "chave").mapear(comIdComIdentificador);
	}
}
