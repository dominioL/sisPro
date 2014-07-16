package br.dominioL.sisPro.dominio.mapeadores;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

public final class MapeadorObjetoJson {
	public static MapeadorObjetoJson criar() {
		return new MapeadorObjetoJson();
	}
	
	private MapeadorObjetoJson() {}

	public ObjetoJson mapear(ObjetoJson vazio) {
		return Json.criarObjeto();
	}
}
