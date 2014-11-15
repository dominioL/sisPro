package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.transformacao.TransformadorJson;

public final class RegraDeMapeamentoDeTransformacao extends RegraDeMapeamentoAbstrata {

	private Texto nome;
	private TransformadorJson transformador;
	private Mapa<Texto, Booleano> camposMapeados;
	private Mapa<Texto, Booleano> camposNovosMapeados;

	public RegraDeMapeamentoDeTransformacao(Texto nome, TransformadorJson transformador, Mapa<Texto, Booleano> camposMapeados, Mapa<Texto, Booleano> camposNovosMapeados) {
		this.nome = nome;
		this.transformador = transformador;
		this.camposMapeados = camposMapeados;
		this.camposNovosMapeados = camposNovosMapeados;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (camposMapeados.contem(nome).negar().avaliar() && camposNovosMapeados.contem(nome).negar().avaliar()) {
			throw new ExcecaoDeTransformacaoDeCampoNaoMapeadoOuNaoManipulado();
		}
		if (mapeado.contem(nome).avaliar()) {
			mapeado.inserir(nome, transformador.transformar(mapeado.fornecer(nome)));
		}
	}

}
