package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJsonClonarCampos<T extends ValorJson> extends MapeadorJsonAdicionarCampos<T> {
	public MapeadorJsonClonarCampos<T> clonarCampo(String nome, String novoNome);
}
