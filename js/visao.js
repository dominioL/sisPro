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
			var secaoConteudos = Linda.selecionar("section.conteudos");
			var templateConteudo = secaoConteudos.selecionar("template.conteudo");
			var secaoConteudo = templateConteudo.content.selecionar("section.conteudo");
			secaoConteudo.innerHTML = conteudo.body.innerHTML;
			secaoConteudos.appendChild(templateConteudo.content.cloneNode(true));
		},

		limparConteudo: function () {
			var secoesConteudo = Linda.selecionarTodos("section.conteudo");
			secoesConteudo.paraCada(function (secaoConteudo) {
				secaoConteudo.remove();
			}, this);
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

		mostrarMensagem: function (mensagem, classe) {
			var selecaoDeContainer = String.formatar("section.sistema > div.mensagem.%@", classe);
			var selecaoDeContainers = "section.sistema > div.mensagem";
			var selecaoDeCaixaDeMensagem = "p";
			var containersDeMensagens = Linda.selecionarTodos(selecaoDeContainers);
			var containerDeMensagem = Linda.selecionar(selecaoDeContainer);
			var caixaDeMensagem = containerDeMensagem.selecionar(selecaoDeCaixaDeMensagem);
			containersDeMensagens.paraCada(function (container) {
				container.classList.remove("habilitado");
			}, this);
			containerDeMensagem.offsetWidth = caixaDeMensagem.offsetWidth;
			containerDeMensagem.classList.add("habilitado");
			caixaDeMensagem.textContent = mensagem;
		},

		selecionarBotao: function (classe) {
			var selecao = String.formatar("section.conteudo > nav > button.%@", classe);
			return Linda.selecionar(selecao);
		},

		selecionarCampo: function (classe) {
			var selecao = String.formatar("section.conteudo > form > input.%@", classe);
			return Linda.selecionar(selecao);
		},

		selecionarCampos: function (classe) {
			var selecao = String.formatar("section.conteudo > form > input.%@", classe);
			return Linda.selecionarTodos(selecao);
		},

		selecionarTemplateDeConteudo: function (classe) {
			var selecao = String.formatar("section.conteudo template.%@", classe);
			return Linda.selecionar(selecao);
		},

		selecionarTemplateDeFormulario: function (classe) {
			var selecao = String.formatar("section.conteudo > form > template.%@", classe);
			return Linda.selecionar(selecao);
		},

		selecionarCampoDeTemplate: function (classe) {
			var selecao = String.formatar("input.%@", classe);
			return this.selecionarTemplateDeFormulario(classe).content.selecionar(selecao);
		},

		selecionarLista: function (classe) {
			var selecao = String.formatar("section.conteudo ul.%@", classe);
			return Linda.selecionar(selecao);
		},

		selecionarFormulario: function () {
			return Linda.selecionar("section.conteudo > form");
		},

		incluirCampo: function (classe) {
			var template = this.selecionarTemplateDeFormulario(classe);
			var formulario = this.selecionarFormulario();
			var campo = template.content.cloneNode(true);
			formulario.appendChild(campo);
			template.remove();
			return formulario.selecionar("input:last-of-type");
		},

		adicionarCampo: function (classe) {
			var template = this.selecionarTemplateDeFormulario(classe);
			var formulario  = this.selecionarFormulario();
			var campo = template.content.cloneNode(true);
			formulario.appendChild(campo);
			return formulario.selecionar("input:last-of-type");
		},

		adicionarRecurso: function (recursoJson) {
			var recursos = this.selecionarLista("recursos");
			var templateRecurso = recursos.selecionar("template.recurso");
			var recurso = templateRecurso.content.cloneNode(true);
			var uri = recurso.selecionar("h2.uri");
			var apis = recurso.selecionar("ul.apis");
			uri.textContent = recursoJson.uri;
			recursoJson.apis.paraCada(function (apiJson) {
				var templateApi = recurso.selecionar("template.api");
				var api = templateApi.content.cloneNode(true);
				var metodo = api.selecionar("dl.atributos > dd.metodo");
				var tipoDeMidiaAceita = api.selecionar("dl.atributos > dd.tipoDeMidiaAceita");
				var tipoDeMidia = api.selecionar("dl.atributos > dd.tipoDeMidia");
				metodo.textContent = apiJson.metodo;
				if (!Linda.nuloOuIndefinido(apiJson.tipoDeMidiaAceita)) {
					tipoDeMidiaAceita.textContent = apiJson.tipoDeMidiaAceita;
				}
				tipoDeMidia.textContent = apiJson.tipoDeMidia;
				apis.appendChild(api);
			}, this);
			recursos.appendChild(recurso);
		},

		obterValor: function (elemento) {
			return elemento.value;
		},

		fixarValor: function (elemento, valor) {
			elemento.setAttribute("value", valor);
			elemento.value = valor;
		},

		fixarValidacaoEmCampo: function (campo, validacao) {
			campo.setCustomValidity(validacao);
		}
	});

	var Mensagens = Classe.criarEnumeracaoDeConstantes({
		DADOS_INVALIDOS: "Dados inválidos. Verifique se todos os campos foram preenchidos corretamente.",
		CADASTRO_BEM_SUCEDIDO: "Cadastro realizado com sucesso.",
		BOAS_VINDAS: "Bem vindo ao Sistema Protege Redes & Telas de gerenciamento.",
		ESTOURO_DE_TEMPO: "Tempo limite atingido.",
		OPERACAO_ABORTADA: "Operação abortada.",
		ERRO: "Problema interno."
	});

	global.Mensagens = Mensagens;
	global.SisProVisao = SisProVisao;
}(this));
