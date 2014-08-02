package br.dominioL.sisPro.mapeadores.json;

public interface MapeadorCamposNaoMapeados<T> {
	public MapeadorCamposNaoMapeados<T> ignorarCamposNaoMapeados();

	public MapeadorCamposNaoMapeados<T> incluirCamposNaoMapeados();

	public MapeadorCamposNaoMapeados<T> impedirCamposNaoMapeados();

	public T mapear(T origem);
}
