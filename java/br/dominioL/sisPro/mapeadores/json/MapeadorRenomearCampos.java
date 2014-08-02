package br.dominioL.sisPro.mapeadores.json;


public interface MapeadorRenomearCampos<T> extends MapeadorClonarCampos<T> {
	public MapeadorRenomearCampos<T> renomearCampo(String nome, String novoNome);
}
