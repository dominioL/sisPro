package br.dominioL.sisPro.infraestrutura.mapeadores.json;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ConstrutorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamento;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeAdicao;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeClonagem;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusao;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusaoComMapeador;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeInclusaoOpcional;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeRenomeacao;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento.RegraDeMapeamentoDeTransformacao;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeados;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueIgnora;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueImpede;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.tratadoresDeNaoMapeamento.TratadorDeNaoMapeadosQueInclui;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.transformacao.TransformadorJson;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.transformacao.TransformadorJsonQueSubstitui;

public final class MapeadorJsonDeObjeto extends MapeadorJson<ObjetoJson> {

	static MapeadorJsonDeObjeto criar() {
		return new MapeadorJsonDeObjeto();
	}

	private ListaEncadeada<RegraDeMapeamento> regras;
	private Mapa<Texto, Booleano> campos;
	private Mapa<Texto, Booleano> camposNovos;
	private TratadorDeNaoMapeados tratadorDeNaoMapeados;

	private MapeadorJsonDeObjeto() {
		regras = ListaEncadeada.criar();
		campos = MapaLista.criar();
		camposNovos = MapaLista.criar();
		ignorarCamposNaoMapeados();
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampo(String nome) {
		Texto nomeDoCampo = Texto.criar(nome);
		campos.inserir(nomeDoCampo, Booleano.falso());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusao(nomeDoCampo));
		return this;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoOpcional(String nome) {
		Texto nomeDoCampo = Texto.criar(nome);
		campos.inserir(Texto.criar(nome), Booleano.verdadeiro());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusaoOpcional(nomeDoCampo));
		return this;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampo(String nome, MapeadorJsonCamposNaoMapeados<ObjetoJson> mapeador) {
		Texto nomeDoCampo = Texto.criar(nome);
		campos.inserir(nomeDoCampo, Booleano.falso());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusaoComMapeador(nomeDoCampo, mapeador, new RegraDeMapeamentoDeInclusao(nomeDoCampo)));
		return this;
	}

	@Override
	public MapeadorJson<ObjetoJson> comCampoOpcional(String nome, MapeadorJsonCamposNaoMapeados<ObjetoJson> mapeador) {
		Texto nomeDoCampo = Texto.criar(nome);
		campos.inserir(nomeDoCampo, Booleano.verdadeiro());
		regras.inserirNoFim(new RegraDeMapeamentoDeInclusaoComMapeador(nomeDoCampo, mapeador, new RegraDeMapeamentoDeInclusaoOpcional(nomeDoCampo)));
		return null;
	}

	@Override
	public MapeadorJsonRenomearCampos<ObjetoJson> renomearCampo(String nome, String novoNome) {
		Texto nomeDoCampo = Texto.criar(nome);
		Texto novoNomeDoCampo = Texto.criar(novoNome);
		camposNovos.inserir(novoNomeDoCampo, Booleano.falso());
		regras.inserirNoFim(new RegraDeMapeamentoDeRenomeacao(nomeDoCampo, novoNomeDoCampo, campos));
		return this;
	}

	@Override
	public MapeadorJsonClonarCampos<ObjetoJson> clonarCampo(String nome, String nomeDoClone) {
		Texto nomeDoCampo = Texto.criar(nome);
		Texto nomeDoCloneDoCampo = Texto.criar(nomeDoClone);
		camposNovos.inserir(nomeDoCloneDoCampo, Booleano.falso());
		regras.inserirNoFim(new RegraDeMapeamentoDeClonagem(nomeDoCampo, nomeDoCloneDoCampo, campos));
		return this;
	}

	@Override
	public MapeadorJsonAdicionarCampos<ObjetoJson> adicionarCampo(String nome, ValorJson valor) {
		Texto nomeDoCampo = Texto.criar(nome);
		camposNovos.inserir(Texto.criar(nome), Booleano.verdadeiro());
		regras.inserirNoFim(new RegraDeMapeamentoDeAdicao(nomeDoCampo, valor, campos));
		return this;
	}

	@Override
	public MapeadorJsonTransformarCampos<ObjetoJson> transformarCampo(String nome, TransformadorJson transformador) {
		Texto nomeDoCampo = Texto.criar(nome);
		regras.inserirNoFim(new RegraDeMapeamentoDeTransformacao(nomeDoCampo, transformador, campos, camposNovos));
		return this;
	}

	@Override
	public MapeadorJsonTransformarCampos<ObjetoJson> transformarCampo(String nome, ValorJson valor) {
		transformarCampo(nome, new TransformadorJsonQueSubstitui(valor));
		return this;
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
		ObjetoJson mapeado = ConstrutorJson.deObjeto().construir();
		for (RegraDeMapeamento regra : regras) {
			regra.aplicar(origem, mapeado);
		}
		tratadorDeNaoMapeados.tratar(origem, mapeado);
		return mapeado;
	}

}