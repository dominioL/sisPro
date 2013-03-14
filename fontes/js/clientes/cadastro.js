(function () {
	"use strict";
	
	var Cadastro = new PrototipoUnico({
		inicializarUnico: function () {
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.adicionarTelefone"))
				.paraClique(this.adicionarTelefone.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.adicionarEnderecoEletronico"))
				.paraClique(this.adicionarEnderecoEletronico.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.incluirCpf"))
				.paraClique(this.incluirCpf.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.incluirCnpj"))
				.paraClique(this.incluirCnpj.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.incluirInscricaoEstadual"))
				.paraClique(this.incluirInscricaoEstadual.vincularEscopo(this));
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
		}
	});
	Cadastro.instancia();
}());
