package br.dominioL.sisPro.dominio.mapeadores;


public interface MapeadorRenomearCampos<T> extends MapeadorClonarCampos<T> {
	public MapeadorRenomearCampos<T> renomearCampo(String nome, String novoNome);
}
