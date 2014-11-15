package br.dominioL.sisPro.infraestrutura.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJsonAdicionarCampos<T extends ValorJson> extends MapeadorJsonTransformarCampos<T> {

	public MapeadorJsonAdicionarCampos<T> adicionarCampo(String nome, ValorJson valor);

}
