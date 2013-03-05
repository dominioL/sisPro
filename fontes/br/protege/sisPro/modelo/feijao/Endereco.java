package br.protege.sisPro.modelo.feijao;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONObject;

import br.protege.sisPro.modelo.json.ManipuladorJson;
import br.protege.sisPro.modelo.validacao.Validador;
import br.protege.sisPro.recursos.RecursoAbstrato;
import br.protege.sisPro.recursos.clientes.RecursoCliente;
import br.protege.sisPro.recursos.enderecos.RecursoEndereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Endereco extends RecursoAbstrato {

	@JsonProperty("_id") private String _id;
	@JsonProperty("_rev") private String _rev;
	@JsonProperty("apartamento") private String apartamento;
	@JsonProperty("bloco") private String bloco;
	@JsonProperty("edifícil") private String edifícil;
	@JsonProperty("condomínio") private String condomínio;
	@JsonProperty("número") private String número;
	@JsonProperty("logradouro") private String logradouro;
	@JsonProperty("bairro") private String bairro;
	@JsonProperty("cidade") private String cidade;
	@JsonProperty("estado") private String estado;
	@JsonProperty("cep") private String cep;
	@JsonProperty("tipo") private final String tipo = "endereço";
	@JsonProperty("identificadorDoCliente") private String identificadorDoCliente;

	@Override
	public String fornecer_id() {
		return _id;
	}

	@Override
	public void fixar_id(String _id) {
		this._id = _id;
	}

	@Override
	public String fornecer_rev() {
		return _rev;
	}
	
	@Override
	public void fixar_rev(String _rev) {
		this._rev = _rev;
	}
	
	public String fornecerApartamento() {
		return apartamento;
	}
	
	public void fixarApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	
	public String fornecerBloco() {
		return bloco;
	}
	
	public void fixarBloco(String bloco) {
		this.bloco = bloco;
	}
	
	public String fornecerEdifícil() {
		return edifícil;
	}
	
	public void fixarEdifícil(String edifícil) {
		this.edifícil = edifícil;
	}

	public String fornecerCondomínio() {
		return condomínio;	
	}
	
	public void fixarCondomínio(String condomínio) {
		this.condomínio = condomínio;
	}

	public String fornecerNúmero() {
		return número;
	}
	
	public void fixarNúmero(String número) {
		this.número = número;
	}

	public String fornecerLogradouro() {
		return logradouro;
	}

	public void fixarLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String fornecerBairro() {
		return bairro;
	}

	public void fixarBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String fornecerCidade() {
		return cidade;
	}
	
	public void fixarCidade(String cidade) {
		this.cidade = cidade;
	}

	public String fornecerEstado() {
		return estado;
	}

	public void fixarEstado(String estado) {
		this.estado = estado;
	}
	
	public String fornecerCep() {
		return cep;
	}
	
	public void fixarCep(String cep) {
		this.cep = cep;
	}

	public String fornecerIdentificadorDoCliente() {
		return identificadorDoCliente;
	}

	public void fixarIdentificadorDoCliente(String identificadorDoCliente) {
		this.identificadorDoCliente = identificadorDoCliente;
	}
	
	public String fornecerTipo() {
		return tipo;
	}

	@Override
	public Validador validarCadastro() {
		Validador validador = new Validador();
		_id = validador.validarIdentificador(_id, false);
		_rev = validador.validarRevisão(_rev, false);
		apartamento = validador.validarApartamento(apartamento, false);
		bloco = validador.validarBloco(bloco, false);
		edifícil = validador.validarEdifícil(edifícil, false);
		condomínio = validador.validarCondomínio(condomínio, false);
		número = validador.validarNúmeroDeLogradouro(número, true);
		logradouro = validador.validarLogradouro(logradouro, true);
		bairro = validador.validarBairro(bairro, true);
		cidade = validador.validarCidade(cidade, true);
		estado = validador.validarEstado(estado, true);
		cep = validador.validarCep(cep, false);
		identificadorDoCliente = validador.validarIdentificadorEstrangeiro(identificadorDoCliente, "identificador do cliente", true);

		return validador;
	}

	@Override
	public Validador validarAtualização() {
		Validador validador = validarCadastro();
		_id = validador.validarIdentificador(_id, true);
		_rev = validador.validarRevisão(_rev, true);
		
		return validador;
	}
	
	@Override
	public JSONObject fornecerComoRecursoJson(JSONObject recipiente) {
		JSONObject representaçãoJson = new JSONObject(this);
		ManipuladorJson manipuladorJsonDaRepresentação = new ManipuladorJson(representaçãoJson);
		ManipuladorJson manipuladorJsonDoRecipiente = new ManipuladorJson(recipiente);
		manipuladorJsonDaRepresentação.adicionarCampo(UriBuilder.fromResource(RecursoEndereco.class).build(_id).toString(), "uri");
		manipuladorJsonDaRepresentação.adicionarCampo(UriBuilder.fromResource(RecursoCliente.class).build(identificadorDoCliente).toString(), "uriDoCliente");
		manipuladorJsonDoRecipiente.adicionarObjetoJson(representaçãoJson, "endereço");
		
		return recipiente;
	}
}
