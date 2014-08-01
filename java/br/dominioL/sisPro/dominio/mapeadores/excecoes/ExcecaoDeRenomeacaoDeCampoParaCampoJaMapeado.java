package br.dominioL.sisPro.dominio.mapeadores.excecoes;

public class ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado extends RuntimeException {
	private static final long serialVersionUID = 3050849911960256384L;

	public ExcecaoDeRenomeacaoDeCampoParaCampoJaMapeado() {
		super("O novo campo não pode já ter sido mapeado.");
	}
}
