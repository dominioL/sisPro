/*global Classe*/
/*global Linda*/
/*global SisProVisao*/

(function (global) {
	"use strict";

	var SisProControle = Classe.criarSingleton({
		inicializar: function () {
			this.botoesBloqueados = [];
		},

		bloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			for (var indice = 0, tamanho = botoes.length; indice < tamanho; indice++) {
				botoes.item(indice).setAttribute("disabled", "disabled");
			}
		},

		bloquearBotao: function (botao) {
			this.botoesBloqueados.push(botao);
			botao.setAttribute("disabled", "disabled");
		},

		desbloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			for (var indice = 0, tamanho = botoes.length; indice < tamanho; indice++) {
				botoes.item(indice).removeAttribute("disabled");
			}
			this.botoesBloqueados.paraCada(function (botao) {
				botao.setAttribute("disabled", "disabled");
			});
		},

		desbloquearBotao: function (botao) {
			this.botoesBloqueados.removerElemento(botao);
			botao.removeAttribute("disabled");
		},

		adicionarTratadorDeInclusaoDeCampo: function (entidade, campo) {
			var botao = SisProVisao.instancia.selecionarBotao(campo.botao);
			botao.tratadorDeClique(function () {
				entidade.ativarCampoUnico(campo.nome);
			}.vincularEscopo(this));
		},

		adicionarTratadorDeAdicaoDeCampo: function (entidade, campo) {
			var botao = SisProVisao.instancia.selecionarBotao(campo.botao);
			botao.tratadorDeClique(function () {
				entidade.ativarCampoMultiplo(campo.nome);
			}.vincularEscopo(this));
		},

		adicionarTratadorDeCadastro: function (entidade) {
			var botao = SisProVisao.instancia.selecionarBotao("cadastrar");
			botao.tratadorDeClique(function () {
				entidade.cadastrar();
			}.vincularEscopo(this));
		},

		adicionarTratadorDeAlteracaoEmCampo: function (entidade, campo) {
			campo.tratadorDeAlteracao(function () {
				entidade.validar();
			}.vincularEscopo(this));
		},

		adicionarTratadorDeDigitacaoEmCampo: function (entidade, campo) {
			campo.tratadorDeCaractereDigitado(function () {
				entidade.formatar();
			}.vincularEscopo(this));
		}
	});

	global.SisProControle = SisProControle;
}(this));
