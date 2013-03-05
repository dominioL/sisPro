package br.protege.sisPro.modelo.validacao;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import br.protege.sisPro.modelo.json.ManipuladorJson;

public final class MensagemDeErro {
	
	private List<String> mensagensDeErro;
	private boolean possuiErro;
	
	public MensagemDeErro() {
		mensagensDeErro = new LinkedList<String>();
		possuiErro = false;
	}
	
	public void adicionarCampoInválido(String mensagem) {
		mensagensDeErro.add(mensagem);
		possuiErro = true;
	}
	
	public boolean possuiErro() {
		return possuiErro;
	}
	
	public String fornecerComoTexto() {
		return mensagensDeErro.toString();
	}
	
	public JSONObject fornecerComoJson() {
		JSONObject representaçãoJson = new JSONObject();
		ManipuladorJson manipuladorJson = new ManipuladorJson(representaçãoJson);
		manipuladorJson.adicionarCampo(possuiErro, "erro");
		manipuladorJson.adicionarColeçãoDeCampos(mensagensDeErro, "mensagensDeErro");
		
		return representaçãoJson;
	}
	
	public JSONObject fornecerMensagemDeRecursoNãoEncontradoComoJson() {
		possuiErro = true;
		mensagensDeErro.add("Recurso não encontrado");
		
		return fornecerComoJson();
	}
	
	public JSONObject fornecerMensagemDeFracassoComoJson() {
		JSONObject mensagemDeFracasso = new JSONObject();
		new ManipuladorJson(mensagemDeFracasso).adicionarCampo(true, "fracasso");
		
		return mensagemDeFracasso;
	}
	
	public JSONObject fornecerMensagemDeRecursoPaiNãoModificável() {
		possuiErro = true;
		mensagensDeErro.add("Não é possível alterar diretamente a coleção em que se encontra este recurso. Não altere o identificador do recurso pai.");
		
		return fornecerComoJson();
	}
	
	public JSONObject fornecerMensagemDeColeçãoDeRecursosNãoModificável() {
		possuiErro = true;
		mensagensDeErro.add("Não é possível alterar a coleção com os recursos filhos. Não altere nenhum identificador dos recursos filhos.");
		
		return fornecerComoJson();
	}
}
