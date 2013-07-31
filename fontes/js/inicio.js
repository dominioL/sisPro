/*global Cadastro*/
/*global Classe*/
/*global Linda*/
/*global Requeridor*/
/*global SisPro*/
/*global Validador*/

(function () {
	"use strict";

	var Inicio = Classe.criarSingleton({
		inicializar: function () {
			SisProVisao.instancia.mostrarMensagemDeBoasVindas();
		}
	});
	Inicio.instancia();
}());
