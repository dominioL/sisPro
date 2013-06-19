/*global Classe*/
/*global Linda*/
/*global Requeridor*/
/*global SisPro*/

(function () {
	"use strict";

	var Cadastro = Classe.criarSingleton({
		inicializar: function () {
			var secaoConteudo = Linda.selecionar("section.conteudo");
			secaoConteudo.selecionar("button.adicionarTelefone").tratadorDeClique(this.adicionarTelefone.vincularEscopo(this));
			secaoConteudo.selecionar("button.adicionarEnderecoEletronico").tratadorDeClique(this.adicionarEnderecoEletronico.vincularEscopo(this));
			secaoConteudo.selecionar("button.incluirCpf").tratadorDeClique(this.incluirCpf.vincularEscopo(this));
			secaoConteudo.selecionar("button.incluirCnpj").tratadorDeClique(this.incluirCnpj.vincularEscopo(this));
			secaoConteudo.selecionar("button.incluirInscricaoEstadual").tratadorDeClique(this.incluirInscricaoEstadual.vincularEscopo(this));
			secaoConteudo.selecionar("button.cadastrar").tratadorDeClique(this.cadastrar.vincularEscopo(this));
		},

		adicionarTelefone: function () {
			SisPro.instancia.adicionarCampoEmConteudo("telefone");
		},

		adicionarEnderecoEletronico: function () {
			SisPro.instancia.adicionarCampoEmConteudo("enderecoEletronico");
		},

		incluirCpf: function () {
			SisPro.instancia.incluirCampoEmConteudo("incluirCpf", "cpf");
		},

		incluirCnpj: function () {
			SisPro.instancia.incluirCampoEmConteudo("incluirCnpj", "cnpj");
		},

		incluirInscricaoEstadual: function () {
			SisPro.instancia.incluirCampoEmConteudo("incluirInscricaoEstadual", "inscricaoEstadual");
		},

		cadastrar: function () {
			SisPro.instancia.iniciarAtualizacao();
			var requisicao = Requeridor.instancia.fornecerRequisicaoDeSalvamento("/clientes");
			// requisicao.tratarErro = this.finalizarCadastroComErro.vincularEscopo(this);
			// requisicao.tratarSucesso = this.finalizarCadastroComSucesso.vincularEscopo(this);
			requisicao.enviarPost(JSON.stringify({}), true);
		},

		finalizarCadastroComErro: function () {
			//TODO
		},

		finalizarCadastroComSucesso: function () {
			//TODO
		}
	});
	Cadastro.instancia();
}());
