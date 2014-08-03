package br.dominioL.sisPro.mapeadores.json.objeto.regrasDeMapeamento;

public abstract class RegraDeMapeamentoAbstrata implements RegraDeMapeamento {
	@Override
	public final Boolean igual(RegraDeMapeamento outro) {
		return (this == outro);
	}
}
