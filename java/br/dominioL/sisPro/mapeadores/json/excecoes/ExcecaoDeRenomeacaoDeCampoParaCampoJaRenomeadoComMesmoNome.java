package br.dominioL.sisPro.mapeadores.json.excecoes;

public class ExcecaoDeRenomeacaoDeCampoParaCampoJaRenomeadoComMesmoNome extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcecaoDeRenomeacaoDeCampoParaCampoJaRenomeadoComMesmoNome() {
		super("O novo campo não pode ser usado mais de uma vez para renomear.");
	}
}
