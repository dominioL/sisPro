/*global Classe*/
/*global Linda*/

(function (global) {
	"use strict";

	var SisProVisao = Classe.criarSingleton({
		iniciarAtualizacao: function () {
			var carregador = Linda.selecionar("section.pesquisa > div.carregadorLinear");
			carregador.style.visibility = "visible";
		},

		finalizarAtualizacao: function () {
			var carregador = Linda.selecionar("section.pesquisa > div.carregadorLinear");
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

		mostarMensagemDeAviso: function (mensagem) {
			this.mostrarMensagem(mensagem, "aviso");
		},

		mostrarMensagem: function (mensagem, classe) {
			var caixaDeMensagem = Linda.selecionar("section.sistema > p.mensagem");
			caixaDeMensagem.classList.remove("sucesso");
			caixaDeMensagem.classList.remove("erro");
			caixaDeMensagem.classList.remove("informacao");
			caixaDeMensagem.classList.remove("aviso");
			caixaDeMensagem.classList.add(classe);
			caixaDeMensagem.textContent = mensagem;
		}
	});

	global.SisProVisao = SisProVisao;
}(this));
