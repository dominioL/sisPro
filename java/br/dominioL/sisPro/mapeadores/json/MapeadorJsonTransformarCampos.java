 package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJsonTransformarCampos<T extends ValorJson> extends MapeadorJsonCamposNaoMapeados<T> {
	public MapeadorJsonTransformarCampos<T> transformarCampo(String nome, TransformadorDeMapeamentoJson<T> transformador);
}
