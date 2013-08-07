/*global Cadastro*/
/*global Campo*/
/*global Classe*/
/*global Validador*/

(function () {
	"use strict";

	var CadastroDeCliente = Classe.criarSingleton({
		estende: Cadastro,

		inicializar: function () {
			Cadastro.prototipo.inicializar.chamarComEscopo(this, "/clientes");
			this.iniciarCampoObrigatorio(new Campo("nome")
				.comPadrao(Validador.NOME));
			this.iniciarCampoMultiplo(new Campo("telefone")
				.comPadrao(Validador.TELEFONE)
				.comBotao("adicionarTelefone")
				.comNomeDeAtributo("telefones")
				.comFormato("(##) ####-####"));
			this.iniciarCampoMultiplo(new Campo("enderecoEletronico")
				.comPadrao(Validador.EMAIL)
				.comBotao("adicionarEnderecoEletronico")
				.comNomeDeAtributo("enderecosEletronicos"));
			this.iniciarCampoUnico(new Campo("cpf")
				.comPadrao(Validador.CPF)
				.comBotao("incluirCpf")
				.comFormato("###.###.###-##"));
			this.iniciarCampoUnico(new Campo("cnpj")
				.comPadrao(Validador.CNPJ)
				.comBotao("incluirCnpj")
				.comFormato("##.###.###/####-##"));
			this.iniciarCampoUnico(new Campo("inscricaoEstadual")
				.comPadrao(Validador.IE)
				.comBotao("incluirInscricaoEstadual"));
		}
	});
	CadastroDeCliente.instancia();
}());
