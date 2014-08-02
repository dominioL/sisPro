package br.dominioL.sisPro.mapeadores.json.objeto;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.sisPro.mapeadores.json.MapeadorJson;
import br.dominioL.sisPro.mapeadores.json.MapeadorJsonAdicionarCampos;
import br.dominioL.sisPro.mapeadores.json.MapeadorJsonCamposNaoMapeados;
import br.dominioL.sisPro.mapeadores.json.MapeadorJsonClonarCampos;
import br.dominioL.sisPro.mapeadores.json.MapeadorJsonRenomearCampos;
import br.dominioL.sisPro.mapeadores.json.MapeadorJsonTransformarCampos;
import br.dominioL.sisPro.mapeadores.json.TransformadorDeMapeamentoJson;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamento;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeClonagem;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusao;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusaoOpcional;
import br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeRenomeacao;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeados;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueIgnora;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueImpede;
import br.dominioL.sisPro.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueInclui;

public final class MapeadorObjetoJson implements MapeadorJson<ObjetoJson> {
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
	public MapeadorJson<ObjetoJson> comCampo(String nome) {
		campos.inserir(Texto.comValor(nome), Booleano.falso());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusao(nome));
		return this;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoOpcional(String nome) {
		campos.inserir(Texto.comValor(nome), Booleano.verdadeiro());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusaoOpcional(nome));
		return this;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoColecao(String nome, MapeadorJson<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoColecaoOpcional(String nome, MapeadorJson<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoElemento(String nome, MapeadorJson<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoElementoOpcional(String nome, MapeadorJson<ObjetoJson> mapeador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorJsonRenomearCampos<ObjetoJson> renomearCampo(String nome, String novoNome) {
		regras.inserirNoFim(new RegraDeMapeamentoDeRenomeacao(nome, novoNome, campos));
		return this;
	}

	@Override
	public MapeadorJsonClonarCampos<ObjetoJson> clonarCampo(String nome, String nomeDoClone) {
		regras.inserirNoFim(new RegraDeMapeamentoDeClonagem(nome, nomeDoClone, campos));
		return this;
	}

	@Override
	public MapeadorJsonAdicionarCampos<ObjetoJson> adicionarCampo(String nome, ValorJson valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorJsonTransformarCampos<ObjetoJson> transformarCampo(String nome, TransformadorDeMapeamentoJson<ObjetoJson> transformador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MapeadorJsonCamposNaoMapeados<ObjetoJson> ignorarCamposNaoMapeados() {
		this.tratadorDeNaoMapeados = new TratadorDeNaoMapeadosQueIgnora();
		return this;
	}

	@Override
	public MapeadorJsonCamposNaoMapeados<ObjetoJson> incluirCamposNaoMapeados() {
		this.tratadorDeNaoMapeados = new TratadorDeNaoMapeadosQueInclui(campos);
		return this;
	}

	@Override
	public MapeadorJsonCamposNaoMapeados<ObjetoJson> impedirCamposNaoMapeados() {
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
