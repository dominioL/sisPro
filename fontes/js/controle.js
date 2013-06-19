/*global Classe*/
/*global Linda*/

(function (global) {
	"use strict";

	var SisProControle = Classe.criarSingleton({
		inicializar: function () {},

		bloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			for (var indice = 0, tamanho = botoes.length; indice < tamanho; indice++) {
				this.bloquearBotao(botoes.item(indice));
			}
		},

		bloquearBotao: function (botao) {
			botao.setAttribute("disabled", "disabled");
		},

		desbloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			for (var indice = 0, tamanho = botoes.length; indice < tamanho; indice++) {
				this.desbloquearBotao(botoes.item(indice));
			}
		},

		desbloquearBotao: function (botao) {
			botao.removeAttribute("disabled");
		}
	});

	global.SisProControle = SisProControle;
}(this));
