package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;
import br.dominioL.sisPro.mapeadores.json.transformacao.TransformadorJson;

public interface MapeadorJsonTransformarCampos<T extends ValorJson> extends MapeadorJsonCamposNaoMapeados<T> {
	public MapeadorJsonTransformarCampos<T> transformarCampo(String nome, TransformadorJson transformador);

	public MapeadorJsonTransformarCampos<T> transformarCampo(String nome, ValorJson valor);
}
