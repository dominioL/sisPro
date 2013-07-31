/*global Cadastro*/
/*global Classe*/
/*global Linda*/
/*global Requeridor*/
/*global SisPro*/
/*global Validador*/

(function () {
	"use strict";

	var CadastroDeCliente = Classe.criarSingleton({
		estende: Cadastro,

		inicializar: function () {
			Cadastro.prototipo.inicializar.chamarComEscopo(this, "/clientes");
			var nome = new Campo("nome");
			var telefone = new Campo("telefone");
			var email = new Campo("enderecoEletronico");
			var cpf = new Campo("cpf");
			var cnpj = new Campo("cnpj");
			var ie = new Campo("inscricaoEstadual");
			nome.comPadrao(Validador.NOME);
			telefone.comPadrao(Validador.TELEFONE);
			email.comPadrao(Validador.EMAIL);
			cpf.comPadrao(Validador.CPF);
			cnpj.comPadrao(Validador.CNPJ);
			ie.comPadrao(Validador.IE);
			telefone.comBotao("adicionarTelefone");
			email.comBotao("adicionarEnderecoEletronico");
			cpf.comBotao("incluirCpf");
			cnpj.comBotao("incluirCnpj");
			ie.comBotao("incluirInscricaoEstadual");
			telefone.comNomeDeAtributo("telefones");
			email.comNomeDeAtributo("enderecosEletronicos");
			telefone.comFormato("(##) ####-####");
			cpf.comFormato("###.###.###-##");
			cnpj.comFormato("##.###.###/####-##");
			this.iniciarCampoObrigatorio(nome);
			this.iniciarCampoMultiplo(telefone);
			this.iniciarCampoMultiplo(email);
			this.iniciarCampoUnico(cpf);
			this.iniciarCampoUnico(cnpj);
			this.iniciarCampoUnico(ie);
		}
	});
	CadastroDeCliente.instancia();
}());
