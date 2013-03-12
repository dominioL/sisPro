(function (global) {
	"use strict";
	
	var SisProControle = new PrototipoUnico({
		inicializarUnico: function () {
			
		},
		
		bloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			for (var indice = 0; indice < botoes.length; indice++) {
				this.bloquearBotao(botoes.item(indice));
			}
		},
		
		bloquearBotao: function (botao) {
			botao.setAttribute("disabled", "disabled");
		},
		
		desbloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			for (var indice = 0; indice < botoes.length; indice++) {
				this.desbloquearBotao(botoes.item(indice));
			}
		},
		
		desbloquearBotao: function (botao) {
			botao.removeAttribute("disabled");
		}
	});
	
	global.SisProControle = SisProControle;
}(this));
