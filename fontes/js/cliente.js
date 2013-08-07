/*global Classe*/

(function () {
	"use strict";

	var Cliente = Classe.criarSingleton({
		inicializar: function () {
			// TODO
			// SisPro.instancia.adicionarConteudo("/inicio");
		}
	});
	Cliente.instancia();
}());
