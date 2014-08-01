package br.dominioL.sisPro.dominio.mapeadores;


public interface Mapeador<T> extends MapeadorRenomearCampos<T> {
	public Mapeador<T> comCampo(String nome);

	public Mapeador<T> comCampoOpcional(String nome);

	public Mapeador<T> comCampoColecao(String nome, Mapeador<T> mapeador);

	public Mapeador<T> comCampoColecaoOpcional(String nome, Mapeador<T> mapeador);

	public Mapeador<T> comCampoElemento(String nome, Mapeador<T> mapeador);

	public Mapeador<T> comCampoElementoOpcional(String nome, Mapeador<T> mapeador);
}
