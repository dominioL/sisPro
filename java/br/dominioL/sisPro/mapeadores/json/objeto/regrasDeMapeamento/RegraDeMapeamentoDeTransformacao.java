package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.Booleano;
import br.dominioL.estruturados.elemento.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.mapeadores.json.excecoes.ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado;
import br.dominioL.sisPro.mapeadores.json.transformacao.TransformadorJson;

public final class RegraDeMapeamentoDeTransformacao extends RegraDeMapeamentoAbstrata {
	private String nome;
	private TransformadorJson transformador;
	private Mapa<Texto, Booleano> camposMapeados;
	private Mapa<Texto, Booleano> camposNovosMapeados;

	public RegraDeMapeamentoDeTransformacao(String nome, TransformadorJson transformador, Mapa<Texto,Booleano> camposMapeados, Mapa<Texto,Booleano> camposNovosMapeados) {
		this.nome = nome;
		this.transformador = transformador;
		this.camposMapeados = camposMapeados;
		this.camposNovosMapeados = camposNovosMapeados;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		Texto chave = Texto.criar(nome);
		if (!camposMapeados.contem(chave) && !camposNovosMapeados.contem(chave)) {
			throw new ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado();
		}
		if (mapeado.contem(nome)) {
			mapeado.inserir(nome, transformador.transformar(mapeado.fornecer(nome)));
		}
	}
}
