(function () {
	"use strict";
	
	var Cadastro = new PrototipoUnico({
		inicializarUnico: function () {
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.adicionarTelefone"))
				.paraClique(this.adicionarTelefone.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.adicioanrEnderecoEletronico"))
				.paraClique(this.adicionarEnderecoEletronico.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.incluirCpf"))
				.paraClique(this.incluirCpf.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.incluirCnpj"))
				.paraClique(this.incluirCnpj.vincularEscopo(this));
			new TratadorDeMouse(Linda.selecionar("section.conteudo button.incluirInscricaoEstadual"))
				.paraClique(this.incluirInscricaoEstadual.vincularEscopo(this));
		},
		
		adicionarTelefone: function () {
			
		},
			
		adicionarEnderecoEletronico: function () {
			
		},
			
		incluirCpf: function () {
			SisProControle.instancia.bloquearBotao(Linda.selecionar("section.conteudo button.incluirCpf"));
		},
		
		incluirCnpj: function () {
			SisProControle.instancia.bloquearBotao(Linda.selecionar("section.conteudo button.incluirCnpj"));
		},
		
		incluirInscricaoEstadual: function () {
			SisProControle.instancia.bloquearBotao(Linda.selecionar("section.conteudo button.incluirInscricaoEstadual"));
		}
	}); 
	Cadastro.instancia();
}());
