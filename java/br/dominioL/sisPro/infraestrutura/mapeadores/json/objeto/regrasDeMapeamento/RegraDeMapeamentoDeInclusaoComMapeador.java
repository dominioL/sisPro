package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.infraestrutura.mapeadores.json.MapeadorJsonCamposNaoMapeados;

public class RegraDeMapeamentoDeInclusaoComMapeador extends RegraDeMapeamentoAbstrata {

	private Texto nome;
	private MapeadorJsonCamposNaoMapeados<ObjetoJson> mapeador;
	private RegraDeMapeamento regra;

	public RegraDeMapeamentoDeInclusaoComMapeador(Texto nome, MapeadorJsonCamposNaoMapeados<ObjetoJson> mapeador, RegraDeMapeamento regra) {
		this.nome = nome;
		this.mapeador = mapeador;
		this.regra = regra;
	}

	@Override
	public void aplicar(ObjetoJson origem, ObjetoJson mapeado) {
		regra.aplicar(origem, mapeado);
		if (mapeado.contem(nome).avaliar()) {
			mapeado.substituir(nome, mapeador.mapear(mapeado.fornecer(nome).comoObjeto()));
		}
	}

}
