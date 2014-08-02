package br.dominioL.sisPro.mapeadores.json.objeto;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.sisPro.mapeadores.json.Mapeador;
import br.dominioL.sisPro.mapeadores.json.MapeadorAdicionarCampos;
import br.dominioL.sisPro.mapeadores.json.MapeadorCamposNaoMapeados;
import br.dominioL.sisPro.mapeadores.json.MapeadorClonarCampos;
import br.dominioL.sisPro.mapeadores.json.MapeadorRenomearCampos;
import br.dominioL.sisPro.mapeadores.json.MapeadorTransformarCampos;
import br.dominioL.sisPro.mapeadores.json.TransformadorDeMapeamento;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamento;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusao;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusaoOpcional;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeRenomeacao;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeados;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueIgnora;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueImpede;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueInclui;

public final class MapeadorObjetoJson implements Mapeador<ObjetoJson> {
	public static MapeadorObjetoJson criar() {
		return new MapeadorObjetoJson();
	}

	private ListaEncadeada<RegraDeMapeamento> regras;
	private Mapa<Texto, Booleano> campos;
	private TratadorDeNaoMapeados tratadorDeNaoMapeados;

	private MapeadorObjetoJson() {
		regras = ListaEncadeada.criar();
		campos = MapaLista.criar();
		ignorarCamposNaoMapeados();
	}

	@Override
	public Mapeador<ObjetoJson> comCampo(String nome) {
		campos.inserir(Texto.comValor(nome), Booleano.falso());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusao(nome));
		return this;
	}

	@Override
	public Mapeador<ObjetoJson> comCampoOpcional(String nome) {
		campos.inserir(Texto.comValor(nome), Booleano.verdadeiro());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusaoOpcional(nome));
		return this;
	}

	@Override
	public Mapeador<ObjetoJson> comCampoColecao(String nome, Mapeador<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeador<ObjetoJson> comCampoColecaoOpcional(String nome, Mapeador<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeador<ObjetoJson> comCampoElemento(String nome, Mapeador<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeador<ObjetoJson> comCampoElementoOpcional(String nome, Mapeador<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorRenomearCampos<ObjetoJson> renomearCampo(String nome, String novoNome) {
		regras.inserirNoFim(new RegraDeMapeamentoDeRenomeacao(nome, novoNome, campos));
		return this;
	}

	@Override
	public MapeadorClonarCampos<ObjetoJson> clonarCampo(String nome, String novoNome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorAdicionarCampos<ObjetoJson> adicionarCampo(String nome, ValorJson valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorTransformarCampos<ObjetoJson> transformarCampo(String nome, TransformadorDeMapeamento<ObjetoJson> transformador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorCamposNaoMapeados<ObjetoJson> ignorarCamposNaoMapeados() {
		this.tratadorDeNaoMapeados = new TratadorDeNaoMapeadosQueIgnora();
		return this;
	}

	@Override
	public MapeadorCamposNaoMapeados<ObjetoJson> incluirCamposNaoMapeados() {
		this.tratadorDeNaoMapeados = new TratadorDeNaoMapeadosQueInclui(campos);
		return this;
	}

	@Override
	public MapeadorCamposNaoMapeados<ObjetoJson> impedirCamposNaoMapeados() {
		this.tratadorDeNaoMapeados = new TratadorDeNaoMapeadosQueImpede(campos);
		return this;
	}

	@Override
	public ObjetoJson mapear(ObjetoJson origem) {
		ObjetoJson mapeado = Json.criarObjeto();
		for (RegraDeMapeamento regra : regras) {
			regra.aplicar(origem, mapeado);
		}
		tratadorDeNaoMapeados.tratar(origem, mapeado);
		return mapeado;
	}
}
