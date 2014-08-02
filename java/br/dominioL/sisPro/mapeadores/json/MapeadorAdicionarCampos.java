package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorAdicionarCampos<T> extends MapeadorTransformarCampos<T> {
	public MapeadorAdicionarCampos<T> adicionarCampo(String nome, ValorJson valor);
}
