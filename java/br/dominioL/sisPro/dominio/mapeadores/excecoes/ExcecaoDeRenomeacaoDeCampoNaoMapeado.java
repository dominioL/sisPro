package br.dominioL.sisPro.dominio.mapeadores.excecoes;

public class ExcecaoDeRenomeacaoDeCampoNaoMapeado extends RuntimeException {
	private static final long serialVersionUID = 3436988456761966416L;

	public ExcecaoDeRenomeacaoDeCampoNaoMapeado() {
		super("O campo deve ser mapeado para ser renomeado.");
	}
}
