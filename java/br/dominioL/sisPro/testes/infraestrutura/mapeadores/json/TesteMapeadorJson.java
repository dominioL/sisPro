package br.dominioL.sisPro.testes.infraestrutura.mapeadores.json;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.excecoes.ExcecaoJsonDeTipo;
import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.MapeadorJson;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.MapeadorJsonDeObjeto;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoNaoMapeado;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoJaManipuladoComMesmoNome;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeManipulacaoDeCampoParaCampoMapeado;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoImpedido;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoInexistente;

public class TesteMapeadorJson {

	private ObjetoJson vazio;
	private MapeadorJsonDeObjeto mapeador;
	private ObjetoJson comId;
	private ObjetoJson comIdentificador;
	private ObjetoJson comIdentificadorComRevisao;
	private ObjetoJson comIdComIdentificador;
	private ObjetoJson comIdentificadorComSegundaRevisao;
	private ObjetoJson comCliente;
	private ObjetoJson comClienteNumero;
	private ObjetoJson comRevisao;
	private ObjetoJson comSegundaRevisao;
	private ObjetoJson comRevComSegundaRevisao;
	private ObjetoJson comSegundaRevComRevisao;
	private ObjetoJson comSegundaRevComSegundaRevisao;
	private ObjetoJson comRev;
	private ObjetoJson comUri;
	private ObjetoJson comIdentificadorComUri;
	private ObjetoJson comIdentificadorComClienteCotendoNomeCidadeContendoNomeEstado;
	private ObjetoJson comIdentificadorComClienteCotendoNomeRevisaoCidadeContendoNomeEstado;

	@Before
	public void cirarFiguracao() {
		vazio = ConstrutorJson.deObjeto().construir();
		comId = ConstrutorJson.deObjeto().inserir("id", Numero.um()).construir();
		comIdentificador = ConstrutorJson.deObjeto().inserir("identificador", Numero.um()).construir();
		comIdentificadorComRevisao = ConstrutorJson.deObjeto().inserir("identificador", Numero.um()).inserir("revisao", Numero.um()).construir();
		comIdentificadorComSegundaRevisao = ConstrutorJson.deObjeto().inserir("identificador", Numero.um()).inserir("revisao", Numero.criar(2)).construir();
		comIdComIdentificador = ConstrutorJson.deObjeto().inserir("id", Numero.um()).inserir("identificador", Numero.criar(2)).construir();
		comCliente = ConstrutorJson.deObjeto().inserir("cliente", ConstrutorJson.deObjeto().inserir("nome", Texto.criar("Lucas")).construir()).construir();
		comClienteNumero = ConstrutorJson.deObjeto().inserir("cliente", Numero.um()).construir();
		mapeador = MapeadorJson.deObjeto();
		comRevisao = ConstrutorJson.deObjeto().inserir("revisao", Numero.um()).construir();
		comSegundaRevisao = ConstrutorJson.deObjeto().inserir("revisao", Numero.criar(2)).construir();
		comRevComSegundaRevisao = ConstrutorJson.deObjeto().inserir("rev", Numero.um()).inserir("revisao", Numero.criar(2)).construir();
		comSegundaRevComRevisao = ConstrutorJson.deObjeto().inserir("rev", Numero.criar(2)).inserir("revisao", Numero.um()).construir();
		comSegundaRevComSegundaRevisao = ConstrutorJson.deObjeto().inserir("rev", Numero.criar(2)).inserir("revisao", Numero.criar(2)).construir();
		comRev = ConstrutorJson.deObjeto().inserir("rev", Numero.um()).construir();
		comUri = ConstrutorJson.deObjeto().inserir("uri", Texto.criar("/cliente/1")).construir();
		comIdentificadorComUri = ConstrutorJson.deObjeto().inserir("identificador", Numero.um()).inserir("uri", Texto.criar("/cliente/1")).construir();
		comIdentificadorComClienteCotendoNomeCidadeContendoNomeEstado = ConstrutorJson.deObjeto()
				.inserir("identificador", Numero.um())
				.inserir("cliente", ConstrutorJson.deObjeto()
						.inserir("nome", Texto.criar("Lucas"))
						.inserir("cidade", ConstrutorJson.deObjeto()
								.inserir("nome", Texto.criar("Florianópolis"))
								.inserir("estado", Texto.criar("SC"))
								.construir())
						.construir())
				.construir();
		comIdentificadorComClienteCotendoNomeRevisaoCidadeContendoNomeEstado = ConstrutorJson.deObjeto()
				.inserir("identificador", Numero.um())
				.inserir("cliente", ConstrutorJson.deObjeto()
						.inserir("nome", Texto.criar("Lucas"))
						.inserir("revisao", Numero.um())
						.inserir("cidade", ConstrutorJson.deObjeto()
								.inserir("nome", Texto.criar("Florianópolis"))
								.inserir("estado", Texto.criar("SC"))
								.construir())
						.construir())
				.construir();
		mapeador = MapeadorJson.deObjeto();
	}

