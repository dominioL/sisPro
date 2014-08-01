package br.dominioL.sisPro.dominio.mapeadores.objetoJson.regrasDeMapeamento;

import br.dominioL.estruturados.json.ObjetoJson;

public abstract class RegraDeMapeamentoAbstrata implements RegraDeMapeamento {
	public abstract void aplicar(ObjetoJson origem, ObjetoJson mapeado);

	@Override
	public Boolean igual(RegraDeMapeamento outro) {
		return (this == outro);
	}
}
