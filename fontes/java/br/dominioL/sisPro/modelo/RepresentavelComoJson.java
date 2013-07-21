package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.json.ObjetoJson;

public interface RepresentavelComoJson {
	public ObjetoJson comoJson();

	public void fixarDados(ObjetoJson dados);
}
