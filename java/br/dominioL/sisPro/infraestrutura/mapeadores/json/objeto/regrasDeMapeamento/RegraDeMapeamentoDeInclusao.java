package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.excecoes.ExcecaoDeMapeamentoComCampoInexistente;

public final class RegraDeMapeamentoDeInclusao extends RegraDeMapeamentoAbstrata {

	private Texto nome;

	public RegraDeMapeamentoDeInclusao(Texto nome) {
		this.nome = nome;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		if (origem.contem(nome).negar().avaliar()) {
			throw new ExcecaoDeMapeamentoComCampoInexistente();
		}
		mapeado.inserir(nome, origem.fornecer(nome));
	}

}