	@Test
	public void criarMapeador() {
		assertThat(MapeadorJson.deObjeto(), not(is(sameInstance(mapeador))));
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

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampo("id").comCampo("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void renomearCampoOpcionalMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampo("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void renomearCampoMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void renomearCampoOpcionalMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampoOpcional("identificador").renomearCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
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

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void clonarCampoMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampo("id").comCampo("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void clonarCampoOpcionalMapeadoExistenteParaCampoMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampo("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void clonarCampoMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampo("id").comCampoOpcional("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void clonarCampoOpcionalMapeadoExistenteParaCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("id").comCampoOpcional("identificador").clonarCampo("id", "identificador").mapear(comIdComIdentificador);
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
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

	@Test
	public void mapearObjetoDentroDeObjeto() {
		mapeador.comCampo("cliente", MapeadorJson.deObjeto().comCampo("nome"));
		assertThat(mapeador.mapear(comCliente), is(equalTo(comCliente)));
	}

	@Test
	public void mapearObjetoOpcionalExistenteDentroDeObjeto() {
		mapeador.comCampoOpcional("cliente", MapeadorJson.deObjeto().comCampo("nome"));
		assertThat(mapeador.mapear(comCliente), is(equalTo(comCliente)));
	}

	@Test
	public void mapearObjetoOpcionalInexistenteDentroDeObjeto() {
		mapeador.comCampoOpcional("cliente", MapeadorJson.deObjeto().comCampo("nome"));
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test(expected = ExcecaoJsonDeTipo.class)
	public void mapearObjetoComTipoInvalido() {
		mapeador.comCampo("cliente", MapeadorJson.deObjeto()).mapear(comClienteNumero);
	}

	@Test
	public void adicionarCampoNaoMapeadoInexistenteIgnorandoCamposNaoMepeados() {
		mapeador.comCampo("identificador").adicionarCampo("revisao", Numero.um()).ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void adicionarCampoNaoMapeadoInexistenteImpedindoCamposNaoMepeados() {
		mapeador.comCampo("identificador").adicionarCampo("revisao", Numero.um()).impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void adicionarCampoNaoMapeadoInexistenteIncluindoCamposNaoMepeados() {
		mapeador.comCampo("identificador").adicionarCampo("revisao", Numero.um()).incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComRevisao)));
	}

	@Test
	public void adicionarCampoNaoMapeadoExistenteIgnorandoCamposNaoMapeados() {
		mapeador.adicionarCampo("revisao", Json.criarNumero(Numero.criar(2))).ignorarCamposNaoMapeados();
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comSegundaRevisao)));
	}

	@Test
	public void adicionarCampoNaoMapeadoExistenteImpedindoCamposNaoMapeados() {
		mapeador.adicionarCampo("revisao", Numero.um()).impedirCamposNaoMapeados();
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comSegundaRevisao)));
	}

	@Test
	public void adicionarCampoNaoMapeadoExistenteIncluindoCamposNaoMapeados() {
		mapeador.adicionarCampo("revisao", Numero.um()).incluirCamposNaoMapeados();
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comSegundaRevisao)));
	}

	@Test
	public void adicionarCampoOpcionalMapeadoExistente() {
		mapeador.comCampoOpcional("revisao").adicionarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comRevisao)));
	}

	@Test
	public void adicionarCampoOpcionalMapeadoInexistente() {
		mapeador.comCampoOpcional("revisao").adicionarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(vazio), is(equalTo(comRevisao)));
	}

	@Test(expected = ExcecaoDeManipulacaoDeCampoParaCampoMapeado.class)
	public void adicionarCampoMapeadoExistente() {
		mapeador.comCampo("revisao").adicionarCampo("revisao", Numero.um()).mapear(comRevisao);
	}

	@Test
	public void adicionarCampoOpcionalMapeadoExistenteRenomeado() {
		mapeador.comCampoOpcional("revisao").renomearCampo("revisao", "rev").adicionarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(comRevComSegundaRevisao), is(equalTo(comSegundaRevComRevisao)));
	}

	@Test
	public void adicionarCampoOpcionalMapeadoInexistenteRenomeado() {
		mapeador.comCampoOpcional("revisao").renomearCampo("revisao", "rev").adicionarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(vazio), is(equalTo(comRevisao)));
	}

	@Test
	public void adicionarCampoComMesmoNomeDeCampoOpcionalMapeadoExistenteRenomeado() {
		mapeador.comCampoOpcional("revisao").renomearCampo("revisao", "rev").adicionarCampo("rev", Numero.um());
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comRev)));
	}

	@Test
	public void adicionarCampoComMesmoNomeDeCampoOpcionalMapeadoInexistenteRenomeado() {
		mapeador.comCampoOpcional("revisao").renomearCampo("revisao", "rev").adicionarCampo("rev", Numero.um());
		assertThat(mapeador.mapear(vazio), is(equalTo(comRev)));
	}

	@Test
	public void adicionarCampoOpcionalMapeadoExistenteClonado() {
		mapeador.comCampoOpcional("revisao").clonarCampo("revisao", "rev").adicionarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(comRevComSegundaRevisao), is(equalTo(comSegundaRevComSegundaRevisao)));
	}

	@Test
	public void adicionarCampoOpcionalMapeadoInexistenteClonado() {
		mapeador.comCampoOpcional("revisao").clonarCampo("revisao", "rev").adicionarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(vazio), is(equalTo(comRevisao)));
	}

	@Test
	public void adicionarCampoComMesmoNomeDeCampoOpcionalMapeadoExistenteClonado() {
		mapeador.comCampoOpcional("revisao").clonarCampo("revisao", "rev").adicionarCampo("rev", Numero.um());
		assertThat(mapeador.mapear(comSegundaRevisao), is(equalTo(comSegundaRevComSegundaRevisao)));
	}

	@Test
	public void adicionarCampoComMesmoNomeDeCampoOpcionalMapeadoInexistenteClonado() {
		mapeador.comCampoOpcional("revisao").clonarCampo("revisao", "rev").adicionarCampo("rev", Numero.um());
		assertThat(mapeador.mapear(vazio), is(equalTo(comRev)));
	}

	@Test
	public void transformarCampoMapeadoExistente() {
		mapeador.comCampo("revisao").transformarCampo("revisao", new TransformadorJson() {

			@Override
			public ValorJson transformar(ValorJson valor) {
				return Numero.um();
			}
		});
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comSegundaRevisao)));
	}

	@Test
	public void transformarCampoMapeadoExistentePorValor() {
		mapeador.comCampo("revisao").transformarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(comRevisao), is(equalTo(comSegundaRevisao)));
	}

	@Test
	public void transformarCampoOpcionalMapeadoInexistente() {
		mapeador.comCampoOpcional("revisao").transformarCampo("revisao", new TransformadorJson() {

			@Override
			public ValorJson transformar(ValorJson valor) {
				return Numero.um();
			}
		});
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test
	public void transformarCampoOpcionalMapeadoInexistentePorValor() {
		mapeador.comCampoOpcional("revisao").transformarCampo("revisao", Numero.um());
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test(expected = ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado.class)
	public void transformarCampoNaoMapeadoExistenteIgnorandoCamposNaoMapeados() {
		mapeador.transformarCampo("revisao", new TransformadorJson() {

			@Override
			public ValorJson transformar(ValorJson valor) {
				return Numero.um();
			}
		}).ignorarCamposNaoMapeados().mapear(vazio);
	}

	@Test(expected = ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado.class)
	public void transformarCampoNaoMapeadoExistenteIgnorandoCamposNaoMapeadosPorValor() {
		mapeador.transformarCampo("revisao", Numero.um()).ignorarCamposNaoMapeados().mapear(comRevisao);
	}

	@Test(expected = ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado.class)
	public void transformarCampoNaoMapeadoExistenteIncluindoCamposNaoMapeados() {
		mapeador.transformarCampo("revisao", new TransformadorJson() {

			@Override
			public ValorJson transformar(ValorJson valor) {
				return Numero.um();
			}
		}).incluirCamposNaoMapeados().mapear(comRevisao);
	}

	@Test(expected = ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado.class)
	public void transformarCampoNaoMapeadoExistenteIncluindoCamposNaoMapeadosPorValor() {
		mapeador.transformarCampo("revisao", Numero.um()).incluirCamposNaoMapeados().mapear(comRevisao);
	}

	@Test(expected = ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado.class)
	public void transformarCampoNaoMapeadoExistenteImpedindoCamposNaoMapeados() {
		mapeador.transformarCampo("revisao", new TransformadorJson() {

			@Override
			public ValorJson transformar(ValorJson valor) {
				return Numero.um();
			}
		}).impedirCamposNaoMapeados().mapear(vazio);
	}

	@Test(expected = ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado.class)
	public void transformarCampoNaoMapeadoExistenteImpedindoCamposNaoMapeadosPorValor() {
		mapeador.transformarCampo("revisao", Numero.um()).impedirCamposNaoMapeados().mapear(comRevisao);
	}

	@Test
	public void transformarCampoRenomeadoExistente() {
		mapeador.comCampo("identificador").renomearCampo("identificador", "uri").transformarCampo("uri", Json.criarTexto(Texto.criar("/cliente/1")));
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comUri)));
	}

	@Test
	public void transformarCampoRenomeadoInexistente() {
		mapeador.comCampoOpcional("identificador").renomearCampo("identificador", "uri").transformarCampo("uri", Json.criarTexto(Texto.criar("/cliente/1")));
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test
	public void transformarCampoClonadoExistente() {
		mapeador.comCampo("identificador").clonarCampo("identificador", "uri").transformarCampo("uri", Json.criarTexto(Texto.criar("/cliente/1")));
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComUri)));
	}

	@Test
	public void transformarCampoClonadoInexistente() {
		mapeador.comCampoOpcional("identificador").clonarCampo("identificador", "uri").transformarCampo("uri", Json.criarTexto(Texto.criar("/cliente/1")));
		assertThat(mapeador.mapear(vazio), is(equalTo(vazio)));
	}

	@Test
	public void transformarCampoAdicionado() {
		mapeador.comCampo("identificador").adicionarCampo("uri", Json.criarTexto(Texto.criar("/cliente/2"))).transformarCampo("uri", Json.criarTexto(Texto.criar("/cliente/1")));
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificadorComUri)));
	}

	@Test
	public void mapearObjetoDentroDeObjetoDentroDeObjetoComCampoOpcionalExistente() {
		mapeador.comCampo("identificador")
				.comCampo("cliente", MapeadorJson.deObjeto()
						.comCampo("nome")
						.comCampo("cidade", MapeadorJson.deObjeto()
								.comCampo("nome")
								.comCampoOpcional("estado"))
						.adicionarCampo("revisao", Numero.um()));
		assertThat(mapeador.mapear(comIdentificadorComClienteCotendoNomeCidadeContendoNomeEstado), is(equalTo(comIdentificadorComClienteCotendoNomeRevisaoCidadeContendoNomeEstado)));
	}

	@Test
	public void mapearObjetoOpcionalExistenteDentroDeObjetoDentroDeObjetoComCampoOpcionalExistente() {
		mapeador.comCampo("identificador")
				.comCampoOpcional("cliente", MapeadorJson.deObjeto()
						.comCampo("nome")
						.comCampo("cidade", MapeadorJson.deObjeto()
								.comCampo("nome")
								.comCampoOpcional("estado"))
						.adicionarCampo("revisao", Numero.um()));
		assertThat(mapeador.mapear(comIdentificadorComClienteCotendoNomeCidadeContendoNomeEstado), is(equalTo(comIdentificadorComClienteCotendoNomeRevisaoCidadeContendoNomeEstado)));
	}

	@Test
	public void mapearObjetoOpcionalInexistenteDentroDeObjetoDentroDeObjetoComCampoOpcionalExistente() {
		mapeador.comCampo("identificador")
				.comCampoOpcional("cliente", MapeadorJson.deObjeto()
						.comCampo("nome")
						.comCampo("cidade", MapeadorJson.deObjeto()
								.comCampo("nome")
								.comCampoOpcional("estado"))
						.adicionarCampo("revisao", Numero.um()));
		assertThat(mapeador.mapear(comIdentificador), is(equalTo(comIdentificador)));
	}

}
