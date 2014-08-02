package br.dominioL.sisPro.mapeadores.json;

import br.dominioL.estruturados.json.ValorJson;

public interface MapeadorJson<T extends ValorJson> extends MapeadorJsonRenomearCampos<T> {
	public MapeadorJson<T> comCampo(String nome);

	public MapeadorJson<T> comCampoOpcional(String nome);

	public MapeadorJson<T> comCampoColecao(String nome, MapeadorJson<T> mapeador);

	public MapeadorJson<T> comCampoColecaoOpcional(String nome, MapeadorJson<T> mapeador);

	public MapeadorJson<T> comCampoElemento(String nome, MapeadorJson<T> mapeador);

	public MapeadorJson<T> comCampoElementoOpcional(String nome, MapeadorJson<T> mapeador);
}
