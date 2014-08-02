package br.dominioL.sisPro.mapeadores.json.excecoes;

public final class ExcecaoDeManipulacaoDeCampoNaoMapeado extends RuntimeException {
	private static final long serialVersionUID = -7062629849328351929L;

	public ExcecaoDeManipulacaoDeCampoNaoMapeado() {
		super("O campo deve ser mapeado para que possa ser renomeado ou clonado.");
	}
}
