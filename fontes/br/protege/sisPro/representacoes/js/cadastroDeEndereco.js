new CadastroDeEndereco();

function CadastroDeEndereco() {
	
	var $ = this;
	this.botãoCadastrarEndereço = null;
	this.uriDoEndereço = null;
		
	this.inicializar = function() {
		$.botãoCadastrarEndereço = P.obterElementoPeloId("botãoCadastrarEndereço");
		$.uriDoEndereço = P.obterElementoPeloId("uriDoEndereço");
		$.botãoCadastrarEndereço.onclick = $.cadastrarEndereço;
	}
	
	this.cadastrarEndereço = function () {
		P.habilitarÍconeDeCarregamento();
		P.desabilitarBotão(botãoCadastrarEndereço);
		var endereço = {
			cep: P.obterValorDoCampo("cep"),
			estado: P.obterValorDoCampo("estado"),
			cidade: P.obterValorDoCampo("cidade"),
			bairro: P.obterValorDoCampo("bairro"),
			logradouro: P.obterValorDoCampo("logradouro"),
			número: P.obterValorDoCampo("número"),
			condomínio: P.obterValorDoCampo("condomínio"),
			edifícil: P.obterValorDoCampo("edifícil"),
			bloco: P.obterValorDoCampo("bloco"),
			apartamento: P.obterValorDoCampo("apartamento"),
			identificadorDoCliente: P.informaçõesJson.cliente._id
		};
		var requisiçãoJson = new RequisicaoHttpJson(P.informaçõesJson.uriEndereços, "POST");
		requisiçãoJson.sucesso = function(resposta) {
			$.uriDoEndereço.href = resposta.endereço.uri;
		};
		requisiçãoJson.concluirRequisição = function() {
			P.habilitarBotão(botãoCadastrarEndereço);
			P.desabilitarÍconeDeCarrregamento();
		};
		requisiçãoJson.enviarRequisição(endereço);
	}
	
	$.inicializar();
}