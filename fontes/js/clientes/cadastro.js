/*global Classe*/
/*global Linda*/
/*global Requeridor*/
/*global SisPro*/
/*global Validador*/

(function () {
	"use strict";

	var Cadastro = Classe.criarSingleton({
		inicializar: function () {
			this.validador = new Validador();
			var controle = SisProControle.instancia;
			var modelo = SisPro.instancia;
			modelo.iniciarCampo("nome", this.validador, Validador.NOME);
			controle.adicionarTratadorDeAdicaoDeCampo("adicionarTelefone", "telefone", this.validador, Validador.TELEFONE);
			controle.adicionarTratadorDeAdicaoDeCampo("adicionarEnderecoEletronico", "enderecoEletronico", this.validador, Validador.EMAIL);
			controle.adicionarTratadorDeInclusaoDeCampo("incluirCpf", "cpf", this.validador, Validador.CPF);
			controle.adicionarTratadorDeInclusaoDeCampo("incluirCnpj", "cnpj", this.validador, Validador.CNPJ);
			controle.adicionarTratadorDeInclusaoDeCampo("incluirInscricaoEstadual", "inscricaoEstadual", this.validador, Validador.IE);
			controle.adicionarTratadorDeBotao("cadastrar", this.cadastrar, this);
			this.validador.validar();
		},

		cadastrar: function () {
			var visao = SisProVisao.instancia;
			var modelo = SisPro.instancia;
			modelo.iniciarAtualizacao();
			if (this.validador.validar()) {
				var requisicao = Requeridor.instancia.fornecerRequisicaoDeSalvamento("/clientes");
				var dados = {};
				modelo.adicionarCampoAosDados(dados, "nome");
				modelo.adicionarCamposAosDados(dados, "telefone", "telefones");
				modelo.adicionarCamposAosDados(dados, "enderecoEletronico", "enderecosEletronicos");
				modelo.adicionarCampoAosDados(dados, "cpf");
				modelo.adicionarCampoAosDados(dados, "cnpj");
				modelo.adicionarCampoAosDados(dados, "inscricaoEstadual");
				requisicao.tratarSucesso = this.finalizarCadastroComSucesso.vincularEscopo(this);
				requisicao.enviarPost(JSON.stringify(dados), true);
				console.log(dados);
			} else {
				//TODO
				SisProVisao.instancia.mostrarMensagemDeDadosInvalidos();
				SisPro.instancia.finalizarAtualizacao();
			}
		},

		finalizarCadastroComSucesso: function () {
			//TODO
			SisProVisao.instancia.mostrarMensagemDeCadastroBemSucedido();
			SisPro.instancia.finalizarAtualizacao();
		}
	});
	Cadastro.instancia();
}());
