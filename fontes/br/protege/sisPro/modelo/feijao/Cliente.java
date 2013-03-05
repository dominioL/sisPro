package br.protege.sisPro.modelo.feijao;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.json.JSONObject;

import br.protege.sisPro.modelo.json.ManipuladorJson;
import br.protege.sisPro.modelo.validacao.Validador;
import br.protege.sisPro.recursos.RecursoAbstrato;
import br.protege.sisPro.recursos.clientes.RecursoCliente;
import br.protege.sisPro.recursos.enderecos.RecursoCadastroDeEndereço;
import br.protege.sisPro.recursos.enderecos.RecursoEndereco;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Cliente extends RecursoAbstrato {
	
	@JsonProperty("_id") private String _id;
	@JsonProperty("_rev") private String _rev;
	@JsonProperty("nome") private String nome;
	@JsonProperty("site") private String site;
	@JsonProperty("endereçosEletrônicos") private List<String> endereçosEletrônicos;
	@JsonProperty("telefones") private List<String> telefones;
	@JsonProperty("cpf") private String cpf;
	@JsonProperty("cnpj") private String cnpj;
	@JsonProperty("inscriçãoEstadual") private String inscriçãoEstadual;
	@JsonProperty("informaçõesExtras") private String informaçõesExtras;
	@JsonProperty("tipo") private String tipo;
	@JsonProperty("identificadoresDosEndereços") private List<String> identificadoresDosEndereços;
	
	private static final String TIPO_PESSOA_JURÍDICA = "pessoaJurídica";
	private static final String TIPO_PESSOA_FISICA = "pessoaFisica";
	
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
	
	public String fornecerNome() {
		return nome;
	}
	
	public void fixarNome(String nome) {
		this.nome = nome;
	}
	
	public String fornecerSite() {
		return site;
	}
	
	public void fixarSite(String site) {
		this.site = site;
	}
	
	public List<String> fornecerEndereçosEletrônicos() {
		return endereçosEletrônicos;
	}
	
	public void fixarEndereçosEletrônicos(List<String> endereçosEletrônicos) {
		this.endereçosEletrônicos = endereçosEletrônicos;
	}
	
	public List<String> fornecerTelefones() {
		return telefones;
	}
	
	public void fixarTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
	
	public String fornecerCpf() {
		return cpf;
	}
	
	public void fixarCpf(String  cpf) {
		this.cpf = cpf;
	}
	
	public String fornecerCnpj() {
		return cnpj;
	}
	
	public void fixarCnpj(String  cnpj) {
		this.cnpj = cnpj;
	}
	
	public String fornecerInscriçãoEstadual() {
		return inscriçãoEstadual;
	}

	public void fixarInscriçãoEstadual(String inscriçãoEstadual) {
		this.inscriçãoEstadual = inscriçãoEstadual;
	}
	
	public String fornecerInformaçõesExtras() {
		return informaçõesExtras;
	}
	
	public void fixarInformaçõesExtras(String informaçõesExtras) {
		this.informaçõesExtras = informaçõesExtras;
	}
	
	public String fornecerTipo() {
		return tipo;
	}
	
	public void fixarTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<String> fornecerIdentificadoresDosEndereços() {
		return identificadoresDosEndereços;
	}
	
	public void fixarIdentificadoresDosEndereços(List<String> identificadoresDosEndereços) {
		this.identificadoresDosEndereços = identificadoresDosEndereços;
	}
	
	@Override
	public JSONObject fornecerComoRecursoJson(JSONObject recipiente) {
		JSONObject representaçãoJson = new JSONObject(this);
		ManipuladorJson manipuladorJsonDoRecipiente = new ManipuladorJson(recipiente);
		ManipuladorJson manipuladorJsonDaRepresentação = new ManipuladorJson(representaçãoJson);
		manipuladorJsonDaRepresentação.adicionarCampo(UriBuilder.fromResource(RecursoCliente.class).build(_id).toString(), "uri");
		manipuladorJsonDaRepresentação.adicionarCampo(UriBuilder.fromResource(RecursoCadastroDeEndereço.class).queryParam("identificadorDoCliente", _id).build().toString(), "uriDoCadastroDeEndereço");
		List<String> urisDosEndereços = new LinkedList<String>();
		if (identificadoresDosEndereços != null) {
			for (String identificadorDoEndereço : identificadoresDosEndereços) {
				urisDosEndereços.add(UriBuilder.fromResource(RecursoEndereco.class).build(identificadorDoEndereço).toString());
			}
		}
		manipuladorJsonDaRepresentação.adicionarColeçãoDeCampos(urisDosEndereços, "urisDosEndereços");
		manipuladorJsonDoRecipiente.adicionarObjetoJson(representaçãoJson, "cliente");
		return recipiente;
	}
	
	@Override
	public Validador validarCadastro() {
		Validador validador = new Validador();
		_id = validador.validarIdentificador(_id, false);
		_rev = validador.validarRevisão(_rev, false);
		nome = validador.validarNome(nome, true);
		endereçosEletrônicos = validador.validarEndereçosEletrônicos(endereçosEletrônicos, false);
		telefones = validador.validarTelefones(telefones, true);
		informaçõesExtras = validador.validarInformaçõesExtras(informaçõesExtras, false);
		tipo = validador.validarTipoDeCliente(tipo);
		identificadoresDosEndereços = validador.validarIdentificadoresChavesDoCouchDb(identificadoresDosEndereços, "identificador de endereço", false);
		if (tipo.equals(TIPO_PESSOA_JURÍDICA)) {
			site = validador.validarSite(site, false);
			cnpj = validador.validarCnpj(cnpj, false);
			inscriçãoEstadual = validador.validarInscriçãoEstadual(inscriçãoEstadual, false);
		} 
		if (tipo.equals(TIPO_PESSOA_FISICA)) {
			cpf = validador.validarCpf(cpf, false);
		}
		return validador;
	}
	
	@Override
	public Validador validarAtualização() {
		Validador validador = validarCadastro();
		_id = validador.validarIdentificador(_id, true);
		_rev = validador.validarRevisão(_rev, true);
		return validador;
	}
}
