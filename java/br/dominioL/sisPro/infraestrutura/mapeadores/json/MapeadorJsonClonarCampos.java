package br.dominioL.sisPro.infraestrutura.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJsonClonarCampos<T extends ValorJson> extends MapeadorJsonAdicionarCampos<T> {

	public MapeadorJsonClonarCampos<T> clonarCampo(String nome, String novoNome);

}