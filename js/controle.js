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
			botoes.paraCada(function (botao) {
				botao.bloquear();
			});
		},

		bloquearBotao: function (botao) {
			this.botoesBloqueados.push(botao);
			botao.bloquear();
		},

		desbloquearTodosBotoes: function () {
			var botoes = Linda.selecionarTodos("button");
			botoes.paraCada(function (botao) {
				botao.desbloquear();
			});
			this.botoesBloqueados.paraCada(function (botao) {
				botao.bloquear();
			});
		},

		desbloquearBotao: function (botao) {
			this.botoesBloqueados.removerElemento(botao);
			botao.desbloquear();
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
