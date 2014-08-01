package br.dominioL.sisPro.dominio.mapeadores;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorAdicionarCampos<T> extends MapeadorTransformarCampos<T> {
	public MapeadorAdicionarCampos<T> adicionarCampo(String nome, ValorJson valor);
}
