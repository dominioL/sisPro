/*global Classe*/
/*global Linda*/

(function (global) {
	"use strict";

	var SisProVisao = Classe.criarSingleton({
		iniciarAtualizacao: function () {
			var carregador = Linda.selecionar("section.busca > div.carregadorLinear");
			carregador.style.visibility = "visible";
		},

		finalizarAtualizacao: function () {
			var carregador = Linda.selecionar("section.busca > div.carregadorLinear");
			carregador.style.visibility = "hidden";
		},

		carregarConteudo: function (conteudo) {
			var secaoConteudo = Linda.selecionar("section.conteudo");
			var templateConteudo = Linda.selecionar("section.conteudo > template.conteudo");
			var sombraConteudo = secaoConteudo.webkitCreateShadowRoot();
			secaoConteudo.innerHTML = conteudo.body.innerHTML;
			sombraConteudo.appendChild(templateConteudo.content.cloneNode(true));
			templateConteudo.remove();
		},

		mostrarMensagemDeSucesso: function (mensagem) {
			this.mostrarMensagem(mensagem, "sucesso");
		},

		mostrarMensagemDeErro: function (mensagem) {
			this.mostrarMensagem(mensagem, "erro");
		},

		mostrarMensagemDeInformacao: function (mensagem) {
			this.mostrarMensagem(mensagem, "informacao");
		},

		mostrarMensagemDeAviso: function (mensagem) {
			this.mostrarMensagem(mensagem, "aviso");
		},

		mostrarMensagemDeDadosInvalidos: function () {
			this.mostrarMensagemDeErro("Dados inválidos. Verifique se todos os campos estão preenchidos corretamente.");
		},

		mostrarMensagemDeCadastroBemSucedido: function () {
			this.mostrarMensagemDeSucesso("Cadastro realizado com sucesso.");
		},

		mostrarMensagem: function (mensagem, classe) {
			var caixaDeMensagem = Linda.selecionar("section.sistema > p.mensagem");
			caixaDeMensagem.classList.remove("sucesso");
			caixaDeMensagem.classList.remove("erro");
			caixaDeMensagem.classList.remove("informacao");
			caixaDeMensagem.classList.remove("aviso");
			caixaDeMensagem.classList.add(classe);
			caixaDeMensagem.textContent = mensagem;
		},

		selecionarBotao: function (classeDoBotao) {
			var selecao = String.formatar("section.conteudo > nav > button.%@", classeDoBotao);
			return Linda.selecionar(selecao);
		},

		selecionarCampo: function (classeDoCampo) {
			var selecao = String.formatar("section.conteudo > form > input.%@", classeDoCampo);
			return Linda.selecionar(selecao);
		},

		selecionarCampos: function (classeDosCampos) {
			var selecao = String.formatar("section.conteudo > form > input.%@", classeDosCampos);
			return Linda.selecionarTodos(selecao);
		},

		selecionarCampoDeTemplate: function (classeDoCampo) {
			var selecao = String.formatar("input.%@", classeDoCampo);
			return this.selecionarTemplateDeFormulario(classeDoCampo).content.selecionar(selecao);
		},

		selecionarTemplateDeFormulario: function (classeDoTemplate) {
			var selecaoDoTemplate = String.formatar("section.conteudo > form > template.%@", classeDoTemplate);
			return Linda.selecionar(selecaoDoTemplate);
		},

		selecionarFormulario: function () {
			return Linda.selecionar("section.conteudo > form");
		},

		incluirCampo: function (classeDoCampo) {
			var template = this.selecionarTemplateDeFormulario(classeDoCampo);
			var formulario = this.selecionarFormulario();
			var campo = template.content.cloneNode(true);
			formulario.appendChild(campo);
			template.remove();
			return formulario.selecionar("input:last-of-type");
		},

		adicionarCampo: function (classeDoCampo) {
			var template = this.selecionarTemplateDeFormulario(classeDoCampo);
			var formulario  = this.selecionarFormulario();
			var campo = template.content.cloneNode(true);
			formulario.appendChild(campo);
			return formulario.selecionar("input:last-of-type");
		}
	});

	global.SisProVisao = SisProVisao;
}(this));
