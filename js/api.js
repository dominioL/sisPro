/*global Classe*/
/*global Requeridor*/
/*global SisPro*/
/*global SisProVisao*/

(function () {
	"use strict";

	var Recurso = Classe.criar({
		inicializar: function (uri) {
			this.uri = uri;
			this.apis = [];
		},

		comApi: function (metodo, tipoDeMidia, tipoDeMidiaAceita) {
			var tipoDeMidiaTextual = Linda.nulo(tipoDeMidia) ? "" : tipoDeMidia.comoTexto();
			var tipoDeMidiaAceitaTextual = Linda.nuloOuIndefinido(tipoDeMidiaAceita) ? "" : tipoDeMidiaAceita.comoTexto();
			this.apis.push({metodo: metodo, tipoDeMidiaAceita: tipoDeMidiaAceitaTextual, tipoDeMidia: tipoDeMidiaTextual});
			return this;
		}
	});

	var Api = Classe.criarSingleton({
		inicializar: function () {
			SisPro.instancia.iniciarAtualizacao();
			var requisicao = Requeridor.instancia.fornecerRequisicaoDePedidoJson("/json/api");
			requisicao.tratarSucesso = this.mostrarRecursos.vincularEscopo(this);
			requisicao.enviarGet(true);
		},

		mostrarRecursos: function (recursos) {
			recursos.paraCada(function (recurso) {
				SisProVisao.instancia.adicionarRecurso(recurso);
				SisPro.instancia.finalizarAtualizacao();
			});
		}
	});
	Api.instancia();
}());
