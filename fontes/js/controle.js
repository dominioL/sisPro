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
		},

		adicionarTratadorDeInclusaoDeCampo: function (classeDoBotao, classeDoCampo, validador, padraoDeValidacao) {
			var botao = SisProVisao.instancia.selecionarBotao(classeDoBotao);
			botao.tratadorDeClique(function () {
				SisPro.instancia.incluirCampo(classeDoCampo, botao, validador, padraoDeValidacao);
			}.vincularEscopo(this));
		},

		adicionarTratadorDeAdicaoDeCampo: function (classeDoBotao, classeDoCampo, validador, padraoDeValidacao) {
			var botao = SisProVisao.instancia.selecionarBotao(classeDoBotao);
			botao.tratadorDeClique(function () {
				SisPro.instancia.adicionarCampo(classeDoCampo, validador, padraoDeValidacao);
			}.vincularEscopo(this));
		},

		adicionarTratadorDeAlteracaoEmCampo: function (campo, validador) {
			campo.tratadorDeAlteracao(function () {
				SisPro.instancia.validarFormulario(validador);
			}.vincularEscopo(this));
		},

		adicionarTratadorDeBotao: function (classeDoBotao, tratador, escopo) {
			var botao = SisProVisao.instancia.selecionarBotao(classeDoBotao);
			botao.tratadorDeClique(tratador.vincularEscopo(escopo));
		}
	});

	global.SisProControle = SisProControle;
}(this));
