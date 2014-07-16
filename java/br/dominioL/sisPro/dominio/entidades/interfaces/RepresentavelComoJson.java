package br.dominioL.sisPro.dominio.entidades.interfaces;

import br.dominioL.estruturados.json.ObjetoJson;

public interface RepresentavelComoJson {
	public ObjetoJson comoJson();

	public void fixarDados(ObjetoJson dados);
}
