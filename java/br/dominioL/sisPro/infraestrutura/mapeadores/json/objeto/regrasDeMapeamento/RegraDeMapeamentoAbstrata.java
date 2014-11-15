package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.primitivos.Booleano;

public abstract class RegraDeMapeamentoAbstrata implements RegraDeMapeamento {

	@Override
	public final Booleano igual(RegraDeMapeamento outro) {
		return Booleano.iguais(this, outro);
	}

}
