package br.dominioL.sisPro.dominio.mapeadores;

public interface MapeadorCamposNaoMapeados<T> {
	public MapeadorCamposNaoMapeados<T> ignorarCamposNaoMapeados();

	public MapeadorCamposNaoMapeados<T> incluirCamposNaoMapeados();

	public MapeadorCamposNaoMapeados<T> impedirCamposNaoMapeados();

	public T mapear(T origem);
}
