package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;


public interface MapeadorJsonRenomearCampos<T extends ValorJson> extends MapeadorJsonClonarCampos<T> {
	public MapeadorJsonRenomearCampos<T> renomearCampo(String nome, String novoNome);
}
