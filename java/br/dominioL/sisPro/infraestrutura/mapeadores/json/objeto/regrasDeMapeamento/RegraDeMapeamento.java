package br.dominioL.sisPro.infraestrutura.mapeadores.json.objeto.regrasDeMapeamento;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.json.ObjetoJson;

public interface RegraDeMapeamento extends Igualavel<RegraDeMapeamento> {

	public abstract void aplicar(ObjetoJson origem, ObjetoJson mapeado);

}
